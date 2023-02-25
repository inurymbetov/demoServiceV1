import React from 'react';
import "./assets/styles/main.scss"
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HomeView from "./views/home/HomeView";

function App() {
    return (
        <Router>
            <Routes>
                <Route path={"/"} element={<HomeView/>}/>
            </Routes>
        </Router>
    );
}

export default App;
