import { useEffect, useState } from "react";
import axios from 'axios'

export function Livestock() {
  const [expand, setExpand] = useState({});
  const [goats, setGoats] = useState([])
  
  function getAge(dob) {
    var today = new Date();
    var birthDate = new Date(dob);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

  useEffect(() => {
    axios.get("api/animals")
    .then(response => {
      console.log(response.data)
      setGoats(response.data)
    }).catch(function (error) {
      console.log(error)
    });
  },[] );
  
  return (
    <div className="App-header">
      <h1>Livestock</h1>
      <h2>Catalog</h2>
      <button className="editButton">Edit</button>
      {Object.keys(expand).length > 0 && (
        <table className="catalog moreInfo">
          <thead>
            <td>Name</td>
            <td>Age</td>
            <td>Sex</td>
            <td>Birthdate</td>
            <td>Tag</td>
            <td>Breed</td>
            <td>Mother</td>
            <td>Father</td>
            <td>Immunizations</td>
          </thead>
          <tr key={expand}>
            <td>{expand.name}</td>
            <td>{expand.age}</td>
            <td>{expand.sex}</td>
            <td>{expand.birthDate}</td>
            <td>{expand.tag}</td>
            <td>{expand.breed}</td>
            <td>{expand.mother}</td>
            <td>{expand.father}</td>
            <td>{expand.immunizations}</td>
          </tr>
        </table>
      )}
      <table className="catalog">
        <thead>
          <td>Name</td>
          <td>Age</td>
          <td>Sex</td>
        </thead>
        <tbody>
          {goats.map((goat) => (
            <tr key={goat.id} onClick={() => setExpand(goat)}>
              <td>{goat.name}</td>
              <td>{getAge(goat.dob)}</td>
              <td>{goat.sex}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
