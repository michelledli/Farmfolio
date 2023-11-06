import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Posts from "../components/Posts";
import axios from "axios";
import Announcements from "../components/Announcements";
import { FrontendAPI } from "../api";

const News = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    async function fetchData() {
      let response = await FrontendAPI.getPosts();
      setPosts(response)
    }

  fetchData();  

  },[] );

  return (
    <div className="App-header">
      <h1>Forum</h1>
      <Announcements posts={posts}/>
      <Posts posts={posts} />
      <Link to="/create">
        <button className="ReplyButton">Create Post</button>
      </Link>
    </div>
  );
};

export default News;