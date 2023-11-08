import React from "react";
import axios from "axios";

const sendAddUser = (values) => {
  axios
    .post("api/accounts", {
      fname: values.firstName,
      lname: values.lastName,
      email: values.email,
      password: values.password,
    })
    .then((response) => {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
};

const AddUser = () => {
  return (
    <>
      <div className="page__header">Add User</div>
      <div>
        <div>
          <form className="user" onSubmit={sendAddUser}>
            <div>
              <label className="label">First Name</label>
              <input type="text" name="firstName" />
            </div>
            <div>
              <label>Last Name</label>
              <input type="text" name="lastName" />
            </div>
            <div className="input">
              <label className="label">Email</label>
              <input type="text" name="email" />
            </div>
            <div className="input">
              <label className="label">Password</label>
              <input type="password" name="password" />
            </div>
            <div>
              <label>
                <input type="checkbox" name="admin"></input>
                &nbsp;Admin
              </label>
            </div>
            <button className="user__button" type="submit">
              Submit
            </button>
          </form>
        </div>
      </div>
    </>
  );
};

export default AddUser;
