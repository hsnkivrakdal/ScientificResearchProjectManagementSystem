package com.example.srpms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projectinformations")
public class Projectinformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectInformationId", nullable = false)
    private Integer id;

    @Column(name = "ProjectTitle", nullable = false, length = 150)
    private String projectTitle;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ProjectApplicationDate")
    private Instant projectApplicationDate;

    @Column(name = "ProjectSummary", nullable = false, length = 600)
    private String projectSummary;

    @Column(name = "ProjectHarmfulness", nullable = false)
    private Boolean projectHarmfulness = false;

    @Column(name = "ProjectPermits", nullable = false)
    private Boolean projectPermits = false;

    @Column(name = "ProjectProformaInvoice", nullable = false)
    private Boolean projectProformaInvoice = false;

    @Column(name = "ProjectSupportLetter", nullable = false)
    private Boolean projectSupportLetter = false;

    @Column(name = "ProjectEthicsCommittee", nullable = false)
    private Boolean projectEthicsCommittee = false;

    @Column(name = "ProjectBudget", nullable = false)
    private Float projectBudget;

    @Column(name = "ProjectDuration", nullable = false)
    private Short projectDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

}