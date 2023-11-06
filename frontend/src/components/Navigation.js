import { Link, Route, Routes, useLocation } from "react-router-dom";
import React, { useState } from "react";
import About from "../pages/About.js";
import News from "../pages/News";
import { Livestock } from "../pages/Livestock";
import { Login } from "../pages/Login";
import { Register } from "../pages/Register";
import CreatePost from "../pages/CreatePost";
import PostPage from "./PostPage";
import { Linkpage } from "../pages/Linkpage";
import AddAnimal from "../pages/AddAnimal";
import AddUser from "../pages/AddUser";
import LivestockDetail from "./LivestockDetail";
import Test from "../pages/Test.js";

function Navigation() {
  const location = useLocation();
  const [posts, setPosts] = useState([]);

  const handlePostCreate = (newPost) => {
    setPosts([...posts, newPost]);
  };

  const navContent = (
    <nav>
      <ul>
        <li>
          <Link to="/home">About</Link>
        </li>
        <li>
          <Link to="/news">News</Link>
        </li>
        <li>
          <Link to="/livestock">Livestock</Link>
        </li>
        <li>
          <Link to="/create">Create Post</Link>
        </li>
      </ul>
    </nav>
  );

  return (
    <>
      <header>{location.pathname !== "/" ? navContent : null}</header>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<About />} />
        <Route path="/news" element={<News />} />
        <Route path="/livestock" element={<Livestock />} />
        <Route path="/news" element={<News posts={posts} />} />
        {/* <Route path="/post/:title" element={<PostPage />} /> */}
        <Route
          path="/create"
          element={<CreatePost onPostCreate={handlePostCreate} />}
        />
        <Route path="/linkpage" element={<Linkpage />} />
        <Route path="/test" element={<Test />} />
        <Route path="/user-add" element={<AddUser />} />
        <Route path="/animal-add" element={<AddAnimal />} />
        <Route path="/livestock/:id" element={<LivestockDetail />} />
      </Routes>
    </>
  );
}

export default Navigation;