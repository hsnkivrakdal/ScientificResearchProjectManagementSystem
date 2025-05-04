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
@Table(name = "projectreports")
public class Projectreport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectReportId", nullable = false)
    private Integer id;

    @Column(name = "ProjectReportDefinition", nullable = false, length = 2500)
    private String projectReportDefinition;

    @Column(name = "ProjectReportDocument")
    private byte[] projectReportDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectReportTypeId")
    private Projectreporttype projectReportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OngoingProjectId")
    private Ongoingproject ongoingProject;

}