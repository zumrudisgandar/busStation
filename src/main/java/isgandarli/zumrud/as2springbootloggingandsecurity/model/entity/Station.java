package isgandarli.zumrud.as2springbootloggingandsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "station"
) // Define the Station entity

public class Station {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY // Auto-increment for the primary key */
    )
    private int stationID;
    @Column(
            columnDefinition = "TEXT"
    )
    private String routeName;
    @Column(
            columnDefinition = "TEXT"
    )
    private String stationLocation;
    @Column(
            columnDefinition = "TIME"
    )
    private LocalTime arrivalTime;
    @Column(
            columnDefinition = "TIME"
    )
    private LocalTime departureTime;

    public Station(String routeName, String stationLocation, LocalTime arrivalTime, LocalTime departureTime) {
        this.routeName = routeName;
        this.stationLocation = stationLocation;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}