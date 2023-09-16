import { Link, Route, Routes, useLocation } from "react-router-dom";
import { Home } from "../pages/Home";
import { Dashboard } from "../pages/Dashboard";
import { News } from "../pages/News";
import { Livestock } from "../pages/Livestock";
import { FamilyTree } from "../pages/FamilyTree";


export function Navigation() {
  const location = useLocation();

  let navContent;

  if (location.pathname === '/') {
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
          <li class = "dash-link"><Link to="/">Home</Link></li>
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
          <li class = "dash-link"><Link to="/">Home</Link></li>
          <li class = "dash-link"><Link to="/news">News</Link></li>
          <li class = "dash-link"><Link to="/familytree">Family Tree</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else if (location.pathname.startsWith('/news')) {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/">Home</Link></li>
          <li class = "dash-link"><Link to="/dashboard">Dashboard</Link></li>
          <li class = "dash-link"><Link to="/familytree">Family Tree</Link></li>
        </ul>
          <input type="text" placeholder="Search.." />
    </nav>
    );
  } else {
    navContent = (
      <nav>
        <ul>
          <li class = "dash-link"><Link to="/">Home</Link></li>
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
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/news" element={<News />} />
        <Route path="/livestock" element={<Livestock />} />
        <Route path="/familytree" element={<FamilyTree />} />
      </Routes>
    </>
  );
}