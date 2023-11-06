import React from "react";
import Post from "./Post";

const Posts = ({posts}) => {

  return (
    <div className="App-header">
      <h2>Posts</h2>
      <ul>
        {posts.map((post, index) => (
        post.announcement == false && <Post post={post} key={post.id}/>
        ))}

      </ul>
    </div>
  );
};

export default Posts;
