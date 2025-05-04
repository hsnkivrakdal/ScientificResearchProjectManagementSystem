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
@Table(name = "machinerytypes")
public class Machinerytype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MachineryTypeId", nullable = false)
    private Integer id;

    @Column(name = "MachineryTitle", nullable = false, length = 50)
    private String machineryTitle;

    @OneToMany(mappedBy = "machineryType")
    private Set<Projectmachinery> projectmachineries = new LinkedHashSet<>();

}