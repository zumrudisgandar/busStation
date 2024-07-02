package isgandarli.zumrud.as2springbootloggingandsecurity.initializer;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.Station;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Initializer class for creating default instances of courses.
 */

@Component
public class DefaultStationInitializer implements CommandLineRunner {

    private final StationRepository stationRepository;

    /** Constructor injection of CourseRepository */
    public DefaultStationInitializer(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    /** Add default instances to course entity */
    @Override
    public void run(String... args) throws Exception {
        Station station1 = new Station("SOCAR - Əhmədli", "SOCAR Tower", LocalTime.of(8, 45), LocalTime.of(18, 15));
        Station station2 = new Station("SOCAR - Bakıxanov, Qaraçuxur qəs.", "SOCAR Tower", LocalTime.of(8, 45), LocalTime.of(18, 15));
        Station station3 = new Station("SOCAR - Yeni Yasamal", "SOCAR Tower", LocalTime.of(8, 45), LocalTime.of(18, 15));
        Station station4 = new Station("SOCAR - 7-ci, 9-cu Mikrorayon", "SOCAR Tower", LocalTime.of(8, 45), LocalTime.of(18, 15));
        Station station5 = new Station("SOCAR - Sumqayıt şəh.", "SOCAR Tower", LocalTime.of(8, 45), LocalTime.of(18, 15));

        /** Set properties */
        stationRepository.save(station1);
        stationRepository.save(station2);
        stationRepository.save(station3);
        stationRepository.save(station4);
        stationRepository.save(station5);
    }
}