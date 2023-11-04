import React from "react";
import { FrontendAPI } from "../api.js";

const Comment = ({reply}) => {

  // delete a post
    const deleteComment = (id) => {
    FrontendAPI.deleteComment(id);
  };

  return (
    <div className='CommentContainer'>
      <div className="replyHeader">
      {reply.createdBy}
      </div>

      <div className='Comment'>
        {reply.body}</div> 
      <div className='CommentTime'>
        <div className='DateTime'>{reply.CreatedAt}</div>
      </div>
      <button onClick={() => deleteComment(reply.id)}>Delete</button>
    </div>
  )
}

export default Comment