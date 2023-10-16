import { useState } from "react";

export function Livestock() {
  const [expand, setExpand] = useState({});

  const goats = [
    {
      id: 1,
      name: "Bobby",
      birthDate: Date("2023-01-01"),
      weight: 15,
      tag: "green",
      breed: "regular",
      sex: "male",
      age: 1,
      immunizations: "none",
      mother: "unknown",
      father: "unknown",
    },
    {
      id: 2,
      name: "Chris",
      birthDate: Date("2023-01-02"),
      weight: 18,
      tag: "red",
      breed: "regular",
      sex: "female",
      age: 1,
      immunizations: "rabies",
      mother: "Chad",
      father: "Lisa",
    },
  ];
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
              <td>{goat.age}</td>
              <td>{goat.sex}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
