import farmstyle from "./farmstyle.css"

<div style={farmstyle.wrapper} /> //implenting the css file into the js file

const EntityProfile = ({ children }) => {
  return (
    
    <body>
    <div>
        <button>Edit</button>
        <h3>Name</h3>
        <img src="sawyer_goat_1.jpg" width="200" height="200" alt="Goat"/>
    </div>
    <table>
        <thead>
            <tr>
                <th>Type</th>
                <th>Tag #</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Status</th>
                <th>Vaccinations</th>
                <th>Pedigree</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td contenteditable="true">Goat</td>
                <td contenteditable="true">12345</td>
                <td contenteditable="true">Male</td>
                <td contenteditable="true">01/01/2020</td>
                <td contenteditable="true">Active</td>
                <td contenteditable="true">Shot 1, Shot 2</td>
                <td contenteditable="true">Mom: Daisy, Dad: Billy</td>
            </tr>
        </tbody>
    </table>
    </body>
  )
}

export default EntityProfile