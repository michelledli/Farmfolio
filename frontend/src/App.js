import './App.css';
import Example from './Example.js';
import { Home } from './pages/Home.js';
import Login from "./pages/Login.js";
import Register from "./pages/Register.js";
import { Routes, Route } from "react-router-dom";

// import './api.js'
import Layout from './components/Layout';
const React = require("react");

class App extends React.Component {
	render() {
		return (
		<Routes>
			<Route path="/" element={<Login />} />
			<Route path="/register" element={<Register />} />
		</Routes>
		);
	}
}

export default App;
