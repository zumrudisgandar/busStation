package isgandarli.zumrud.as2springbootloggingandsecurity.service;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.Station;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.StationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
// Indicates that this class provides business logic and is a Spring-managed bean
public class StationService {

    private final StationRepository stationRepository;

    // Constructor injection of CourseRepository
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    // Retrieve courses by name with pagination
    public Page<Station> getStationByRouteName(String routeName, Pageable pageable) {
        return stationRepository.findByRouteNameIgnoreCase(routeName, pageable);
    }

    // Retrieve courses by department name with pagination
    public Page<Station> getStationByStationLocation(String stationLocation, Pageable pageable) {
        return stationRepository.findByStationLocationIgnoreCase(stationLocation, pageable);
    }

    // Retrieve courses by credit with pagination
    public Page<Station> getStationByArrivalTime(String arrivalTime, Pageable pageable) {
        return stationRepository.findByArrivalTime(arrivalTime, pageable);
    }

    // Retrieve all courses with pagination
    public Page<Station> getAllStation(Pageable pageable) {
        return stationRepository.findAll(pageable);
    }

    // Retrieve sorted courses based on field with pagination
    public Page<Station> getStationsSortedByField(String field, Pageable pageable) {
        Page<Station> stationsPage;
        switch (field) {
            case "stationID":
                stationsPage = stationRepository.findAllByOrderByStationIDAsc(pageable);
                break;
            case "routeName":
                stationsPage = stationRepository.findAllByOrderByRouteNameAsc(pageable);
                break;
            case "stationLocation":
                stationsPage = stationRepository.findAllByOrderByStationLocationAsc(pageable);
                break;
            case "arrivalTime":
                stationsPage = stationRepository.findAllByOrderByArrivalTimeAsc(pageable);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
        return stationsPage;
    }
}