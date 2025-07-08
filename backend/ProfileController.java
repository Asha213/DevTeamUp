package com.devteamup.api;

import com.devteamup.model.Profile;
import com.devteamup.repository.ProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile updatedProfile) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.setBio(updatedProfile.getBio());
        profile.setSkills(updatedProfile.getSkills());
        profile.setGithubUrl(updatedProfile.getGithubUrl());
        profile.setLinkedinUrl(updatedProfile.getLinkedinUrl());
        profile.setExperience(updatedProfile.getExperience());
        return profileRepository.save(profile);
    }
}
