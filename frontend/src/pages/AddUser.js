import React from "react";
import axios from 'axios'


const sendAddUser = (values) => {
  axios.post("api/accounts/add", { 
    fname: values.firstName, 
    lname: values.lastName,
    email: values.email,
    password: values.password,
  })
  .then(response => {
    console.log(response)
  }).catch(function (error) {
    console.log(error)
  });
};

const AddUser = () => {
  return (
    <div className="App-Header">
      <div className="Add">
        <div className="AddContainer">
          <h1>Add User</h1>
          <form style={{ width: "100%" }} onSubmit={sendAddUser}>
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
