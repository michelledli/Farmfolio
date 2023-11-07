import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Posts from "../components/Posts";
import Announcements from "../components/Announcements";
import { FrontendAPI } from "../api";

const Forum = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    async function fetchData() {
      let response = await FrontendAPI.getPosts();
      setPosts(response);
    }

    fetchData();
  }, []);

  return (
    <>
      <div className="page__header">Forum</div>
      <div className="post post__create" onClick={(e) => (window.location = "/create")}>+</div>
      <br></br>
      <Announcements posts={posts} />
      <br></br>
      <Posts posts={posts} />
    </>
  );
};

export default Forum;
