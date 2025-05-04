package com.example.srpms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activityareatypes")
public class Activityareatype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActivityAreaTypeId", nullable = false)
    private Integer id;

    @Column(name = "ActivityAreaTypeTitle", nullable = false, length = 80)
    private String activityAreaTypeTitle;

    @OneToMany(mappedBy = "activityAreaType")
    private Set<Projectactivityarea> projectactivityareas = new LinkedHashSet<>();

}