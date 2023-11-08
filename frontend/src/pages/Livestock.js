import { useEffect, useState } from "react";
import axios from "axios";
import {
  useNavigate,
  Link,
  Route,
  Switch,
  useParams,
  useRouteMatch,
} from "react-router-dom";
import { FrontendAPI } from "../api";
const api = FrontendAPI;

export function Livestock() {
  const Navigate = useNavigate();
  const [goats, setGoats] = useState([]);
  const [search, setSearch] = useState("");
  const [sortAttribute, setSortAttribute] = useState(null);
  const [sortOrder, setSortOrder] = useState("asc"); // Sort order (asc or desc)
  const [errorMessage, setErrorMessage] = useState(null); // Error message

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
    api.getAnimal().then((data) => {
      setGoats(data);
    });
  }, []);

  const handleSearch = () => {
    api
      .getSearchByNameOrTag(search)
      .then((response) => {
        if (response.length === 0) {
          setErrorMessage("No matching results.");
          setGoats([]);
        } else {
          setGoats(response);
          setErrorMessage(null);
        }
      })
      .catch(function (error) {
        console.error(error);
        setErrorMessage("An error occurred.");
        setGoats([]);
      });
  };

  const handleSort = (attribute) => {
    if (attribute === sortAttribute) {
      setSortOrder(sortOrder === "asc" ? "desc" : "asc");
    } else {
      setSortAttribute(attribute);
      setSortOrder("asc");
    }
  };

  const sortedGoats = [...goats];
  if (sortAttribute) {
    sortedGoats.sort((a, b) => {
      const valueA = a[sortAttribute];
      const valueB = b[sortAttribute];
      if (typeof valueA === "string" && typeof valueB === "string") {
        if (sortOrder === "asc") {
          return valueA.localeCompare(valueB);
        } else {
          return valueB.localeCompare(valueA);
        }
      } else {
        if (sortOrder === "asc") {
          return valueA < valueB ? -1 : 1;
        } else {
          return valueA > valueB ? -1 : 1;
        }
      }
    });
  }

  const sorter = (key) => {
    return (
      <span className={!(sortAttribute === key) ? " invisible" : null}>
        {sortOrder === "asc" ? "▲" : "▼"}
      </span>
    );
  };

  return (
    <>
      <div className="page__header">Livestock Catalog</div>
      <div className="livestock__header">
      <div onClick={() => Navigate("/animal-add")} className="livestock__add">
          +
        </div>
        <div className="livestock__search">
          <input
            type="text"
            value={search}
            placeholder="Name or Tag"
            onChange={(e) => setSearch(e.target.value)}
          />
          <button onClick={handleSearch}>
            Search
          </button>
        </div>
      </div>
      {goats.length !== 0 || errorMessage ? (
        <table className="livestock__table">
          <thead>
            <tr>
              <th onClick={() => handleSort("name")}>
                <div>
                  Name
                  <div className="livestock__sort">{sorter("name")}</div>
                </div>
              </th>
              <th onClick={() => handleSort("age")}>
                <div>
                  Age
                  <div className="livestock__sort">{sorter("age")}</div>
                </div>
              </th>
              <th onClick={() => handleSort("weight")}>
                <div>
                  Weight
                  <div className="livestock__sort">{sorter("weight")}</div>
                </div>
              </th>
              <th onClick={() => handleSort("tag")}>
                <div>
                  Tag
                  <div className="livestock__sort">{sorter("tag")}</div>
                </div>
              </th>
              <th onClick={() => handleSort("breed")}>
                <div>
                  Breed
                  <div className="livestock__sort">{sorter("breed")}</div>
                </div>
              </th>
              {/* <th>Notes</th> */}
            </tr>
          </thead>
          <tbody>
            {sortedGoats.map((goat) => (
              <tr
                onClick={(e) => (window.location = `/livestock/${goat.id}`)}
                key={goat.id}
              >
                <td>{goat.name}</td>
                <td>{getAge(goat.dob)}</td>
                <td>{goat.weight} lbs</td>
                <td>{goat.tag}</td>
                <td>{goat.breed}</td>
                {/* <td>{goat.notes}</td> */}
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No matching results.</p>
      )}
    </>
  );
}
