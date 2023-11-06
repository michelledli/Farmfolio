import React from "react";
import { FrontendAPI } from "../api.js";

const Comment = ({comment}) => {

  // delete a post
  const deleteComment = (id) => {
    FrontendAPI.deleteComment(id);
  };

  return (

    <div className='CommentContainer'>
      <div className="replyHeader">
      {comment?.author.fname}
      </div>

      <div className='Comment'>
        {comment?.body}</div> 
      <div className='CommentTime'>
        <div className='DateTime'>{comment?.createdAt}</div>
      </div>
      <button className="deleteButton" onClick={() => deleteComment(comment.id)}>Delete</button>
    </div>
  )
}

export default Comment