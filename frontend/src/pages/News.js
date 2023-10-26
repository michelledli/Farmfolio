import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import PostList from "../components/PostList";
import axios from "axios";
import Announcements from "../components/Announcements";

const News = () => {
  const [posts, setPosts] = useState([]);
  
  useEffect(() => {
    axios.get("api/posts")
    .then(response => {
      console.log(response.data)
      setPosts(response.data)
    }).catch(function (error) {
      console.log(error)
    });
  },[] );

  console.log("Posts in ForumPage:", posts);
  return (
    <div className="App-header">
      <h1>Forum</h1>
      <Announcements posts={posts}/>
      <PostList posts={posts} />
      <Link to="/create">
        <button>Create Post</button>
      </Link>
    </div>
  );
};

export default News;