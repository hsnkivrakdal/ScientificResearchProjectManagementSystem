package com.example.srpms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectId", nullable = false)
    private Integer id;

    @Column(name = "ProjectTitle", nullable = true, length = 200)
    private String projectTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ProjectApplicationDate")
    private Date projectApplicationDate;

    @Column(name = "ProjectCode", nullable = true, length = 50)
    private String projectCode;

    @Column(name = "IsApproved")
    private Boolean isApproved;

    @Column(name = "IsEvaluated")
    private Boolean isEvaluated;

    @Column(name = "IsSubmitted")
    private Boolean isSubmitted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectApplicationId")
    private Projectapplication projectApplication;

    @OneToMany(mappedBy = "project")
    private Set<Applicationdescription> applicationdescriptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Assesmentcomity> assesmentcomities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Ongoingproject> ongoingprojects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectactivityarea> projectactivityareas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectadditionalfile> projectadditionalfiles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectcoordinatorinformation> projectcoordinatorinformations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectinformation> projectinformations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectkeyword> projectkeywords = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectmachinery> projectmachineries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projectpersonnel> projectpersonnels = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Projecttechnologyreadinesslevel> projecttechnologyreadinesslevels = new LinkedHashSet<>();

}