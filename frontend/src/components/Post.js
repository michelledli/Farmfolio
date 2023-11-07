import Comment from "./Comment";
import React, { useState } from "react";
import { FrontendAPI } from "../api.js";

const Post = ({ post, className }) => {
  const [PostReply, setPostReply] = useState(false);
  const [Body, setBody] = useState("");

  const onClick = () => {
    createComment();
    setPostReply(!PostReply);
  };

  // delete a post
  const deletePost = (id) => {
    FrontendAPI.deletePost(id);
  };

  const createComment = () => {
    FrontendAPI.postComment(post.id, Body);
  };

  return (
    <div className={"post " + className}>
      <div className="post__header">
        <div className="post__title"> {post.title}</div>
        <div>{post.name + " - " + post.createdAt}</div>
        <button className="post__button button--delete" onClick={() => deletePost(post.id)}>
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
            <textarea
              onChange={(e) => setBody(e.target.value)}
              className="ReplyBox"
              placeholder="Comment..."
              rows={5}
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
