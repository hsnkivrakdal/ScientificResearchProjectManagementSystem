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
@Table(name = "projectadditionalfiles")
public class Projectadditionalfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FileId", nullable = false)
    private Integer id;

    @Column(name = "FileDescription", nullable = false, length = 100)
    private String fileDescriptionId;

    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;

    @Column(name = "FileType", nullable = false, length = 45)
    private String fileType;

    @Column(name = "FilePath", nullable = false, length = 150)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId")
    private Project project;

}