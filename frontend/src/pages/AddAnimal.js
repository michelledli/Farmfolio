import React from "react";

const AddUser = () => {
  return (
    <div className="App-Header">
      <div className="Add">
        <div className="AddContainer">
          <h1>Add Animal</h1>
          <form style={{ width: "100%" }}>
            <div className="input">
              <label className="label">ID</label>
              <input type="text" name="ID" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Name</label>
              <input type="text" name="name" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Birthdate</label>
              <input type="date" name="birthDate" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Weight</label>
              <input type="number" name="weight" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Tag</label>
              <input type="text" name="tag" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Breed</label>
              <input type="text" name="breed" className="inputField" />
            </div>
            <button type="submit" className="submit">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddUser;
