import { useState } from "react";
import API from "../services/api";
import "./Login.css";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const res = await API.post("/auth/login", {
        email,
        password
      });

      localStorage.setItem("token", res.data.token);

      alert("Login Successful ✅");
    } catch (err) {
      alert("Login Failed ❌");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Welcome Back</h2>
        <p className="subtitle">Login to your account</p>

        <input
          type="email"
          placeholder="Enter Email"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Enter Password"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  );
}

export default Login;