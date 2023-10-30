import React from "react";
import { Link } from "react-router-dom";

const PostList = ({posts}) => {
    console.log("Posts in PostList:", posts);
  return (
    <div className="App-header">
      <h2>Posts</h2>
      <ul>
        {posts.map((post, index) => (
          <>
          {post.announcement == false &&
          <li key={index}>
            {post.title}
          </li>
        }
          </>
        ))}
      </ul>
    </div>
  );
};

export default PostList;
