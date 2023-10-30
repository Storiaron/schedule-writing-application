import React, { useEffect, useState } from 'react';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loggedInUser, setLoggedInUser] = useState('');
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

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch("/api/employee/login", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
      },
        body: JSON.stringify({"username": username, "password": password})  
    })
    if(response.ok){
      const responseData = await response.json()
      setLoggedInUser(responseData);
      console.log(responseData)
    }
    else {
      //TODO handle bad username
      console.log(response);
    }
  };

  return (
    <div className='loginform'>
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
  );
};

export default Login;