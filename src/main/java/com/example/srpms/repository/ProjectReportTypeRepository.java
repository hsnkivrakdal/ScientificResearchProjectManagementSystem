package com.example.srpms.repository;

import com.example.srpms.models.Projectreporttype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectReportTypeRepository extends JpaRepository<Projectreporttype, Integer> {
}
