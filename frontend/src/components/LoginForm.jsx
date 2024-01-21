import React, { useState } from 'react'
import {
  MDBContainer,
  MDBInput,
  MDBBtn
}
  from 'mdb-react-ui-kit';


const LoginForm = ({ startLogin }) => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');


  const handleLogin = (event) => {

    event.preventDefault();

    const credentials = {
      email, password,
    };


    startLogin(credentials);


    setEmail('');
    setPassword('');
  }


  return (
    <MDBContainer className="p-3 my-5 d-flex flex-column w-50">
      <form onSubmit={handleLogin} id='login-form'>
        <h3>Sign In</h3>

        <MDBInput
          type='text'
          wrapperClass='mb-4'
          placeholder='Email Address'
          value={email}
          onChange={event => setEmail(event.target.value)}
          id='email'
          required
        />

        <MDBInput
          type='password'
          wrapperClass='mb-4'
          placeholder='Password'
          value={password}
          onChange={event => setPassword(event.target.value)}
          id='password'
          required
        />

        <MDBBtn className="mb-4" id='login-submit'>LOGIN</MDBBtn>
      </form>
    </MDBContainer>
  )
};

export default LoginForm;