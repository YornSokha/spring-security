package com.hrd.springsecurity.repository.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    int id;
    String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"  + role;
    }
}
