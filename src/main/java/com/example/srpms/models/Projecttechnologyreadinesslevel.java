package com.example.srpms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projecttechnologyreadinesslevels")
public class Projecttechnologyreadinesslevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnlologyReadinessLevelId", nullable = false)
    private Integer id;

    @Column(name = "FieldOfProjectType", nullable = false)
    private Boolean fieldOfProjectType = true;

    @Column(name = "CurrentTecReadLevelJustification", nullable = false, length = 300)
    private String currentTecReadLevelJustification;

    @Column(name = "TargettedTecReadLevelJustification", nullable = false, length = 300)
    private String targettedTecReadLevelJustification;

    @Column(name = "TechReadLevelFile")
    private byte[] techReadLevelFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExistingTechnologyLevel")
    private Technologyreadinessleveltype existingTechnologyLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TargettedTechnologyLevel")
    private Technologyreadinessleveltype targettedTechnologyLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

}