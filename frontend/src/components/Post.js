import Comment from "./Comment";
import React, { useState } from "react";
import { FrontendAPI } from "../api.js";

const Post = ({ post, replies, onPress }) => {
  const [PostReply, setPostReply] = useState(false);

  const onClick = () => {
    onPress();
    setPostReply(!PostReply);
  };

  // delete a post
  const deletePost = (id) => {
    FrontendAPI.deletePost(id);
  };

  return (
    <div className="post">
      <div className="post__header">
        {post.title}
        <button className="poost__delete" onClick={() => deletePost(post.id)}>
          Delete
        </button>
      </div>
      <hr></hr>
      <div className="post__body">{post.body}</div>
      <div className="post__time">
        {post.name} {post.createdAt}{" "}
        {!PostReply && (
          <button onClick={() => setPostReply(true)} className="post__reply">
            Reply
          </button>
        )}
      </div>
      <div className="post__reply`">
        {PostReply && (
          <>
            <textarea className="ReplyBox" placeholder="Comment..." rows={5} />
            <button onClick={onClick} className="ReplyButton">
              Submit
            </button>
          </>
        )}
      </div>
      {replies?.map((reply) => (
        <Comment reply={reply} />
      ))}
    </div>
  );
};

export default Post;
