package com.example.FirebasePractice.service;

import com.example.FirebasePractice.dto.request.EmployeeCreateRequest;
import com.example.FirebasePractice.dto.response.EmployeeResponse;
import com.example.FirebasePractice.model.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final DatabaseReference databaseReference;

    public EmployeeServiceImpl(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("employees"); // Reference to Firebase 'employees' node
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        String id = databaseReference.push().getKey();
        Employee employee = new Employee(id, request.getName(), request.getEmail(), request.getDepartment());
        databaseReference.child(id).setValueAsync(employee);

        return mapToResponse(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment());
    }
}
