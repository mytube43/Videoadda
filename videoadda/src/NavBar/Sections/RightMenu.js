import React from 'react';
import { Menu } from 'antd';
import axios from 'axios';
import { withRouter } from 'react-router-dom';
import { useNavigate } from "react-router-dom";


function RightMenu(props) {
const navigate = useNavigate();
const Signin =()=>{
let path = '/login';
 navigate(path);
}

const Signup =()=>{
let path = '/Register';
 navigate(path);
}

  const logoutHandler = () => {
    /*axios.get(`/logout`).then(response => {
      if (response.status === 200) {
        props.history.push("/login");
      } else {
        alert('Log Out Failed')
      }
    });*/
  };

  /*if (user.userData && !user.userData.isAuth) {
    return (
      <Menu mode={props.mode}>
        <Menu.Item key="mail">
          <a href="/login">Signin</a>
        </Menu.Item>
        <Menu.Item key="app">
          <a href="/register">Signup</a>
        </Menu.Item>
      </Menu>
    )
  } else {
    return (
      <Menu mode={props.mode}>
        <Menu.Item key="logout">
          <a onClick={logoutHandler}>Logout</a>
        </Menu.Item>
      </Menu>
    )
  }*/
  return (
           <div>
           <button onClick={Signin} >Sign In</button>

           <button onClick={Signup} >Sign Up</button>
           </div>
      )
}

export default RightMenu;

