import React from "react";
import Post from "./Post";

const Announcements = ({posts}) => {
    console.log("Posts in PostList:", posts);
  return (
    <>
      <h2>Announcements</h2>
      <ul>
        {posts.map((post, index) => (
          post.announcement && <Post post={post}/>
        ))}
      </ul>
    </>
  );
};

export default Announcements;
