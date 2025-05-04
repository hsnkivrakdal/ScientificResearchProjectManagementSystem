package com.example.srpms.repository;

import com.example.srpms.models.Projectreport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectReportRepository extends JpaRepository<Projectreport, Integer> {
}
