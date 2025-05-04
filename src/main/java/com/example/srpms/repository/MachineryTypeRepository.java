package com.example.srpms.repository;

import com.example.srpms.models.Machinerytype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineryTypeRepository extends JpaRepository<Machinerytype, Integer> {
}
