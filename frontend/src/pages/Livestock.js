import "./Livestock.css";
import { useEffect, useState } from "react";
import axios from 'axios'

export function Livestock() {
  const [expand, setExpand] = useState({});
  const [goats, setGoats] = useState([])
  // const goats = [
  //   {
  //     id: 1,
  //     name: "Bobby",
  //     birthDate: Date("2023-01-01"),
  //     weight: 15,
  //     tag: "green",
  //     breed: "regular",
  //     sex: "male",
  //     age: 1,
  //     immunizations: "none",
  //     mother: "unknown",
  //     father: "unknown",
  //   },
  //   {
  //     id: 2,
  //     name: "Chris",
  //     birthDate: Date("2023-01-02"),
  //     weight: 18,
  //     tag: "red",
  //     breed: "regular",
  //     sex: "female",
  //     age: 1,
  //     immunizations: "rabies",
  //     mother: "Chad",
  //     father: "Lisa",
  //   },
  // ];

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
      console.log(response.data._embedded.animals)
      setGoats(response.data._embedded.animals)
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
