package org.example.conditional_application.profile;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";//разработчик
    }
}
