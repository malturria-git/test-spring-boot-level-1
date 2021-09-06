import React from "react";
import { Nav,NavLink,Bars,NavMenu,NavBtn,NavBtnLink} from "./NavbarElements";


const Navbar = () => {
    return (
        <>
           <Nav>
            <Bars />
            <NavMenu>
                <NavBtn>
                    <NavBtnLink to="/Registrarse">Registrarse</NavBtnLink>                
                </NavBtn>                
                <NavBtn>
                    <NavBtnLink to="/Login">Login</NavBtnLink>                
                </NavBtn>
                <NavLink to="/MenuPersona" activeStyle>
                    CRUD de Personas
                </NavLink>
                <NavLink to="/MenuCC" activeStyle>
                    CRUD de CC
                </NavLink>
            </NavMenu> 
           </Nav> 
        </>
    );
};
export default Navbar;
