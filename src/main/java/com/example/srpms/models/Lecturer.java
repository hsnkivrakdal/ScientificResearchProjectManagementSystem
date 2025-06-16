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
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LecturerId", nullable = false)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "MailAddress", nullable = false, length = 80)
    private String mailAddress;

    @Column(name = "PhoneNumber", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "DoctorateDegree")
    private Integer doctorateDegree;

    @Column(name = "IdentityNumber", nullable = false, length = 11)
    private String identityNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerTypeId")
    private Lecturertype lecturerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerTitleId")
    private Lecturertitle lecturerTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerAffiliationId")
    private Lectureraffiliation lecturerAffiliation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @OneToMany(mappedBy = "lecturer")
    private Set<Projectcoordinatorinformation> projectcoordinatorinformations = new LinkedHashSet<>();

}