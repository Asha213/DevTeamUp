package com.devteamup.api;

import com.devteamup.model.Project;
import com.devteamup.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        logger.info("Fetching all projects");
        List<Project> projects = projectService.getAllProjects();
        logger.info("Returning {} projects", projects.size());
        return projects;
    }

    @GetMapping("/search")
    public List<Project> searchByTechStack(@RequestParam String tech) {
        logger.info("Searching projects by tech: {}", tech);
        List<Project> projects = projectService.findByTechStack(tech);
        logger.info("Found {} projects", projects.size());
        return projects;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        logger.info("Creating project: {}", project.getTitle());
        project.setCreatedAt(LocalDateTime.now());
        Project savedProject = projectService.saveProject(project);
        logger.info("Project created with ID: {}", savedProject.getId());
        return ResponseEntity.status(201).body(savedProject);
    }
}
