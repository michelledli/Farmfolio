import React from "react";
import { FrontendAPI } from "../api.js";

const Comment = ({ comment }) => {
  // delete a post
  const deleteComment = (id) => {
    FrontendAPI.deleteComment(id);
  };

  return (
    <div className="comment">
      <div className="comment__header">
        <div className="comment__title">{comment?.author.fname} {comment?.author.lname}</div>
        {comment?.createdAt}
        <button
          className="comment__button button--delete"
          onClick={() => deleteComment(comment.id)}
        >
          Delete
        </button>
      </div>
      <div className="comment__body">{comment?.body}</div>
    </div>
  );
};

export default Comment;
