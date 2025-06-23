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
@Table(name = "lecturertitles")
public class Lecturertitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LecturerTitleId", nullable = false)
    private Integer id;

    @Column(name = "LecturerTitleDefinition", nullable = false, length = 80)
    private String lecturerTitleDefinition;

    @OneToMany(mappedBy = "lecturerTitle")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    @Override
    public String toString() {
        return lecturerTitleDefinition;
    }
}