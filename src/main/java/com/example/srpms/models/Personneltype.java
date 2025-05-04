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
@Table(name = "personneltypes")
public class Personneltype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonnelTypeId", nullable = false)
    private Integer id;

    @Column(name = "PersonnelTypeTitle", nullable = false, length = 100)
    private String personnelTypeTitle;

    @OneToMany(mappedBy = "projectPersonnelType")
    private Set<Projectpersonnel> projectpersonnels = new LinkedHashSet<>();

}