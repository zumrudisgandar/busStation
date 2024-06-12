// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from './features/main/MainPage';
import RouteDetailPage from './features/main/RouteDetailPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/route/:id" element={<RouteDetailPage />} />
      </Routes>
    </Router>
  );
}

export default App;