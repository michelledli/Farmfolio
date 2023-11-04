package com.iloveyou.entity;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates setters and getters upon build
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post extends Auditable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;      // the title text of the post
    private String body;
    private String createdAt;    // the date and time the post was made
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
                Objects.equals(title, entity.title) &&
                Objects.equals(body, entity.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, title, body, announcement);
    }
 
    @Override
    public String toString() {
        return "PostEntity{" +
                "id='" + id +
                ", created_at='" + createdAt + '\'' +
                ", title='" + title + '\'' +
                ", title='" + body + '\'' +
                ", title='" + announcement + '\'' +
                '}';
    }

    public String getName() {
        return author.getFname() + " " + author.getLname();
    }
}
