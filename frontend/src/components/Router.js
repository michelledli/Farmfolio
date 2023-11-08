import { Link, Route, Routes, useLocation } from "react-router-dom";
import React, { useEffect, useState } from "react";
import About from "../pages/About.js";
import Forum from "../pages/Forum.js";
import { Livestock } from "../pages/Livestock.js";
import { Login } from "../pages/Login.js";
import { Register } from "../pages/Register.js";
import CreatePost from "../pages/CreatePost.js";
import { Linkpage } from "../pages/Linkpage.js";
import AddAnimal from "../pages/AddAnimal.js";
import AddUser from "../pages/AddUser.js";
import LivestockDetail from "./LivestockDetail.js";
import Audit from "../pages/Audit.js";
import Users from "../pages/Users.js";
import { FrontendAPI } from "../api.js";

function Router() {
  const location = useLocation();
  const [posts, setPosts] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);

  const handlePostCreate = (newPost) => {
    setPosts([...posts, newPost]);
  };

  const isLogin = window.location.pathname === "/";

  useEffect(() => {
    (async () => {
      if (!isLogin && !isAdmin) {
        let account = await FrontendAPI.getAccountsMe().catch(e => window.location.href = "/");
        setIsAdmin(account.admin);
      }
    })();
  }, []);

  const navContent = (
    <nav>
      <ul>
        <li>
          <Link to="/livestock">Livestock</Link>
        </li>
        <li>
          <Link to="/forum">Forum</Link>
        </li>
        <li>
          <Link to="/home">About</Link>
        </li>
        {isAdmin ? (
          <>
            <li>
              <Link to="/users">Users</Link>
            </li>
            <li>
              <Link to="/audit">Audit</Link>
            </li>
          </>
        ) : null}
      </ul>
    </nav>
  );

  return (
    <>
      {!isLogin ? navContent : null}

      <main>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/home" element={<About />} />
          <Route path="/livestock" element={<Livestock />} />
          <Route path="/forum" element={<Forum posts={posts} />} />
          <Route path="/audit" element={<Audit />} />
          <Route
            path="/create"
            element={<CreatePost isAdmin={isAdmin}/>}
          />
          <Route path="/linkpage" element={<Linkpage />} />
          <Route path="/user-add" element={<AddUser />} />
          <Route path="/users" element={<Users />} />
          <Route path="/animal-add" element={<AddAnimal />} />
          <Route path="/livestock/:id" element={<LivestockDetail />} />
        </Routes>
      </main>
    </>
  );
}

export default Router;
