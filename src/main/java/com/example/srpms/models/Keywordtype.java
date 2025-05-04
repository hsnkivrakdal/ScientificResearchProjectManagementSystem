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
@Table(name = "keywordtypes")
public class Keywordtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KeywordTypeId", nullable = false)
    private Integer id;

    @Column(name = "KeywordTypeTitle", nullable = false, length = 80)
    private String keywordTypeTitle;

    @OneToMany(mappedBy = "keywordType")
    private Set<Projectkeyword> projectkeywords = new LinkedHashSet<>();

}