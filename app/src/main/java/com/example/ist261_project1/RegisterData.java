package com.example.ist261_project1;

public class RegisterData {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public RegisterData()
    {
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.username = "";
        this.password = "";
    }

    public RegisterData(String firstname, String lastname, String email, String username,String password)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
