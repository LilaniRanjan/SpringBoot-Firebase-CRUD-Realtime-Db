package com.example.FirebasePractice.controller;

import com.example.FirebasePractice.dto.request.EmployeeCreateRequest;
import com.example.FirebasePractice.dto.response.EmployeeResponse;
import com.example.FirebasePractice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // POST endpoint for creating an employee
    @PostMapping("/create")
    public EmployeeResponse createEmployee(@RequestBody EmployeeCreateRequest request) {
        return employeeService.createEmployee(request);
    }
}
