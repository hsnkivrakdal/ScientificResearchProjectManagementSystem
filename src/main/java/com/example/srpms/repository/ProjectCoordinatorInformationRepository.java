package com.example.srpms.repository;

import com.example.srpms.models.Projectcoordinatorinformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectCoordinatorInformationRepository extends JpaRepository<Projectcoordinatorinformation, Integer> {
}
