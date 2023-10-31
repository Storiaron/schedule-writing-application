import { useEffect, useState } from 'react';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginStatus, setLoginStatus] = useState("base");
  const handleEnter = (event) => {
    if(event.key === "enter"){
        handleSubmit();
    }
  }
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  }
  const fetchRole = async () => {
    const response = await fetch("/api/employee/role", {
      method: 'POST',
      headers: {
        'Authorization': localStorage.getItem('token'),
        'Content-Type': 'application/json',
    },
      body: JSON.stringify({"username" : username})  
  })
  if(response.ok){
    const role = await response.json();
    localStorage.setItem("role", role)
  }
  }
  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch("/login", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
      },
        body: JSON.stringify({"username": username, "password": password})  
    })
    if(response.ok){
      let token = response.headers.get("Authorization");
      if(token != null){
      localStorage.setItem("token", token)
      localStorage.setItem("username", username)
      setLoginStatus("success")
      fetchRole();
      }
      else {
        setLoginStatus("error");
      }
    }
    else {
      setLoginStatus("error");
    }
  };

  const loginform = () => {
    return  <div className='loginform'>
    <h2>Login</h2>
    <form onSubmit={handleSubmit}>
      <div>
        <div className='label'>
          <label htmlFor="username">Username</label>
        </div>
        <input
          type="text"
          id="username"
          value={username}
          onChange={handleUsernameChange}
          onKeyDown={handleEnter}
        />
      </div>
      <div className='label'>
        <label htmlFor="password">Password</label>
      </div>
      <input
        type="password"
        id="password"
        value={password}
        onChange={handlePasswordChange}
        onKeyDown={handleEnter}
      />
      <button type="submit">Login</button>
    </form>
  </div>
  }
  return loginStatus === "base" ? (<div>
   {loginform()}
   </div>
  ) : loginStatus === "success" ? (
<div className='loginform'>Login Succesful</div>
  ) : (
<div>
<div className='loginform'>Something went wrong, please try again</div>
  {loginform()}
</div>
  );
};

export default Login;