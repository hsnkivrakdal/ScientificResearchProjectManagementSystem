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
@Table(name = "projectmachinery")
public class Projectmachinery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MachineryId", nullable = false)
    private Integer id;

    @Column(name = "MachineryTitle", nullable = false, length = 150)
    private String machineryTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MachineryTypeId")
    private Machinerytype machineryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

    @Override
    public String toString() {
        return machineryType.getMachineryTitle();
    }
}