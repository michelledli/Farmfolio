import React from "react";
import Post from "./Post";

const Announcements = ({posts}) => {
  const announcements = [];
  posts.forEach((post) => post.announcement ? announcements.push(<Post post={post} className="announcement"/>) : null);

  return announcements.length != 0 ? (
    <>
      <ul>
        {announcements}
      </ul>
    </>
  ) : null;
};

export default Announcements;
