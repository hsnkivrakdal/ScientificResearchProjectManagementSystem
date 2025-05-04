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
@Table(name = "lecturertypes")
public class Lecturertype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LecturerTypeId", nullable = false)
    private Integer id;

    @Column(name = "LecturerTypeTitle", nullable = false, length = 200)
    private String lecturerTypeTitle;

    @OneToMany(mappedBy = "lecturerType")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

}