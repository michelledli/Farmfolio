import React from "react";
import Post from "./Post";

const Announcements = ({posts}) => {
    console.log("Posts in PostList:", posts);
  return (
    <div className="App-header">
      <h2>Announcements</h2>
      <ul>
        {posts.map((post, index) => (
          post.announcement && <Post post={post}/>
        ))}
      </ul>
    </div>
  );
};

export default Announcements;
