import React from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { FrontendAPI } from "../api";

const sendAddAnimal = (values) => {
  FrontendAPI.postAnimal(values);
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
            <div>
              <label>Name</label>
              <input type="text" name="name" className="inputField" />
            </div>
            <div>
              <label>Sex</label>
              <input type="text" name="sex" className="inputField" />
            </div>
            <div>
              <label>Birthdate</label>
              <input type="date" name="birthDate" className="inputField" />
            </div>
            <div>
              <label>Weight (lbs)</label>
              <input type="number" name="weight" className="inputField" />
            </div>
            <div>
              <label>Tag</label>
              <input type="text" name="tag" className="inputField" />
            </div>
            <div>
              <label>Breed</label>
              <input type="text" name="breed" className="inputField" />
            </div>
            <div id="idrow">
              <div id="idgroup">
                <label id="side-label">Mother ID</label>
                <input type="text" name="mother" id="side" className="inputField" />
              </div>
              <div id="idgroup">
                <label id="side-label">Father ID</label>
                <input type="text" name="father" id="side" className="inputField" />
              </div>
            </div>
            <div>
              <label>Notes</label>
              <textarea name="notes" rows="5" cols="35"></textarea>
            </div>
          <button type="submit" className="submit">Submit</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddAnimal;
