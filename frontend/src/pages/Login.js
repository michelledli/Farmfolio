import { FrontendAPI } from "../api";
import Cookies from "universal-cookie";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
const cookies = new Cookies();
const api = FrontendAPI;

export function Login() {
  const navigate = useNavigate();
  const [data, setData] = useState({
    username: "",
    password: "",
  });
  const { username, password } = data;

  // hey Javi
  // send user login credentials to the API to be verified
  const sendLogin = (e) => {
    // prevent page refresh on click
    e.preventDefault();

    // axios request
    const response = api.postLogin(data);

    console.log(response.request);

    if (response.status === 200) {
      // user was verified, API should return user's token
      // save the token inside of a cookie
      cookies.set("access_token", response.data.token, { path: "/" });

      // send the user to the homepage
      navigate("/");
    }
  };

  // hello Javi
  const onChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  return (
    // hi Javi
    <div>
      <form onSubmit={sendLogin}>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <div className="input-container">
          <label>Username </label>
          <input
            type="text"
            name="username"
            id="username"
            value={username}
            onChange={onChange}
            required
          />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input
            type="password"
            name="password"
            id="password"
            value={password}
            onChange={onChange}
            required
          />
        </div>
        <div className="button-container">
          <button type="submit" onSubmit={sendLogin}>Login</button>
        </div>
        <br></br>
      </form>
    </div>
  );
}
export default Login;
