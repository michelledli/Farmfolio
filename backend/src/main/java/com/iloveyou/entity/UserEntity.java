package com.iloveyou.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String password; // change data type?

    private UserEntity() {}

    public UserEntity(String fname, String lname, String email, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity entity = (UserEntity) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(fname, entity.fname) &&
                Objects.equals(lname, entity.lname) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(username, entity.username) &&
                Objects.equals(password, entity.password) &&;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, username, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.lname = lname;
    }

    public int getEmail() { return email; }

    public void setEmail(int email) { this.email = email; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Override
    public java.lang.String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
