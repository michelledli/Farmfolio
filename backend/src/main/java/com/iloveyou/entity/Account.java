package com.iloveyou.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates setters and getters upon build
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account extends Auditable {

    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    @Column(unique=true)
    private String email;
    @JsonIgnore
    private String password;
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account entity = (Account) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(fname, entity.fname) &&
                Objects.equals(lname, entity.lname) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(password, entity.password);
    }

    // public List<String> getRoles() {
    //     List<String> roles = new ArrayList<>();
    //     roles.add("user");

    //     if(isAdmin) {
    //         roles.add("admin");
    //     }

    //     return roles;
    // }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, password);
    }

    @Override
    public java.lang.String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}