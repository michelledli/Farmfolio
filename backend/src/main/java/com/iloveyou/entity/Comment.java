package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private Long postId;    // the id of the comment that the reply belongs to
    private String body;      // the body of the comment
    private String createdAt;    // the date and time the comment was made

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment entity = (Comment) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(postId, entity.postId) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(body, entity.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, body);
    }
 
    @Override
    public String toString() {
        return "PostEntity{" +
                "id='" + id +
                "post_id='" + postId +
                ", created_at='" + createdAt + '\'' +
                ", title='" + body + '\'' +
                '}';
    }

    public String getName() {
        return author.getFname() + " " + author.getLname();
    }
}

