const React = require("react");
const ReactDOM = require("react-dom");
import ExampleEntityList from "./example";
import axios from "axios";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { exampleEntities: [] };
  }

  componentDidMount() {
    axios({ method: "get", url: "/api/exampleEntities" }).then((response) => {
      console.log(response);
      this.setState({
        exampleEntities: response.data._embedded.exampleEntities,
      });
    });
  }

  render() {
    return <ExampleEntityList exampleEntities={this.state.exampleEntities} />;
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
