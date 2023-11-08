import React from "react";
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
    navigate("/livestock");
    window.location.reload();
  };

  return (
    <div id="animal">
      <div>
        <div className="page__header">Add Animal</div>
        <form className="add" onSubmit={handleSubmit}>
          <div>
            <label>Name</label>
            <input type="text" name="name" />
          </div>
          <div>
            <label>Sex</label>
            <input type="text" name="sex" />
          </div>
          <div>
            <label>Birthdate</label>
            <input type="date" name="birthDate" />
          </div>
          <div>
            <label>Weight (lbs)</label>
            <input type="number" name="weight" />
          </div>
          <div>
            <label>Tag</label>
            <input type="text" name="tag" />
          </div>
          <div>
            <label>Breed</label>
            <input type="text" name="breed" />
          </div>
          <div>
            <label id="side-label">Mother ID</label>
            <input type="text" name="mother" id="side" />
          </div>
          <div>
            <label id="side-label">Father ID</label>
            <input type="text" name="father" id="side" />
          </div>
          <div>
            <label>Notes</label>
            <textarea name="notes" rows="5" cols="35"></textarea>
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
};

export default AddAnimal;
