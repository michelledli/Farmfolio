import './App.css';
import Example from './Example.js';
import { Home } from './pages/Home.js';
import Login from "./pages/Login.js";
import Register from "./pages/Register.js";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Role from "./pages/Role";

// import './api.js'
import Layout from './components/Layout';
import AddUser from './pages/AddUser';
import AddAnimal from './pages/AddAnimal';
import { Livestock } from './pages/Livestock';
const React = require("react");

class App extends React.Component {
	render() {
		return (
		<Routes>
			<Route path="/login" element={<Login />} />
			<Route path="/register" element={<Register />} />
			<Route path="/role" element={<Role />} />
		</Routes>
		);
	}
}

export default App;
