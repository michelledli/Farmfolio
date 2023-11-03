package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;
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
    private String body;      // the title text of the post
    private Date createdAt;    // the date and time the post was made

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment entity = (Comment) o;

        return Objects.equals(id, entity.id) &&
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
                ", created_at='" + createdAt + '\'' +
                ", title='" + body + '\'' +
                '}';
    }
}

