package com.example.demo.security.jwt;

public class RegisterRequest {

    private String username;
    private String password;
    private String mail;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail(){return  mail;}

    public void setMail(String mail) {this.mail = mail;}

    @Override
    public String toString() {
        return "RegisterRequest [username=" + username + ", password=" + password + ",mail=" + mail + "]";
    }
}
