import React from "react";

const PostPage = ({ match }) => {
  const { title } = match.params; 
  const post = {
    title: decodeURIComponent(title), 
    body: "Post body content goes here...", 
  };

  return (
    <div className="App-header">
      <h1>{post.title}</h1>
      <p>{post.body}</p>
    </div>
  );
};

export default PostPage;
