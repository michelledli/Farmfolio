import { FrontendAPI } from "../api";
import Cookies from "universal-cookie";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
const api = FrontendAPI;

export function Role() {
  const [isAdmin, setIsAdmin] = useState(false);
  const [isUser, setIsUser] = useState(false);

  useEffect(() => {
		(async () => {
    setIsAdmin(
      await api.request({
        url: `/api/role/admin`,
        method: "GET",
      })
    );

    setIsUser(
      await api.request({
        url: "/api/role/user",
        method: "GET",
      })
    );
		})();
  }, []);

  return (
    <div>
      <div>Is User: {isUser}</div>
      <div>Is Admin: {isAdmin}</div>
    </div>
  );
}

export default Role;
