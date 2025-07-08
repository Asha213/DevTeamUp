package com.devteamup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @ElementCollection
    private List<String> techStack;
    @ElementCollection
    private List<String> rolesNeeded;
    private String timeline;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Status {
        OPEN, CLOSED, LOOKING_FOR_COLLABORATORS
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public List<String> getTechStack() { return techStack; }
    public void setTechStack(List<String> techStack) { this.techStack = techStack; }
    public List<String> getRolesNeeded() { return rolesNeeded; }
    public void setRolesNeeded(List<String> rolesNeeded) { this.rolesNeeded = rolesNeeded; }
    public String getTimeline() { return timeline; }
    public void setTimeline(String timeline) { this.timeline = timeline; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
