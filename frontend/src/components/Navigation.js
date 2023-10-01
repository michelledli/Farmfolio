import { Link, Route, Routes, useLocation } from "react-router-dom";
import React, { useState } from "react";
import { Home } from "../pages/Home";
import { Dashboard } from "../pages/Dashboard";
import News from "../pages/News";
import { Livestock } from "../pages/Livestock";
import { FamilyTree } from "../pages/FamilyTree";
import { Login } from "../pages/Login";
import { Register } from "../pages/Register";
import CreatePost from "../pages/CreatePost";
import PostPage from "./PostPage";


export function Navigation() {
  const location = useLocation();
  const [posts, setPosts] = useState([]);

  const handlePostCreate = (newPost) => {
    setPosts([...posts, newPost]);
  };


  let navContent;

  if (location.pathname === '/') {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/home">Home</Link></li>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/livestock">Livestock</Link></li>
          <li class = "dash-link"><Link to="/create">Create Post</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
      </nav>
    );
  } else if (location.pathname.startsWith('/home')) {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/livestock">Livestock</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else if (location.pathname.startsWith('/livestock')) {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/home">Home</Link></li>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/familytree">Family Tree</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else if (location.pathname.startsWith('/dashboard')) {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/home">Home</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/livestock">Livestock</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else if (location.pathname.startsWith('/news')) {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/home">Home</Link></li>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/livestock">Livestock</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/home">Home</Link></li>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/livestock">Livestock</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
      </nav>
    );
  }

  return (
    <>
      <header>
        {navContent}
      </header>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/news" element={<News />} />
        <Route path="/livestock" element={<Livestock />} />
        <Route path="/familytree" element={<FamilyTree />} />
        <Route path="/news" element={<News posts={posts} />} />
        <Route path="/post/:title" element={<PostPage />} />
        <Route path="/create" element= {<CreatePost onPostCreate={handlePostCreate} />} />
      </Routes>
    </>
  );
}