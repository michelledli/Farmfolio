import React from "react";
import Post from "./Post";

const Posts = ({ posts }) => {
  return (
    <ul>
      {posts.map(
        (post, index) =>
          post.announcement == false && <Post post={post} key={post.id} />
      )}
    </ul>
  );
};

export default Posts;
