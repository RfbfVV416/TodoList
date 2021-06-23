package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    private Set<Task> tasks = new HashSet<Task>();
}

