package com.example.srpms.repository;


import com.example.srpms.models.Lectureraffiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerAffiliationRepository extends JpaRepository<Lectureraffiliation, Integer> {
}
