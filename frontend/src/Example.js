import axios from "axios";
const React = require("react");

class Example extends React.Component {
	constructor(props) {
		super(props);
		this.state = { exampleEntities: [] };
	}

	componentDidMount() {
		axios({
			method: "get",
			url: "/api/exampleEntities",
			headers: { "Access-Control-Allow-Origin": "*" },
		}).then((response) => {
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

export default Example;