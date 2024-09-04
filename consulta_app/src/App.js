import React from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.css';
import Login from './components/Login';
import Consultas from './components/Consultas';
import { ChakraProvider } from "@chakra-ui/react";
import Register from "./components/Register";


function App() {
    return (
        <ChakraProvider>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/consultas" element={<Consultas/>}/>
                    <Route path="/register" element={<Register/>}/>
                </Routes>
            </div>
        </ChakraProvider>
    );
}

export default App;
