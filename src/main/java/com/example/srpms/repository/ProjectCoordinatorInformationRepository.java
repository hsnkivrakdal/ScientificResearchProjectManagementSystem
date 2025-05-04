package com.example.srpms.repository;

import com.example.srpms.models.Projectcoordinatorinformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCoordinatorInformationRepository extends JpaRepository<Projectcoordinatorinformation, Integer> {
}
