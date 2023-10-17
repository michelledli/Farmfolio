package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private Long accountId;    // the id of the account that made the post
    private String title;      // the title text of the post
    private Date createdAt;    // the date and time the post was made
    @Default
    private boolean announcement = false; // whether or not the Post is an Announcement


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post entity = (Post) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(accountId, entity.accountId) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(announcement, entity.announcement) &&
                Objects.equals(title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, createdAt, title, announcement);
    }
 
    @Override
    public String toString() {
        return "PostEntity{" +
                "id='" + id +
                ", user_id='" + accountId + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", title='" + title + '\'' +
                ", title='" + announcement + '\'' +
                '}';
    }
}
