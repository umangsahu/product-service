package com.example.first_project.inheritanceType.singleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="st_users")
@Inheritance(strategy =  InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type", discriminatorType =  DiscriminatorType.INTEGER)
@DiscriminatorValue(value ="0")
public class User {
    @Id
    private  long id;
    private  String name;
    private String email;

}
