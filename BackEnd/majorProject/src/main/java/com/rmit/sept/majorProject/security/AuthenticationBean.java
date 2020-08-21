package com.rmit.sept.majorProject.security;

public class AuthenticationBean {
    private String role;


    public AuthenticationBean(String role) {
        this.role = role;

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
