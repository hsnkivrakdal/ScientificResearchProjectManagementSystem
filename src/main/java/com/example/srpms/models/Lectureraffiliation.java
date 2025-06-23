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
@Table(name = "lectureraffiliations")
public class Lectureraffiliation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LecturerAffiliationId", nullable = false)
    private Integer id;

    @Column(name = "LecturerAffiliationTitle", nullable = false, length = 100)
    private String lecturerAffiliationTitle;

    @OneToMany(mappedBy = "lecturerAffiliation")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    @Override
    public String toString() {
        return lecturerAffiliationTitle;
    }
}