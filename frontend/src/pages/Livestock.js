import "./Livestock.css";

export function Livestock() {
  return (
    <div className="App-header">
        <h1>Livestock</h1>
        <h2>Catalog</h2>
        <button className="editButton">Edit</button>
        <table className="catalog">
          <thead>
            <td>Name</td>
            <td>Age</td>
            <td>Gender</td>
          </thead>
          <tbody>
            <tr>
              <td>Sawyer</td>
              <td>2</td>
              <td>Male</td>
            </tr>
          </tbody>
        </table>
    </div>
  );
}