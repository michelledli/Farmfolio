package com.iloveyou.entity;

import java.util.Objects;

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
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String password; // change data type?

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
                Objects.equals(password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, username, password);
    }

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
