package isgandarli.zumrud.as2springbootloggingandsecurity.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;

/** Lombok annotation to generate getters, setters, toString, equals, and hashCode methods */
@Data
public class StationDTO {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY // Auto-increment for the primary key */
    )
    private int stationID;

    @NotBlank(message = "The route name is required")
    private String routeName;

    @NotBlank(message = "The station location is required")
    private String stationLocation;

    @NotNull(message = "The arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "The departure time is required")
    private LocalTime departureTime;
}