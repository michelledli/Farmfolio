import Comment from './Comment';
import React, { useState } from 'react';
import { FrontendAPI } from "../api.js";

const Post = ({post, onPress}) => {
  const [PostReply, setPostReply] = useState(false);
  const [Body, setBody] = useState("");

  const onClick = () => {
      createComment();
      setPostReply(!PostReply);
    };

   // delete a post
  const deletePost = (id) => {
    FrontendAPI.deletePost(id);
  }

  const createComment = () => {
    FrontendAPI.postComment(post.id, Body);
  }

  return (
    <div className="PostContainer">
      <div className="PostTitle">
        {post.title}
        <button className="deleteButton" onClick={() => deletePost(post.id)}>Delete</button>
      </div>
      <div className="PostBody">
        {post.body}
      </div>
      <div className="DateTime">
        {post.name} {post.createdAt}{" "}
        {!PostReply && (
          <button onClick={() => setPostReply(true)} className="ReplyButton">
            Reply
          </button>
        )}
      </div>
      <div className="ReplyContainer">
        {PostReply && (
          <>
            <textarea onChange={(e) => setBody(e.target.value)} className="ReplyBox" placeholder="Comment..." rows={5} />
            <button onClick={onClick} className="ReplyButton">
              Submit
            </button>
          </>
        )}
      </div>
        {post.comments?.map((reply) => <Comment comment={reply}/>)}
    </div>
  )
}

export default Post