package com.anil.springboot_multiauth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TargetLocationService {

    private final String targetLocation;

    public TargetLocationService(@Value("${client.target.url}") String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String determineTargetLocation(){
        return targetLocation;
    }
}
