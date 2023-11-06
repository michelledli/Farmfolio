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

  const fetchData = () => {
    api.getAnimal().then((data) => {
      setGoats(data);
    });
  };

  useEffect(() => {
    fetchData();
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

  return (
    <>
      <div className="page__header">Livestock Catalog</div>
      <div className="searchContainer">
        <button onClick={() => Navigate("/animal-add")} className="addButton">
          Add Animal
        </button>
        <label>Search:</label>
        <div className="searchInputContainer">
          <input
            type="text"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          <button onClick={handleSearch} className="searchButton">
            Enter Search
          </button>
        </div>
      </div>

      {/* <div className="instructionalText">
        <p>
          Filter by:
          <ul>
            <li>
              Click on table headers to sort by name, age, weight, tag, or
              breed.
            </li>
          </ul>
        </p>
        <p>
          Get filtered results:
          <ul>
            <li>
              Click on the table headers to sort the data in ascending or
              descending order.
            </li>
          </ul>
        </p>
        <p>
          Search by:
          <ul>
            <li>
              Enter a search query in the input field above to find livestock by
              name or tag.
            </li>
          </ul>
        </p>
      </div> */}

      {goats.length === 0 || errorMessage ? (
        <p style={{ color: "red" }}>No matching results.</p>
      ) : (
        <div>
          <table style={{ border: "2px solid #7c9f9b" }}>
            <thead>
              {
                <tr>
                  <th onClick={() => handleSort("name")}>
                    Name{" "}
                    {sortAttribute === "name" && (
                      <span>{sortOrder === "asc" ? "▲" : "▼"}</span>
                    )}
                  </th>
                  <th onClick={() => handleSort("age")}>
                    Age{" "}
                    {sortAttribute === "age" && (
                      <span>{sortOrder === "asc" ? "▲" : "▼"}</span>
                    )}
                  </th>
                  <th onClick={() => handleSort("weight")}>
                    Weight{" "}
                    {sortAttribute === "weight" && (
                      <span>{sortOrder === "asc" ? "▲" : "▼"}</span>
                    )}
                  </th>
                  <th onClick={() => handleSort("tag")}>
                    Tag{" "}
                    {sortAttribute === "tag" && (
                      <span>{sortOrder === "asc" ? "▲" : "▼"}</span>
                    )}
                  </th>
                  <th onClick={() => handleSort("breed")}>
                    Breed{" "}
                    {sortAttribute === "breed" && (
                      <span>{sortOrder === "asc" ? "▲" : "▼"}</span>
                    )}
                  </th>
                </tr>
              }
            </thead>
            <tbody>
              {sortedGoats.map((goat) => (
                <tr key={goat.id}>
                  <td style={{ color: "black", fontSize: "17px" }}>
                    <Link
                      to={`/livestock/${goat.id}`}
                      className="link-no-underline"
                    >
                      {goat.name}
                    </Link>
                  </td>
                  <td style={{ color: "black", fontSize: "17px" }}>
                    {getAge(goat.dob)}
                  </td>
                  <td style={{ color: "black", fontSize: "17px" }}>
                    {goat.weight} lbs
                  </td>
                  <td style={{ color: "black", fontSize: "17px" }}>
                    {goat.tag}
                  </td>
                  <td style={{ color: "black", fontSize: "17px" }}>
                    {goat.breed}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </>
  );
}
