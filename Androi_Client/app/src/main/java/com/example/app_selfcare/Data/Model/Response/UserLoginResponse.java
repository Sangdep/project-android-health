package com.example.app_selfcare.Data.Model.Response;

public class UserLoginResponse {
    private boolean authenticated;
    private String token;
    private String role;

    public boolean isAuthenticated() { return authenticated; }
    public String getToken() { return token; }
    public String getRole() { return role; }
}
