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
@Table(name = "projecttypes")
public class Projecttype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectTypeId", nullable = false)
    private Integer id;

    @Column(name = "ProjectTypeTitle", nullable = false, length = 200)
    private String projectTypeTitle;

}