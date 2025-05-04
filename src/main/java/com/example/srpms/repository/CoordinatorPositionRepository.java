package com.example.srpms.repository;

import com.example.srpms.models.Coordinatorposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorPositionRepository extends JpaRepository<Coordinatorposition, Integer> {
}
