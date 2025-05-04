package com.example.srpms.repository;

import com.example.srpms.models.Assesmentcomity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssesmentComityRepository extends JpaRepository<Assesmentcomity, Integer> {
}
