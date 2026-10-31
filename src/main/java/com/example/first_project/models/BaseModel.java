package com.example.first_project.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
    private long id;
    private String updated_at;
    private String created_at;

}
