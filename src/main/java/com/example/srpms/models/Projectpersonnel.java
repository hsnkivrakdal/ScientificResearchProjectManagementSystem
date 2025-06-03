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
@Table(name = "projectpersonnel")
public class Projectpersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectPersonnelId", nullable = false)
    private Integer id;

    @Column(name = "IdentityNumber", nullable = false, length = 11)
    private String identityNumber;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Title", nullable = false, length = 80)
    private String title;

    @Column(name = "Affiliation", nullable = false, length = 100)
    private String affiliation;

    @Column(name = "Responsibility", nullable = false, length = 200)
    private String responsibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectPersonnelTypeId")
    private Personneltype projectPersonnelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

    @Override
    public String toString() {
        return projectPersonnelType.toString();
    }
}