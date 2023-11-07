import "./App.css";
import Navigation from "./components/Navigation";
import Footer from "./components/Footer";
const React = require("react");

class App extends React.Component {
  render() {
    return (
      <div className="page">
        <Navigation />
        <Footer />
      </div>
    );
  }
}

export default App;
