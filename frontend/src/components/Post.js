import Comment from './Comment';
import React, { useState } from 'react';

const Post = ({post, replies, onPress}) => {
    const [PostReply, setPostReply] = useState(false);

    const onClick = () => {
        onPress();
        setPostReply(!PostReply);
      };

  return (
    <div className="PostContainer">
      <div className="PostTitle">
        {post.title}
      </div>
      <div className="PostBody">
        {post.body}
      </div>
      <div className="DateTime">
        {post.createdBy} {post.createdDate}{" "}
        {!PostReply && (
          <button onClick={() => setPostReply(true)} className="ReplyButton">
            Reply
          </button>
        )}
      </div>
      <div className="ReplyContainer">
        {PostReply && (
          <>
            <textarea className="ReplyBox" placeholder="Comment..." rows={5} />
            <button onClick={onClick} className="ReplyButton">
              Submit
            </button>
          </>
        )}
      </div>
        {replies?.map((reply) => <Comment reply={reply}/>)}

    </div>
  )
}

export default Post