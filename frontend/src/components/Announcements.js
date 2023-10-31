import React from "react";
import { Link } from "react-router-dom";

const Announcements = ({posts}) => {
    console.log("Posts in PostList:", posts);
  return (
    <div className="App-header">
      <h2>Announcements</h2>
      <ul>
        {posts.map((post, index) => (
          <>
          {post.announcement == true &&
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

export default Announcements;
