package com.iloveyou.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {
    private String fname;
    private String lname;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private boolean isAdmin;

    @OneToMany(mappedBy = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Post> forumPosts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Comment> forumComments = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Account entity = (Account) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(fname, entity.fname) &&
                Objects.equals(lname, entity.lname) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}