package com.devteamup.repository;

import com.devteamup.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p JOIN p.techStack t WHERE t = :tech")
    List<Project> findByTechStack(@Param("tech") String tech);
}
