import './App.css';
import React from "react";
import Navbar from "./components/Navbar/index";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home from './pages';
import MenuCC from './pages/MenuCC';
import MenuPersona from './pages/MenuPersona'
import { Login } from './components/Login';
import { AltaUser } from './components/AltaUser';


function App() {
  return (
    <>
    <div style={{backgroundColor:'#282c34'}} className = "container" >

    <Router>
      <Navbar />
      <Switch>
        <Route path="/Registrarse" exact component={AltaUser} />
        <Route path="/" exact component={Home} />
        <Route path="/MenuPersona" exact component={MenuPersona} />        
        <Route path="/MenuCC" exact component={MenuCC} />        
        <Route path="/Login" exact component={Login} />  
      </Switch>
    </Router>
    </div>        

  </>
  );
}

export default App;