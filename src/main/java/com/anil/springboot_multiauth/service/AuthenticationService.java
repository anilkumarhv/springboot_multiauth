package com.anil.springboot_multiauth.service;

import com.anil.springboot_multiauth.dto.UserDTO;

public interface AuthenticationService {
    public void authenticate(UserDTO user);
}
