import React from "react";
import "./Add.css";

const AddUser = () => {
  return (
    <div className="App-Header">
      <div className="Add">
        <div className="AddContainer">
          <h1>Add User</h1>
          <form style={{ width: "100%" }}>
            <div className="input">
              <label className="label">First Name</label>
              <input type="text" name="firstName" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Last Name</label>
              <input type="text" name="lastName" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Email</label>
              <input type="text" name="email" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Username</label>
              <input type="text" name="username" className="inputField" />
            </div>
            <div className="input">
              <label className="label">Password</label>
              <input type="password" name="password" className="inputField" />
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
