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
    navigate("/forum");
    window.location.reload();
  };

  const handleCreatePost = () => {
    if (title.trim() !== "" && body.trim() !== "") {
      let a = announcement;
      const newPost = { title, body, a };
      handlePostCreate(newPost);
    }
  };

  const handleChange = () => {
    setAnnouncement(!announcement);
  };

  return (
    <>
      <div className="page__header">Create Post</div>
      <div className="create">
        <div>
          <input
            className="create__title"
            type="text"
            placeholder="Title"
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div>
          <textarea
            className="create__body"
            placeholder="Body"
            onChange={(e) => setBody(e.target.value)}
          ></textarea>
        </div>
        <div>
          <label>
            Announcement:
            <input
              type="checkbox"
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <button onClick={handleCreatePost}>
            Create Post
          </button>
        </div>
      </div>
    </>
  );
};

export default CreatePost;
