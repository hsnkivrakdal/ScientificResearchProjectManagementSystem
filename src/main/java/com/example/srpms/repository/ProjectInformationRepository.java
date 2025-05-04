package com.example.srpms.repository;

import com.example.srpms.models.Projectinformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectInformationRepository extends JpaRepository<Projectinformation, Integer> {
}
