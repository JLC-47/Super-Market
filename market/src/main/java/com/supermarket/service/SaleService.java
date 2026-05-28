package com.supermarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supermarket.dto.MessageResponseDTO;
import com.supermarket.dto.SaleRequestDTO;
import com.supermarket.dto.SalesDetailRequestDTO;
import com.supermarket.entity.Employees;
import com.supermarket.entity.Products;
import com.supermarket.entity.SaleDetails;
import com.supermarket.entity.Sales;
import com.supermarket.repository.EmployeeRepository;
import com.supermarket.repository.ProductRepository;
import com.supermarket.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public MessageResponseDTO processSale(SaleRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Employees> employeeFound = employeeRepository.findById(request.getEmployeeId());
        if (employeeFound.isEmpty()) {
            throw new RuntimeException("Error: El empleado con ID " + request.getEmployeeId() + " no existe.");
        }

        Sales sale = new Sales();
        sale.setEmployee(employeeFound.get());

        List<SaleDetails> detailsList = new ArrayList<>();
        Double subtotalAcumulado = 0.0;

        for (SalesDetailRequestDTO item : request.getItems()) {
            Optional<Products> productFound = productRepository.findById(item.getProductId());

            if (productFound.isEmpty() || !productFound.get().isStatus()) {
                throw new RuntimeException(
                        "Error: El producto con ID " + item.getProductId() + " no existe o está inactivo.");
            }

            Products product = productFound.get();

            if (product.getStock() < item.getQuantitiy()) {
                throw new RuntimeException("Error: Stock insuficiente para '" + product.getName()
                        + "'. Disponible: " + product.getStock() + ", Solicitado: " + item.getQuantitiy());
            }

            product.setStock(product.getStock() - item.getQuantitiy());
            productRepository.save(product);

            SaleDetails detail = new SaleDetails();
            detail.setSale(sale);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantitiy());
            detail.setUnitPrice(product.getPrice());

            Double detailSubtotal = product.getPrice() * item.getQuantitiy();
            detail.setSubtotal(detailSubtotal);

            subtotalAcumulado += detailSubtotal;

            detailsList.add(detail);
        }

        Double vatCalculado = subtotalAcumulado * 0.19;
        Double totalCalculado = subtotalAcumulado + vatCalculado;

        sale.setSubtotal(subtotalAcumulado);
        sale.setVat(vatCalculado);
        sale.setTotal(totalCalculado);
        sale.setDetails(detailsList);

        saleRepository.save(sale);

        response.setMessage("Venta procesada con éxito. Factura N° " + sale.getId() + " - Total: $" + totalCalculado);
        return response;
    }
}