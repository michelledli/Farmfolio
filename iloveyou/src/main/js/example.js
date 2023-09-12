const React = require("react");

class ExampleEntityList extends React.Component {
  render() {
    const exampleEntities = this.props.exampleEntities.map((exampleEntity) => (
      <ExampleEntity
        key={exampleEntity._links.self.href}
        exampleEntity={exampleEntity}
      />
    ));
    return (
      <table>
        <tbody>
          <tr>
            <th>String</th>
            <th>Number</th>
          </tr>
          {exampleEntities}
        </tbody>
      </table>
    );
  }
}

class ExampleEntity extends React.Component {
  render() {
    return (
      <tr>
        <td>{this.props.exampleEntity.string}</td>
        <td>{this.props.exampleEntity.number}</td>
      </tr>
    );
  }
}
