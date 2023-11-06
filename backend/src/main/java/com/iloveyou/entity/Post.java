
package com.iloveyou.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends AbstractEntity {
    private String title;      // the title text of the post
    private String body;
    private String createdAt;    // the date and time the post was made
    @Default
    private boolean announcement = false; // whether or not the Post is an Announcement

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;

    // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Transient
    private List<Comment> comments = new ArrayList<>();
    // @OneToMany(fetch = FetchType.EAGER)
    // @JoinColumn(name = "post_id", referencedColumnName = "id")
    // private List<Comment> comments;

    @JsonGetter
    public List<Comment> getComments() {
        return this.comments;
    }
    @JsonIgnore
    public void setComments(List<Comment> list) {
        this.comments = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post entity = (Post) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(announcement, entity.announcement) &&
                Objects.equals(title, entity.title) &&
                Objects.equals(body, entity.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, title, body, announcement);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return author.getFname() + " " + author.getLname();
    }
}
