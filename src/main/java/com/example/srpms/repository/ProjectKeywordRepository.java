package com.example.srpms.repository;

import com.example.srpms.models.Projectkeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectKeywordRepository extends JpaRepository<Projectkeyword, Integer> {
}
