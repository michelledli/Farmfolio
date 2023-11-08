import { FrontendAPI } from "../api";
import Cookies from "universal-cookie";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import sawyer_goat_1 from "./sawyer_goat_1.jpg";
const cookies = new Cookies();
const api = FrontendAPI;

export function Login() {
  const Images = [
    sawyer_goat_1,
    sawyer_goat_1,
    sawyer_goat_1,
    sawyer_goat_1,
    sawyer_goat_1,
    sawyer_goat_1,
    sawyer_goat_1,
  ];
  const SCROLL_SPEED = 0.3;
  const navigate = useNavigate();
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const { email, password } = data;
  const [showError, setShowError] = useState(false);

  // hey Javi
  // send user login credentials to the API to be verified
  const sendLogin = async (e) => {
    // prevent page refresh on click
    e.preventDefault();

    // axios request
    const response = await api.postLogin(data).catch(function (error) {
      if (error.response.status === 401) {
        setShowError(true);
      }
    });

    if (response?.status === 200) {
      // user was verified, API should return user's token
      // save the token inside of a cookie
      cookies.set("access_token", response.data.token, {
        path: "/",
        sameSite: "strict",
      });

      // send the user to the homepage
      window.location = "/livestock";
    }
  };

  const onChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
    setShowError(false);
  };

  return (
    <>
      <div className="login">
        <div className="login__title">Login</div>
        <form onSubmit={sendLogin}>
          <input
            type="text"
            placeholder="Email"
            name="email"
            id="email"
            value={email}
            onChange={onChange}
            required
          />
          <input
            type="password"
            placeholder="Password"
            name="password"
            id="password"
            value={password}
            onChange={onChange}
            required
          />
          <button className="login__button" type="submit">
            LOGIN
          </button>
          {showError ? (
            <div className="login__error">Invalid Login.</div>
          ) : null}
        </form>
      </div>
      {/* <div className="center login__background"></div> */}
    </>
  );
}

export default Login;
