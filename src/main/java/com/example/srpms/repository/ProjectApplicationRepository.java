package com.example.srpms.repository;

import com.example.srpms.models.Projectapplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<Projectapplication, Integer> {
}
