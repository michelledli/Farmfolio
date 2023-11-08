package com.iloveyou.entity;

import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends AbstractEntity {
    private Long postId;
    private String body; // the title text of the post
    private String createdAt; // the date and time the post was made

    @ManyToOne
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Account author;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

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
        return super.toString();
    }
}
