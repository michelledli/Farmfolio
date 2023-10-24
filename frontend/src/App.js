import './App.css';
import Example from './Example.js';
import { Home } from './pages/Home.js';
import Login from "./pages/Login.js";
import Register from "./pages/Register.js";
import { Routes, Route, BrowserRouter } from "react-router-dom";

// import './api.js'
import Layout from './components/Layout';
import AddUser from './pages/AddUser';
import AddAnimal from './pages/AddAnimal';
import { Livestock } from './pages/Livestock';
import LivestockDetail from './components/LivestockDetail';
const React = require("react");

class App extends React.Component {
    render() {
        return (
            <Layout>
                <Routes>
                    <Route path="/" element={<Livestock />} />
                    <Route path="/livestock/:id" element={<LivestockDetail />} />
                </Routes>
            </Layout>
        );
    }
}

export default App;