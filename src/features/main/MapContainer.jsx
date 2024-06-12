import React from 'react';
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';

const containerStyle = {
  width: '100%',
  height: '400px'
};

const center = {
  lat: 40.4093,  // Default center
  lng: 49.8671
};

const MapContainer = ({ stops }) => {
  const API_KEY = 'YOUR_GOOGLE_MAPS_API_KEY';

  const stopCoordinates = stops.map(stop => {
    switch (stop) {
      case 'Neapol Dairəsi': return { lat: 40.3842, lng: 49.8978 };
      case 'Gəncə pr': return { lat: 40.3734, lng: 49.9211 };
      case 'Həzi Aslanov m/s': return { lat: 40.3732, lng: 49.9517 };
      case 'Nargilə': return { lat: 40.3702, lng: 49.8874 };
      case '8 Noyabr pr.Amay T/M': return { lat: 40.3832, lng: 49.8402 };
      case 'Xətai m/s': return { lat: 40.3953, lng: 49.8826 };
      case 'Heydər Əliyev prospekti': return { lat: 40.3737, lng: 49.8443 };
      case 'SOCAR Tower': return { lat: 40.3925, lng: 49.8767 };
      default: return null;
    }
  }).filter(coord => coord !== null);

  return (
    <LoadScript googleMapsApiKey={API_KEY}>
      <GoogleMap mapContainerStyle={containerStyle} center={center} zoom={12}>
        {stopCoordinates.map((coord, index) => (
          <Marker key={index} position={coord} />
        ))}
      </GoogleMap>
    </LoadScript>
  );
};

export default MapContainer;
