import React, { useState } from 'react';
import LeftMenu from './Sections/LeftMenu';
import RightMenu from './Sections/RightMenu';


import './Sections/Navbar.css';

    function NavBar() {
  const [visible, setVisible] = useState(false)

  const showDrawer = () => {
    setVisible(true)
  };

  const onClose = () => {
    setVisible(false)
  };

  return (
    <nav className="" style={{ position: 'fixed', zIndex: 1, width: '100%',height: '150px',background: 'blueviolet' }}>
      <div className="menu__logo">
      <button >Video adda</button>
      </div>
      <div className="menu__container">
        <div className="">
          <RightMenu mode="horizontal" />
        </div>


      </div>
    </nav>
  )
}

export default NavBar