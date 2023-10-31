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
public class Comment extends Auditable {

    @Id
    @GeneratedValue
    private Long id;            
    private Long postId;    // the id of the post that the reply belongs to
    private Long accountId;    // the id of the account that made the post
    private String body;      // the title text of the post
    private Date createdAt;    // the date and time the post was made



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment entity = (Comment) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(postId, entity.postId) &&
                Objects.equals(accountId, entity.accountId) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(body, entity.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, createdAt, body);
    }
 
    @Override
    public String toString() {
        return "PostEntity{" +
                "id='" + id +
                "post_id='" + postId +
                ", user_id='" + accountId + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", title='" + body + '\'' +
                '}';
    }
}

