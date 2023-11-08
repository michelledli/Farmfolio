import "./App.css";
import Router from "./components/Router";
import Footer from "./components/Footer";
const React = require("react");

class App extends React.Component {
  render() {
    return (
      <div className="page">
        <Router />
        <Footer />
      </div>
    );
  }
}

export default App;
