package com.example.SpringBoot_Firebase_CRUD.model;

import lombok.Data;

@Data
public class Task {
    private String id;
    private String title;
    private boolean completed;
}
