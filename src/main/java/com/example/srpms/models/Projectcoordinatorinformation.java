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
@Table(name = "projectcoordinatorinformations")
public class Projectcoordinatorinformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CoordinatorId", nullable = false)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "MailAddress", nullable = false, length = 80)
    private String mailAddress;

    @Column(name = "PhoneNumber", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "DoctorateDegree", nullable = false, length = 100)
    private String doctorateDegree;

    @Column(name = "CoordinatorResponsibility", nullable = false, length = 250)
    private String coordinatorResponsibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CoordinatorPositionId")
    private Coordinatorposition coordinatorPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerId")
    private Lecturer lecturer;

}