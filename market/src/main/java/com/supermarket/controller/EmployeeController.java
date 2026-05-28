package com.supermarket.controller;

import com.supermarket.dto.EmployeeRegisterDTO;
import com.supermarket.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // @PostMapping indica que este método solo responde cuando envían datos nuevos (crear)
    @PostMapping
    public ResponseEntity<String> registerEmployee(@Valid @RequestBody EmployeeRegisterDTO data) {
        
        // Si el código llega a esta línea, significa que @Valid revisó el DTO y todo está perfecto.
        // No hay cédulas vacías ni salarios negativos.
        
        // Le pasamos los datos limpios al Service para que haga la lógica de negocio y guarde.
        String resultado = employeeService.registerEmployee(data);
        
        // Le respondemos al usuario con un "Ok".
        return ResponseEntity.ok(resultado);
    }
}