package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_model", schema="public")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String imageUrl;
    @Getter @Setter
    private Boolean emailVerified = false;
    @JsonIgnore
    @Getter @Setter
    private String password = null;
    @Getter @Setter
    private AuthProvider provider;
    @Getter @Setter
    private String providerId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "owner", referencedColumnName = "id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @Getter @Setter
    private Set<Task> tasks = new HashSet<>();
    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    private Set<Category> categories = new HashSet<>();

}

