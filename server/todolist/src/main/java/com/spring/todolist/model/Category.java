package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "category_model", schema="public")
public class Category{
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @ManyToMany(targetEntity = Task.class, mappedBy="categories",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @Getter @Setter
    private Set<Task> tasks = new HashSet<>();

}
