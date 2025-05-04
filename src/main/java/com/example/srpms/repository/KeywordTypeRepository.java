package com.example.srpms.repository;

import com.example.srpms.models.Keywordtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordTypeRepository extends JpaRepository<Keywordtype, Integer> {
}
