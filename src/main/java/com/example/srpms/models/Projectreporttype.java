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
@Table(name = "projectreporttypes")
public class Projectreporttype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectReportTypeId", nullable = false)
    private Integer id;

    @Column(name = "ProjectReportTypeTitle", nullable = false, length = 50)
    private String projectReportTypeTitle;

    @OneToMany(mappedBy = "projectReportType")
    private Set<Projectreport> projectreports = new LinkedHashSet<>();

}