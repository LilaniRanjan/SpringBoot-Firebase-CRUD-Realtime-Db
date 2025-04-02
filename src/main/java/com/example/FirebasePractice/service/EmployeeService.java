package com.example.FirebasePractice.service;

import com.example.FirebasePractice.dto.request.EmployeeCreateRequest;
import com.example.FirebasePractice.dto.request.EmployeeUpdateRequest;
import com.example.FirebasePractice.dto.response.EmployeeResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateRequest request);

    CompletableFuture<EmployeeResponse> getEmployeeById(String id);

    CompletableFuture<List<EmployeeResponse>> getAllEmployees();

}
