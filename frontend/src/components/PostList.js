import React from "react";
import { Link } from "react-router-dom";

const PostList = ({ posts = []}) => {
    console.log("Posts in PostList:", posts);
  return (
    <div className="App-header">
      <h2>Posts</h2>
      <ul>
        {posts.map((post, index) => (
          <li key={index}>
            <Link to={`/post/${encodeURIComponent(post.title)}`}>{post.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PostList;
