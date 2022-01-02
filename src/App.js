import React, { useState } from "react";
import Modal from "react-modal";
//npm install react-modal
export default function App() {
  const [isOpen, setIsOpen] = useState(false);

  function toggleModal() {
    setIsOpen(!isOpen);
  }

  return (
    <div className="App">
      <button >Open modal</button>


    </div>
  );
}



