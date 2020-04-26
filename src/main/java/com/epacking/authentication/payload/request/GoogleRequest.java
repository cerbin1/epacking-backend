package com.epacking.authentication.payload.request;

import com.epacking.authentication.models.Role;
import lombok.Data;

import java.util.Set;

@Data
public class GoogleRequest {
    private Long id;
    private String username;
    private String email;
    private Set<Role> role;
    private String password;
}
