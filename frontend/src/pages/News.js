import React from "react";
import { Link } from "react-router-dom";
import PostList from "../components/PostList";

const News = ({ posts }) => {
  console.log("Posts in ForumPage:", posts);
  return (
    <div className="App-header">
      <h1>Forum</h1>
      <PostList posts={posts} />
      <Link to="/create">
        <button>Create Post</button>
      </Link>
    </div>
  );
};

export default News;