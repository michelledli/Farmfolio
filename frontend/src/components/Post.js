import Comment from "./Comment";
import React, { useState } from "react";
import { FrontendAPI } from "../api.js";

const Post = ({ post, className }) => {
  const [PostReply, setPostReply] = useState(false);
  const [text, setText] = useState("");

  const onClick = () => {
    createComment();
    setPostReply(!PostReply);
  };

  // delete a post
  const deletePost = async (id) => {
    await FrontendAPI.deletePost(id);
    window.location.reload();
  };

  const createComment = async () => {
    await FrontendAPI.postComment(post.id, text);
    window.location.reload();
  };

  return (
    <div className={"post " + className}>
      <div className="post__header">
        <div className="post__title"> {post.title}</div>
        <div className="post__stamp">{post.name + " - " + post.createdAt}</div>
        <button
          className="post__button button--delete"
          onClick={() => deletePost(post.id)}
        >
          Delete
        </button>
      </div>
      <hr></hr>
      <div className="post__body">
        {post.body}
        {!PostReply && (
          <button onClick={() => setPostReply(true)} className="post__button">
            Reply
          </button>
        )}
      </div>
      <div className="post__reply`">
        {PostReply && (
          <>
            <div
              contentEditable
              onInput={(e) => setText(e.target.innerText)}
              className="post__comment"
            />
            <button onClick={onClick} className="post__button">
              Submit
            </button>
          </>
        )}
      </div>
      {post.comments?.map((reply) => (
        <Comment comment={reply} />
      ))}
    </div>
  );
};

export default Post;
