package com.example.FirebasePractice.service;

import com.example.FirebasePractice.dto.request.EmployeeCreateRequest;
import com.example.FirebasePractice.dto.response.EmployeeResponse;
import com.example.FirebasePractice.model.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Override
    public CompletableFuture<EmployeeResponse> getEmployeeById(String id) {
        CompletableFuture<EmployeeResponse> future = new CompletableFuture<>();

        // Reference to the specific employee node using the provided 'id'
        DatabaseReference employeeRef = databaseReference.child(id);

        employeeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Employee employee = dataSnapshot.getValue(Employee.class);
                    if (employee != null) {
                        future.complete(mapToResponse(employee));  // Return the employee details
                    } else {
                        future.completeExceptionally(new Exception("Employee not found"));
                    }
                } else {
                    future.completeExceptionally(new Exception("Employee not found"));
                }
            }

            @Override
            public void onCancelled(com.google.firebase.database.DatabaseError error) {
                future.completeExceptionally(new Exception("Failed to fetch employee: " + error.getMessage()));
            }
        });

        return future;
    }



    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment());
    }
}
