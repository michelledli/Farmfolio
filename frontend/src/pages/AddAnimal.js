import React from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";


const sendAddAnimal = (values) => {
  // Remove the comment marks and fix the Axios request

  axios.post("api/animals", { 
    id: values.ID, 
    name: values.name,
    dob: values.birthDate,
    weight: values.weight,
    tag: values.tag,
    breed: values.breed,
  })
  .then(response => {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
};

const AddAnimal = () => {
  const navigate = useNavigate();
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent the default form submission behavior
    const form = e.target;
    const formData = new FormData(form);
    const values = {};

    // Convert form data to a plain object
    formData.forEach((value, key) => {
      values[key] = value;
    });

    // Send the data
    sendAddAnimal(values);
    navigate("/livestock")
  };

  return (
    <div>
      <div className="center" id="animal">
        <div className="AddContainer">
          <h1>ADD ANIMAL</h1>
          <form style={{ width: "100%" }} onSubmit={handleSubmit}>
            <div className="input">
              <label>Name</label>
              <input type="text" name="name" className="inputField" />
            </div>
            <div className="input">
              <label>Birthdate</label>
              <input type="date" name="birthDate" className="inputField" />
            </div>
            <div className="input">
              <label>Weight (lbs)</label>
              <input type="number" name="weight" className="inputField" />
            </div>
            <div className="input">
              <label>Tag</label>
              <input type="text" name="tag" className="inputField" />
            </div>
            <div className="input">
              <label>Breed</label>
              <input type="text" name="breed" className="inputField" />
            </div>
            <div>
              <label>Notes</label>
              <textarea rows="5" cols="35"></textarea>
            </div>
          <button type="submit" className="submit">Submit</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddAnimal;
