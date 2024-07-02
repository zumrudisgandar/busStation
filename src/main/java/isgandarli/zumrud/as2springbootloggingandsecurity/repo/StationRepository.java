package isgandarli.zumrud.as2springbootloggingandsecurity.repo;


import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing Course entities.
 */
public interface StationRepository extends JpaRepository<Station, Integer> {

    // Spring Data JPA Query Methods for Searching
    Page<Station> findByRouteNameIgnoreCase(@Param("routeName") String routeName, Pageable pageable);
    Page<Station> findByStationLocationIgnoreCase(@Param("stationLocation") String stationLocation, Pageable pageable);
    Page<Station> findByArrivalTime(@Param("arrivalTime")String arrivalTime, Pageable pageable);

    // JPA @Query (non-native) for custom query (for Sorting)
    @Query("SELECT s FROM Station s ORDER BY s.stationID ASC")
    Page<Station> findAllByOrderByStationIDAsc(Pageable pageable);
    @Query("SELECT s FROM Station s ORDER BY s.routeName ASC")
    Page<Station> findAllByOrderByRouteNameAsc(Pageable pageable);
    @Query("SELECT s FROM Station s ORDER BY s.stationLocation ASC")
    Page<Station> findAllByOrderByStationLocationAsc(Pageable pageable);

    @Query("SELECT s FROM Station s ORDER BY s.arrivalTime ASC")
    Page<Station> findAllByOrderByArrivalTimeAsc(Pageable pageable);
}