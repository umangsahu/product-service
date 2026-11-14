package com.example.first_project.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String categoryName;
    private String description;
}
