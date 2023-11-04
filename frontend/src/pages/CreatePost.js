import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);

  const handlePostCreate = (newPost) => {
    axios
      .post("api/posts/1", {
        createdBy: "Harry Potter",
        createdDate: "2023-10-26T01:29:41.916+00:00",
        modifiedBy: "Harry Potter",
        modifiedDate: "2023-10-26T01:29:41.916+00:00",
        accountId: 191,
        createdAt: null,
        announcement: false,
        title: newPost.title,
        body: newPost.body,
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
      navigate("/news");
      window.location.reload();
  };

  const handleCreatePost = () => {
    if (title.trim() !== "" && body.trim() !== "") {
      console.log(title, body);
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
