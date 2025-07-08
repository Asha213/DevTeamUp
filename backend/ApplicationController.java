package com.devteamup.api;

import com.devteamup.model.Application;
import com.devteamup.repository.ApplicationRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @PostMapping
    public Application applyToProject(@RequestBody Application application) {
        application.setStatus("PENDING");
        return applicationRepository.save(application);
    }

    @PutMapping("/{id}/accept")
    public Application acceptApplication(@PathVariable Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus("ACCEPTED");
        return applicationRepository.save(application);
    }

    @PutMapping("/{id}/reject")
    public Application rejectApplication(@PathVariable Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus("REJECTED");
        return applicationRepository.save(application);
    }
}
