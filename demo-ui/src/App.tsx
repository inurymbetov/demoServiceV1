import React from 'react';
import "./assets/styles/main.scss"
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HomeView from "./views/home/HomeView";
import ProfileViews from "./views/profile/ProfileViews";
import Navbar from "./components/navbar/Navbar";

function App() {
    return (
        <Router>
          <Navbar/>
            <Routes>
                <Route path={"/"} element={<HomeView/>}/>
                <Route path={"/profile"} element={<ProfileViews/>}/>
            </Routes>
        </Router>
    );
}

export default App;
