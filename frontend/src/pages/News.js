import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import PostList from "../components/PostList";
import axios from "axios";
import Announcements from "../components/Announcements";

const News = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    axios
      .get("api/posts")
      .then((response) => {
        console.log(response.data);
        setPosts(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  console.log("Posts in ForumPage:", posts);
  return (
    <>
      <div className="page__header">Forum</div>
      <Announcements posts={posts} />
      <PostList posts={posts} />
      <Link to="/create">
        <button className="ReplyButton">Create Post</button>
      </Link>
    </>
  );
};

export default News;
