package com.spring.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "task_model", schema="public", indexes = @Index(columnList = "owner"))
public class Task {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String input;
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
    @ManyToMany(targetEntity = Category.class,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "task_categories",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName="id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @Getter @Setter
    private Set<Category> categories;
    @Getter @Setter
    private String owner;




}
