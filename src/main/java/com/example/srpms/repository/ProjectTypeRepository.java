package com.example.srpms.repository;

import com.example.srpms.models.Projecttype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTypeRepository extends JpaRepository<Projecttype, Integer> {
}
