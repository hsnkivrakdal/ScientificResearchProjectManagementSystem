package com.example.srpms.repository;

import com.example.srpms.models.Technologyreadinessleveltype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyReadinessLevelTypeRepository extends JpaRepository<Technologyreadinessleveltype,Integer> {
}
