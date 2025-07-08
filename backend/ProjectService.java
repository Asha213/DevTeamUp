package com.devteamup.service;

import com.devteamup.model.Project;
import com.devteamup.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> findByTechStack(String tech) {
        return projectRepository.findByTechStack(tech);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}
