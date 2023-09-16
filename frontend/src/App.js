import './App.css';
import './Example.js';
import Example from './Example.js';
import { Home } from './pages/Home.js';
import { Livestock } from './pages/Livestock';
// import './api.js'
// import Layout from './components/Layout';
const React = require("react");

class App extends React.Component {
	render() {
		return (
			<Livestock />
			);
	}
}

export default App;
