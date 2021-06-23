package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "task_model", schema="public")
public class Task {
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private Date deadline;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private Date creationDate;
    @Getter @Setter
    private Date lastModifiedDate;
    @ManyToOne
    @Getter @Setter
    private User user;
    @ManyToMany(targetEntity = Category.class,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "task_categories",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName="id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @Getter @Setter
    private Set<Category> categories = new HashSet<>();

}
