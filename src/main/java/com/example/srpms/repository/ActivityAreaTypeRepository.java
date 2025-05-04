package com.example.srpms.repository;

import com.example.srpms.models.Activityareatype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAreaTypeRepository  extends JpaRepository<Activityareatype, Integer> {
}
