package com.example.srpms.repository;

import com.example.srpms.models.Projectactivityarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectActivityAreaRepository extends JpaRepository<Projectactivityarea, Integer> {
}
