import React from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.css';
import Login from './components/Login';
import Consultas from './components/Consultas';

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/consultas" element={<Consultas />} />
            </Routes>
        </div>
    );
}

export default App;
