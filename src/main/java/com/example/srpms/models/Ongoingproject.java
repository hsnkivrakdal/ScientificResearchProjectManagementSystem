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
@Table(name = "ongoingprojects")
public class Ongoingproject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OngoingProjectId", nullable = false)
    private Integer id;

    @Column(name = "OngoingProjectCode", nullable = false, length = 50)
    private String ongoingProjectCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

    @OneToMany(mappedBy = "ongoingProject")
    private Set<Projectreport> projectreports = new LinkedHashSet<>();

}