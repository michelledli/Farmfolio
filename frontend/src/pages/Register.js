import { FrontendAPI } from "../api"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
const api = FrontendAPI;

export function Register() {
    const navigate = useNavigate();
    const [data, setData] = useState({
        email:"",
        password:""
    })
    const {email, password} = data;

    // send user register credentials to the API to be saved into the database
    const sendRegister = (e) => {
        // prevent page refresh on click
        e.preventDefault();
        
        // axios request
        const response = api.postRegister(data);

        if (response.status === 200) {
            // user was registered, send to login page
            navigate("/login");
        }
    }

    const onChange = (e) => {
        setData({...data,[e.target.name]:[e.target.value]});
    }

    return (
        <div>
        <form action="" onSubmit={sendRegister}>
          <div className="input-container">
            <label>Email </label>
            <input 
                type="text" 
                name="email"
                id="email"
                value={email}
                onChange={onChange} 
                required />
          </div>
          <div className="input-container">
            <label>Password </label>
            <input 
                type="password" 
                name="password"
                id="password"
                value={password}
                onChange={onChange}  
                required />
          </div>
          <div className="button-container">
            <button type="submit">Register </button>
          </div>
        </form>
      </div>
    );
}

export default Register;