import React, {useState} from "react";
import "./Navbar.scss"
import {Nav} from "react-bootstrap";
import {classNameBuilder} from "../../utils/className";
import {EventKey} from "@restart/ui/types";

interface IProps {
}

const Navbar: React.FC<IProps> = () => {
  const cn = classNameBuilder("navbar")
  return (
    <Nav
      activeKey={window.location.pathname}
      className={cn()}
    >
      <Nav.Item>
        <Nav.Link href="/">Home</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link href="/profile">Profile</Nav.Link>
      </Nav.Item>
    </Nav>
  )
}

export default Navbar;