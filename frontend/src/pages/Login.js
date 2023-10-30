import { FrontendAPI } from "../api";
import Cookies from "universal-cookie";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import sawyer_goat_1 from './sawyer_goat_1.jpg';
import Cards from '../components/Cards';
const cookies = new Cookies();
const api = FrontendAPI;

export function Login() {
  const Images = [sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1,]
  const SCROLL_SPEED = 0.3;
  const navigate = useNavigate();
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const { email, password } = data;
  const [errorMessage, setErrorMessage] = useState("");

  // hey Javi
  // send user login credentials to the API to be verified
  const sendLogin = async (e) => {
    // prevent page refresh on click
    e.preventDefault();

    // axios request
    const response = await api.postLogin(data).catch(function (error) {
      if (error.response.status === 401) {
        console.log("Invalid login.");
        setErrorMessage("Invalid login information.");
      }

      console.log(error.response.status + ": " + error.response.statusText);
    });

    if (response?.status === 200) {
      // user was verified, API should return user's token
      // save the token inside of a cookie
      cookies.set("access_token", response.data.token, {
        path: "/",
        sameSite: "strict",
      });

      console.log("HERE");

      // send the user to the homepage
      window.location = "/livestock";
    }

  };

  // hello Javi
  const onChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };


  // const setError401 = () => {
  //   setErrorMessage("Invalid login information.");
  // }
  return (
    <div className="">
        <div className="login">
          <h1>WELCOME</h1>
        <form onSubmit={sendLogin}>
          <div>
            <input
              type="text"
              placeholder="Email"
              name="email"
              id="email"
              value={email}
              onChange={onChange}
              required
            />
          </div>
          <div>
            <input
              type="password"
              placeholder="Password"
              name="password"
              id="password"
              value={password}
              onChange={onChange}
              required
            />
          </div>
          <div className="btn-container">
            <button className="btn" type="submit" onSubmit={sendLogin}>LOGIN</button>
            {errorMessage ? errorMessage : null}
          </div>
        </form>
        </div>
      </div>
  );
}
export default Login;
