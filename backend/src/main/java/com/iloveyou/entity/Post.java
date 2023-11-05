package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;

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
public class Post  extends AbstractEntity {
    private String title;      // the title text of the post
    private Date createdAt;    // the date and time the post was made
    @Default
    private boolean announcement = false; // whether or not the Post is an Announcement

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post entity = (Post) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(announcement, entity.announcement) &&
                Objects.equals(title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, title, announcement);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public String getName() {
        return author.getFname();
    }
}
