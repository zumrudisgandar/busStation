// src/features/main/MainPage.jsx
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../App.css';
import logo from '../../images/socarLogo.jpeg';
import { Link } from 'react-router-dom';


const busStops = [
  { id: 1, name: "SOCAR - Əhmədli", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 2, name: "SOCAR - Bakıxanov, Qaraçuxur qəs.", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 3, name: "SOCAR - Yeni Yasamal", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 4, name: "SOCAR - 7-ci, 9-cu Mikrorayon", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 5, name: "SOCAR - Sumqayıt şəh.", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 6, name: "SOCAR - Yeni Günəşli", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 7, name: "SOCAR - Lökbatan qəs.", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" },
  { id: 8, name: "SOCAR - Badamdar qəs.", location: "SOCAR Tower", arrival: "08:45", departure: "18:15" }
];

const MainPage = () => {
  return (
        <div className="container">
        <header>
            <img src={logo} alt="SOCAR Logo" className="logo" />
            <div className="h2Container">
                <h2 className="h2Header">Avtobus Dayanacaqları</h2>
            </div>
            <h4>Dayanacaqların siyahısı</h4>
        </header>
            <div className="row">
                {busStops.map(stop => (
                <div className="col-md-3 mb-4" key={stop.id}>
                    <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">Ad:  <br /> {stop.name}</h5>
                        <p className="card-text">Yer: <br /> {stop.location}</p>
                        <p className="card-text">Gəliş vaxtı:  <br /> {stop.arrival}</p>
                        <p className="card-text">Gediş vaxtı: <br /> {stop.departure}</p>
                        <Link to={`/route/${stop.id}`} className="btn btn-custom">View Details</Link>
                    </div>
                    </div>
                </div>
                ))}
            </div>
        </div>
  );
};

export default MainPage;
