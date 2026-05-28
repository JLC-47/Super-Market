package com.supermarket.market.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.market.dto.EmployeeRegisterDTO;
import com.supermarket.market.entity.Employees;
import com.supermarket.market.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String registerEmployee(EmployeeRegisterDTO data) {
        
        if (employeeRepository.existsByNationalId(data.getNationalId())) {
            throw new RuntimeException("Error: Ya existe un empleado registrado con esta cédula.");
        }

        Employees newEmployee = new Employees();
        newEmployee.setNationalId(data.getNationalId());
        newEmployee.setName(data.getName());
        
        // usamos valueOf() para convertirlo y toUpperCase() por si lo escriben en minúsculas.
        newEmployee.setRole(Employees.Role.valueOf(data.getRole().toUpperCase()));
        
        newEmployee.setHireDate(data.getHireDate());
        newEmployee.setSalary(data.getSalary());

        employeeRepository.save(newEmployee);

        return "Empleado registrado con éxito";
    }
}