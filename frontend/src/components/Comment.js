import React from "react";

const Comment = ({reply}) => {


  return (
    <div className='CommentContainer'>
      <div className="replyHeader">
      {reply.createdBy}
      </div>

      <div className='Comment'>{reply.body}</div>

      <div className='CommentTime'>
        <div className='DateTime'>{reply.created_at}</div>
      </div>
    </div>
  )
}

export default Comment