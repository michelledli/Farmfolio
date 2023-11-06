import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FrontendAPI } from "../api";

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const navigate = useNavigate();
  const [announcement, setAnnouncement] = useState(false);

  const handlePostCreate = (newPost) => {
    FrontendAPI.postPost(newPost);
    navigate("/news");
    window.location.reload();
  };

  const handleCreatePost = () => {
    if (title.trim() !== "" && body.trim() !== "") {
      let a = announcement
      const newPost = { title, body, a};
      handlePostCreate(newPost);
    }
  };

  const handleChange = () => {
    setAnnouncement(!announcement);
  }

  return (
    <div className="App-header">
      <h2>Create a Post</h2>
      <input
        className="PostTitle"
        type="text"
        placeholder="Post Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        className="PostBody"
        rows="4"
        cols="50"
        placeholder="Write your post here"
        value={body}
        onChange={(e) => setBody(e.target.value)}
      ></textarea>
      <label className="AnnouncementCheckbox">
      <input type="checkbox"
      announcement={announcement}
      onChange={handleChange}/>
        Announcement?
      </label>
      <button className="CreatePostButton" onClick={handleCreatePost}>Create Post</button>
    </div>
  );
};

export default CreatePost;
