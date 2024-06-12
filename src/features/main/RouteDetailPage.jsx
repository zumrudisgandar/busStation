import React, {useState} from 'react';
import { useParams } from 'react-router-dom';
import '../../App.css';
import staticMap from '../../images/map.png';
import logo from '../../images/socarLogo.jpeg';

const routeDetails = {
  1: {
    name: 'Əhmədli',
    vehicle: 'İsuzu Visigo',
    location: 'SOCAR Tower',
    phone: '050-575-43-84',
    plate: '99 JU 968',
    driver: 'Hacıyev Vahid',
    serviceNo: 4,
    route: 'SOCAR - Əhmədli marşrutu, Neapol Dairəsi',
    arrival: '08:45',
    departure: '18:15',
    stops: ['Gəncə pr', 'Həzi Aslanov m/s', 'Nargilə', '8 Noyabr pr.Amay T/M', 'Xətai m/s', 'Heydər Əliyev prospekti', 'SOCAR Tower']
  }
  // add details for other routes
};

const RouteDetailPage = () => {
  const { id } = useParams();
  const details = routeDetails[id];
  const [showAll, setShowAll] = useState(false);

  if (!details) {
    return <div>Route not found</div>;
  }

  const stopsToShow = showAll ? details.stops : details.stops.slice(0, 3);

  return (
    <div className="container-details">
        <header>
            <img src={logo} alt="SOCAR Logo" className="logo" />
            <div className="h2Container">
            <h4 className='pt-3'>Dayanacaq detalı</h4>
            </div>
        </header>
      <div className="map mt-4">
        <img src={staticMap} alt="Map" style={{ width: '100%', height: '100%' }} />
      </div>
      <div className="details-page">
      <div className="box-details">
          <div className="detail-row">
            <span className="detail-label">Ad:</span>
            <span className="detail-value">{`SOCAR - ${details.name}`}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Avtomobil:</span>
            <span className="detail-value">{details.vehicle}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Yer:</span>
            <span className="detail-value">{details.location}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Telefon nömrəsi:</span>
            <span className="detail-value">{details.phone}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Avtomobilin nömrəsi:</span>
            <span className="detail-value">{details.plate}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Sürücü:</span>
            <span className="detail-value">{details.driver}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Servis No:</span>
            <span className="detail-value">{details.serviceNo}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Marşrut:</span>
            <span className="detail-value">
              {details.route}.
              <br />
              {stopsToShow.map((stop, index) => (
                <React.Fragment key={index}>
                  - {stop}
                  <br />
                </React.Fragment>
              ))}
              {details.stops.length > 3 && (
                <button onClick={() => setShowAll(!showAll)} className="btn btn-link">
                  {showAll ? 'Show less' : 'Show more'}
                </button>
              )}
            </span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Gəliş vaxtı:</span>
            <span className="detail-value">{details.arrival}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Gediş vaxtı:</span>
            <span className="detail-value">{details.departure}</span>
          </div>
        </div>
        <div className="box-details">
        <div className="route-stops">
            {details.stops.map((stop, index) => (
              <div className="stop" key={index}>
                <div className={`stop-circle ${index === 0 ? 'start' : ''} ${index === details.stops.length - 1 ? 'end' : ''}`}></div>
                <div className="stop-name">{stop}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
      <button onClick={() => window.history.back()} className="btn btn-custom">Back</button>
    </div>
  );
};

export default RouteDetailPage;