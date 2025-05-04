package com.example.srpms.repository;

import com.example.srpms.models.Projectpersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPersonnelRepository extends JpaRepository<Projectpersonnel, Integer> {
}
