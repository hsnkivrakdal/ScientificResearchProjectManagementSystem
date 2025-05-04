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
@Table(name = "technologyreadinessleveltypes")
public class Technologyreadinessleveltype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnologyReadinessLevelTypeId", nullable = false)
    private Integer id;

    @Column(name = "TechnologyReadinessLevelTypeDefiniton", nullable = false, length = 50)
    private String technologyReadinessLevelTypeDefiniton;

    @OneToMany(mappedBy = "existingTechnologyLevel")
    private Set<Projecttechnologyreadinesslevel> projecttechnologyreadinesslevels = new LinkedHashSet<>();

}