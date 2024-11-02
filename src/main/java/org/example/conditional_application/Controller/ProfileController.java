package org.example.conditional_application.Controller;

import org.example.conditional_application.profile.SystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final SystemProfile systemProfile;

    @Autowired
    public ProfileController(SystemProfile systemProfile) {
        this.systemProfile = systemProfile;
    }

    @GetMapping("/")
    public String getProfile() {
        return systemProfile.getProfile();
    }
}
