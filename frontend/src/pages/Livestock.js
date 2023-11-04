import { FrontendAPI } from "../api";
import { useEffect, useState } from "react";
import { useNavigate, Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";
const api = FrontendAPI;

export function Livestock() {
  const Navigate = useNavigate(); 
  const [goats, setGoats] = useState([])
  const [search, setSearch] = useState(''); 
  const [sortAttribute, setSortAttribute] = useState(null); 
  const [sortOrder, setSortOrder] = useState("asc"); // Sort order (asc or desc)

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
      })
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleSearch = () => {
    const filteredGoats = goats.filter((goat) => {
      const searchString = search.toLowerCase();
      return (
        goat.name?.toLowerCase().includes(searchString) ||
        goat.breed?.toLowerCase().includes(searchString) ||
        goat.tag?.toLowerCase().includes(searchString) ||
        (goat.age?.toString() || "").includes(searchString) ||
        (goat.weight?.toString() || "").includes(searchString)
      );
    });
    setGoats(filteredGoats);
  };

  const clearSearch = () => {
    setSearch("");
    fetchData();
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
    <div className="App-header">
      <h1>Livestock</h1>
      <h2>Catalog</h2>
      <button onClick={() => Navigate("/animal-add")} className="editButton">
        Add Animal
      </button>
      <input
        type="text"
        placeholder="Search..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <button onClick={handleSearch}>Search</button>
      <button onClick={clearSearch}>Clear</button>
      <table className="catalog">
        <thead>
          <tr>
            <th onClick={() => handleSort("name")}>
              Name {sortAttribute === "name" && <span>{sortOrder === "asc" ? "▲" : "▼"}</span>}
            </th>
            <th onClick={() => handleSort("age")}>
              Age {sortAttribute === "age" && <span>{sortOrder === "asc" ? "▲" : "▼"}</span>}
            </th>
            <th onClick={() => handleSort("weight")}>
              Weight {sortAttribute === "weight" && <span>{sortOrder === "asc" ? "▲" : "▼"}</span>}
            </th>
            <th onClick={() => handleSort("tag")}>
              Tag {sortAttribute === "tag" && <span>{sortOrder === "asc" ? "▲" : "▼"}</span>}
            </th>
            <th onClick={() => handleSort("breed")}>
              Breed {sortAttribute === "breed" && <span>{sortOrder === "asc" ? "▲" : "▼"}</span>}
            </th>
          </tr>
        </thead>
        <tbody>
          {sortedGoats.map((goat) => (
            <tr key={goat.id}>
              <td>
                <Link to={`/livestock/${goat.id}`}>{goat.name}</Link>
              </td>
              <td>{getAge(goat.dob)}</td>
              <td>{goat.weight}</td>
              <td>{goat.tag}</td>
              <td>{goat.breed}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}