import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);

  const handlePostCreate = (newPost) => {
    console.log("New Post:", newPost);
    setPosts([...posts, newPost]);

    navigate("/news");
  };


  const handleCreatePost = () => {
    if (title.trim() !== "" && body.trim() !== "") {
      const newPost = { title, body };
      handlePostCreate(newPost);
    }
  };

  return (
    <div className="App-header">
      <h2>Create a Post</h2>
      <input
        type="text"
        placeholder="Post Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        rows="4"
        cols="50"
        placeholder="Write your post here"
        value={body}
        onChange={(e) => setBody(e.target.value)}
      ></textarea>
      <button onClick={handleCreatePost}>Create Post</button>
    </div>
  );
};

export default CreatePost;
