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
@Table(name = "coordinatorpositions")
public class Coordinatorposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CoordinatorPositionId", nullable = false)
    private Integer id;

    @Column(name = "CoordinatorPositionTitle", nullable = false, length = 150)
    private String coordinatorPositionTitle;

    @OneToMany(mappedBy = "coordinatorPosition")
    private Set<Projectcoordinatorinformation> projectcoordinatorinformations = new LinkedHashSet<>();

}