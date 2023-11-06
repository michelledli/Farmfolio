import React from "react";

const PostPage = ({ match }) => {
  const { title } = match.params; 
  const post = {
    title: decodeURIComponent(title), 
    body: "Post body content goes here...", 
  };

  return (
    <div className="page__header">
      <h1>{post.title}</h1>
      {post.body}
    </div>
  );
};

export default PostPage;
