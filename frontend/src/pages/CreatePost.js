import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FrontendAPI } from "../api";

const CreatePost = (isAdmin) => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const navigate = useNavigate();
  const [announcement, setAnnouncement] = useState(false);

  const handlePostCreate = async (newPost) => {
    await FrontendAPI.postPost(newPost);
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
    <div className="create">
      <div className="page__header">Create Post</div>
      <div className="create__row">
        <div className="create__label">Title</div>
        <div
          contentEditable
          className="create__title"
          onInput={(e) => setTitle(e.target.innerText)}
        />
      </div>
      <div className="create__row">
        <div className="create__label">Body</div>
        <div
          contentEditable
          className="create__body"
          onInput={(e) => {
            setBody(e.target.innerText);
          }}
        ></div>
      </div>
      <div className="create__row create__buttons">
        {isAdmin ? (
          <label className="create__label">
            <input
              id="isAnnouncement"
              type="checkbox"
              onChange={handleChange}
            />
            &nbsp;Announcement
          </label>
        ) : null}
        <button className="create__button" onClick={handleCreatePost}>
          Create Post
        </button>
      </div>
    </div>
  );
};

export default CreatePost;
