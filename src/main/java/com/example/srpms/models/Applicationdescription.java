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
@Table(name = "applicationdescriptions")
public class Applicationdescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ApplicatiponDescriptionId", nullable = false)
    private Integer id;

    @Column(name = "ProgramName", nullable = false, length = 150)
    private String programName;

    @Column(name = "CallName", nullable = false, length = 150)
    private String callName;

    @Column(name = "ApplicationDefinition", nullable = false, length = 3500)
    private String applicationDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

}