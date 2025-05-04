package com.example.srpms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projectapplications")
public class Projectapplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectApplicationId", nullable = false)
    private Integer id;

    @Column(name = "ProjectApplicationtTitle", nullable = false, length = 200)
    private String projectApplicationtTitle;

    @Column(name = "ProjectApplicationInformation", nullable = false, length = 50)
    private String projectApplicationInformation;

    @Column(name = "ProjectApplicationStartDate", nullable = false)
    private LocalDate projectApplicationStartDate;

    @Column(name = "ProjectApplicationEndDate", nullable = false)
    private LocalDate projectApplicationEndDate;

    @Column(name = "IsActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "projectApplication")
    private Set<Project> projects = new LinkedHashSet<>();

}