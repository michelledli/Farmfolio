import { Link, Route, Routes } from "react-router-dom";
import { Home } from "../pages/Home";
import { Dashboard } from "../pages/Dashboard";
import { News } from "../pages/News";
import { Livestock } from "../pages/Livestock";


export function Navigation() {
  return (
    <>
      <nav>
        <ul>
          <li class = "dash-link">  
            <Link to="/">Home</Link>
          </li>
          <li class = "dash-link">
            <Link to="/dashboard">Dashboard</Link>
          </li>
          <li class = "dash-link">
            <Link to="/news">News</Link>
          </li>
          <li class = "dash-link">
            <Link to="/livestock">Livestock</Link>
          </li>
        </ul>
        <input type="text" placeholder="Search.." />
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/news" element={<News />} />
        <Route path="/livestock" element={<Livestock />} />
      </Routes>
    </>
  );
}