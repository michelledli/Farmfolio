import { Link } from "react-router-dom";
import AuditLog from "../components/AuditLog";

export function Linkpage() {
    return (
        <div className="App-header">
            <h1>Link Page</h1>
            <ul>
                <li><Link to="/news">News</Link></li>
                <li><Link to="/livestock">Livestock</Link></li>
                <li><Link to="/">Home/Login</Link></li>
                <li><Link to="/user-add">Add User</Link></li>
                <li><Link to="/animal-add">Add Animal</Link></li>
                <li><Link to="/create">Post Create</Link></li>
                <li><Link to="/test">Test</Link></li>
            </ul>
        </div>
    );
}