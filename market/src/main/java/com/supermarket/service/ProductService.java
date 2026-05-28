package com.supermarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.supermarket.dto.HttpGlobalResponse;
import com.supermarket.dto.MessageResponseDTO;
import com.supermarket.dto.ProductResgisterDTO;
import com.supermarket.dto.ProductResponseDTO;
import com.supermarket.entity.Products;
import com.supermarket.entity.Suppliers;
import com.supermarket.repository.ProductRepository;
import com.supermarket.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public MessageResponseDTO createProduct(ProductResgisterDTO request) {

        if (productRepository.existsByBarcode(request.getBarcode())) {
            throw new RuntimeException("Error de validación: Ya existe un producto registrado con el código de barras '"
                    + request.getBarcode() + "'.");
        }

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Producto creado correctamente");

        Products product = new Products();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStatus(request.isStatus());
        productRepository.save(product);
        return response;
    }

    public List<ProductResponseDTO> getProducts() {
        List<ProductResponseDTO> listProduct = new ArrayList<>();
        List<Products> productsFound = productRepository.findAll();

        for (Products product : productsFound) {

            if (product.isStatus()) {
                ProductResponseDTO productNew = new ProductResponseDTO();
                productNew.setId(product.getId());
                productNew.setName(product.getName());
                productNew.setDescription(product.getDescription());
                productNew.setBarcode(product.getBarcode());
                productNew.setPrice(product.getPrice());
                productNew.setStock(product.getStock());
                productNew.setStatus(product.isStatus());

                listProduct.add(productNew);
            }

        }
        return listProduct;
    }


    public HttpGlobalResponse<ProductResponseDTO> getProduct(Long id) {
        HttpGlobalResponse<ProductResponseDTO> response = new HttpGlobalResponse<>();
        Optional<Products> productFound = productRepository.findById(id);

        if (productFound.isEmpty() || !productFound.get().isStatus()) {
            response.setMessage("Error: El producto solicitado no existe o se encuentra inactivo.");
            return response;
        }

        Products product = productFound.get();
        ProductResponseDTO newProduct = new ProductResponseDTO();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setBarcode(product.getBarcode());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setStatus(product.isStatus());

        response.setMessage("Producto obtenido con éxito");
        response.setData(newProduct); 
        return response;
    }


    public HttpGlobalResponse<ProductResponseDTO> updateProduct(Long id, ProductResgisterDTO request) {
        HttpGlobalResponse<ProductResponseDTO> response = new HttpGlobalResponse<>();
        Optional<Products> productFound = productRepository.findById(id);

        if (productFound.isEmpty()) {
            response.setMessage("Error: No se puede actualizar un producto que no existe.");
            return response;
        }

        Products product = productFound.get();
        
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStatus(request.isStatus());

        productRepository.save(product);

        ProductResponseDTO newProduct = new ProductResponseDTO();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setBarcode(product.getBarcode());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setStatus(product.isStatus());

        response.setMessage("Producto actualizado correctamente");
        response.setData(newProduct);
        return response;
    }

    public MessageResponseDTO deleteProduct(Long id) {

        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Products> productFound = productRepository.findById(id);

        if (productFound.isEmpty()) {
            response.setMessage("Error: El producto no fue encontrado o no existe.");

            return response;
        }

        Products product = productFound.get();

        product.setStatus(false);
        productRepository.save(product);

        response.setMessage("Producto '" + product.getName() + "' desactivado (borrado lógico) correctamente.");

        return response;
    }

    public MessageResponseDTO registerStock(Long productId, Long supplierId, Long quantity) {
        MessageResponseDTO response = new MessageResponseDTO();
        if (quantity <= 0) {
            response.setMessage("Error de validación: La cantidad a ingresar debe ser mayor a cero.");
            return response;
        }

        Optional<Products> productFound = productRepository.findById(productId);
        if (productFound.isEmpty()) {
            response.setMessage("Error: El producto con ID " + productId + " no existe.");
            return response;
        }

        Optional<Suppliers> supplierFound = supplierRepository.findById(supplierId);
        if (supplierFound.isEmpty()) {
            response.setMessage("Error: El proveedor con ID " + supplierId + " no existe.");
            return response;
        }

        Products product = productFound.get();

        Long newStock = product.getStock() + quantity;
        product.setStock(newStock);

        productRepository.save(product);

        response.setMessage("Entrada de almacén registrada con éxito. Nuevo stock de '" + product.getName() + "': " + newStock);
        return response;
    }

}
 