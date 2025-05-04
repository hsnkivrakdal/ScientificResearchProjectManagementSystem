package com.example.srpms.repository;

import com.example.srpms.models.Projectmachinery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMachineryRepository extends JpaRepository<Projectmachinery, Integer> {
}
