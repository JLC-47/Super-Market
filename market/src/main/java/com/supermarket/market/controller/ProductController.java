package com.supermarket.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.market.dto.HttpGlobalResponse;
import com.supermarket.market.dto.MessageResponseDTO;
import com.supermarket.market.dto.ProductResgisterDTO;
import com.supermarket.market.dto.ProductResponseDTO;
import com.supermarket.market.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createProduct(@Valid @RequestBody ProductResgisterDTO request){
        try {
            MessageResponseDTO response = productService.createProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            MessageResponseDTO errorResponse = new MessageResponseDTO();

            errorResponse.setMessage("Error al crear el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    @GetMapping("/get-products")
    public List<ProductResponseDTO> getProducts() {
        List<ProductResponseDTO> response = productService.getProducts();

        return response;
    }

    @GetMapping("/get-product/{id}")
    public HttpGlobalResponse<ProductResponseDTO> getProductById(@PathVariable Long id){
        try {
            HttpGlobalResponse<ProductResponseDTO> response = productService.getProduct(id);
            return response;
            
        } catch (Exception e) {
            HttpGlobalResponse<ProductResponseDTO> errorResponse = new HttpGlobalResponse<>();
            errorResponse.setMessage("Error al buscar el producto: " + e.getMessage());

            return errorResponse;
        }
    }


    @PutMapping("/update-product/{id}")
    public HttpGlobalResponse<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductResgisterDTO request){
        try {
            HttpGlobalResponse<ProductResponseDTO> response = productService.updateProduct(id, request);
            return response;
            
        } catch (Exception e) {
            HttpGlobalResponse<ProductResponseDTO> errorResponse = new HttpGlobalResponse<>();
            errorResponse.setMessage("Error al eliminar el producto: " + e.getMessage());
            return errorResponse;
        }
    }  
    
    
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@PathVariable Long id){
        try {
            MessageResponseDTO response = productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            
        } catch (Exception e) {
           MessageResponseDTO errorResponse = new MessageResponseDTO();
            errorResponse.setMessage("Error al eliminar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
   

    @PostMapping("/stock-entry")
    public MessageResponseDTO registerStock(@RequestParam Long productId, @RequestParam Long supplierId, @RequestParam Long quantity){
        try {
            MessageResponseDTO response = productService.registerStock(productId, supplierId, quantity);
            return response;
            
        } catch (Exception e) {
            MessageResponseDTO errorResponse = new MessageResponseDTO();
            errorResponse.setMessage("Error en la entrada de almacén: " + e.getMessage());
            return errorResponse;
        }
    }
    
}
