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

  // hey Javi
  // send user login credentials to the API to be verified
  const sendLogin = async (e) => {
    // prevent page refresh on click
    e.preventDefault();

    // axios request
    const response = await api.postLogin(data);

    console.log(response);

    if (response.status === 200) {
      // user was verified, API should return user's token
      // save the token inside of a cookie
      cookies.set("access_token", response.data.token, {
        path: "/",
        sameSite: "strict",
      });

      console.log("HERE");

      // send the user to the homepage
      window.location = "/";
    }
  };

  // hello Javi
  const onChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  return (
    <div>
    <div className="splitScreen">
      <div className="topPane">
        <h1>Farmfolio</h1> 
        <h3>About</h3>
        <div>Explore Sawyer's Farm Goat Catalog at Bret Harte High School:
          Located at Bret Harte High School, Sawyer's Farm is a unique endeavor cared for by both 
          students and faculty. Our catalog offers a range of high-quality goats and supplies, 
          and we take pride in practicing responsible farming. Whether you're a student learning 
          about agriculture or a faculty member with a passion for goats, Sawyer's Farm is here 
          to support your goat-keeping journey. Join our school community in fostering a love for 
          sustainable farming with Sawyer's Farm.
        </div>

      </div>
      <div className="bottomPane">
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
          </div>
        </form>
        </div>
      </div>
    </div>
    </div>
  );
}
export default Login;
