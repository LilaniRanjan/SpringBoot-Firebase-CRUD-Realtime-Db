package com.example.FirebasePractice.controller;

import com.example.FirebasePractice.dto.request.EmployeeCreateRequest;
import com.example.FirebasePractice.dto.response.EmployeeResponse;
import com.example.FirebasePractice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    // GET endpoint for fetching an employee by ID
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable String id) throws ExecutionException, InterruptedException, ExecutionException {
        return employeeService.getEmployeeById(id).get();  // Fetch and return employee data by ID
    }

    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployees() throws ExecutionException, InterruptedException {
        // Call service method to get all employees and block until result is available
        return employeeService.getAllEmployees().get();
    }
}
