import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import Buy from './pages/buy';
import Sell from './pages/sell';
import Play from './pages/play';
import Login from './pages/login';
import Register from './pages/register';
import 'semantic-ui-css/semantic.min.css'
import { CookiesProvider } from "react-cookie";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <CookiesProvider>
    <Router>
      <Routes>
        <Route exact path='/' element={<Home/>}/>
        <Route path='/buy' element={<Buy/>}/>
        <Route path='/sell' element={<Sell/>}/>
        <Route path='/play' element={<Play/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/register' element={<Register/>}/>
      </Routes>
    </Router>
  </CookiesProvider>
);