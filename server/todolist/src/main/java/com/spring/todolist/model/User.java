package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_model", schema="public")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String email;
    private String imageUrl;
    private Boolean emailVerified = false;
    @JsonIgnore
    private String password = null;
    private AuthProvider provider;
    private String providerId;
}

