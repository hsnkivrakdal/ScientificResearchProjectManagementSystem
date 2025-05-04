package com.example.srpms.repository;

import com.example.srpms.models.Lecturertitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerTitleRepository extends JpaRepository<Lecturertitle, Integer> {
}
