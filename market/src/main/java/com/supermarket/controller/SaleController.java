package com.supermarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supermarket.dto.MessageResponseDTO;
import com.supermarket.dto.SaleRequestDTO;
import com.supermarket.service.SaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    
    private final SaleService saleService;


    @PostMapping("/process")
    public ResponseEntity<MessageResponseDTO> processSale(@Valid @RequestBody SaleRequestDTO request ){

        try {
            MessageResponseDTO response = saleService.processSale(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            MessageResponseDTO errorResponse = new MessageResponseDTO();

            errorResponse.setMessage(e.getMessage()); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
