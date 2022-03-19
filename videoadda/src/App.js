import React, { useState } from "react";
import Modal from "react-modal";
import UploadButton from './Upload/Uploadbutton'
import Basicform from './Forms/LoginForm'
import NavBar from './NavBar/NavBar'
import { Routes ,Route, BrowserRouter } from 'react-router-dom';
//npm install react-modal
export default function App() {
  const [isOpen, setIsOpen] = useState(false);

  function toggleModal() {
    setIsOpen(!isOpen);
  }

  return (
    <div className="App">

     <Routes>
         <Route exact path="/" element={<NavBar/>} />
        <Route path="/upload" element={<UploadButton/>} />
        <Route exact path="/login" element={<Basicform/>} />
      </Routes>


    </div>
  );
}



