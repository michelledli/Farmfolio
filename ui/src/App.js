import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <div class="login-box">
    <div class="login-header">
      <h1>Farmfolio Login</h1>
    </div>
      <form>     
        <label class="login-label" for="username">Username:</label>
        <input class="login-input" type="text" id="username" name="username" required></input>

        <label class="login-label" for="password">Password:</label>
        <input class="login-input" type="password" id="password" name="password" required></input>

        <div class="forgot-password">
          <a class="log-link" href="#">Forgot password?</a>
        </div>
        
        <form>
          <button class="login-button" type="submit" formaction="dashboard.html">Login</button>
        </form> 
    </form>
 
    </div>

    </div>
  );
}

export default App;
