package isgandarli.zumrud.as2springbootloggingandsecurity.controller;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.StationDTO;
import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.Station;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.StationRepository;
import isgandarli.zumrud.as2springbootloggingandsecurity.service.StationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling requests related to courses.
 */

@Data
@Controller
@RequestMapping({"/stations"})
@Slf4j
public class StationController {

    /** Dependency Injection */
    private final StationRepository stationRepository;
    private final StationService stationService;


    public StationController(StationRepository stationRepository, StationService stationService) {
        this.stationRepository = stationRepository;
        this.stationService = stationService;
    }

    @GetMapping
    public String showStationList(Model model, @PageableDefault(size = 5) Pageable pageable) {
        log.info("-----> INFO: Displaying station list page");
        Page<Station> stationsPage = stationService.getAllStation(pageable);
        model.addAttribute("stations", stationsPage.getContent());
        model.addAttribute("page", stationsPage);
        return "stations/list";
    }

    /** Send to Create Station page */
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        /** Prepare a new StationDTO object and add it to the model */
        log.info("-----> INFO: User accessed the create station page.");
        StationDTO stationDTO = new StationDTO();
        model.addAttribute("stationDTO", stationDTO);
        return "stations/CreateStation";
    }

    /** Save created new course */
    @PostMapping("/create")
    public String createStation(
            @Validated @ModelAttribute StationDTO stationDTO,
            BindingResult result
    ) {
        /** Validate the submitted form data using @Validated annotation */
        if(result.hasErrors()) {
            log.warn("Station creation failed due to validation errors.");
            return "stations/CreateStation"; /** Return to the form if there are validation errors */
        }

        /** Create a new Station object and populate it with form data */
        Station station = new Station();
        station.setRouteName(stationDTO.getRouteName());
        station.setStationLocation(stationDTO.getStationLocation());
        station.setArrivalTime(stationDTO.getArrivalTime());
        station.setDepartureTime(stationDTO.getDepartureTime());

        /** Save the new course to the database using the courseRepository */
        stationRepository.save(station);
        log.info("-----> INFO: New station created successfully: {}", stationDTO.getRouteName());
        return "redirect:/stations";
    }

    /** Send to edit station page */
    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ){
        log.info("-----> INFO: Displaying edit station page for course with ID: {}", id);
        try {
            /** Retrieve the course by ID and add it to the model for editing */
            Station station = stationRepository.findById(id).get();
            model.addAttribute("station", station);
            /** Prepare a StationDTO object for editing and add it to the model */
            StationDTO stationDTO = new StationDTO();
            stationDTO.setRouteName(station.getRouteName());
            stationDTO.setStationLocation(station.getStationLocation());
            stationDTO.setArrivalTime(station.getArrivalTime());
            stationDTO.setDepartureTime(station.getDepartureTime());

            model.addAttribute("stationDTO", station);
        }
        catch (Exception ex) {
            log.error("-----> ERROR: Error while displaying edit station page for station with ID: {}", id, ex);
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/stations"; /** Redirect to the station list page if an exception occurs */
        }
        return "stations/EditStation";
    }

    // Save edited station
    @PostMapping("/edit")
    public String updateStation(
            Model model,
            @RequestParam int id,
            @Validated @ModelAttribute StationDTO stationDTO,
            BindingResult result
    ) {
        log.info("----->INFO: Updating station with ID: {}", id);
        try{
            if(result.hasErrors()) {
                log.warn("----->WARN: User entered invalid input");
                return "stations/EditStation";
            }
            /** Retrieve the course by ID */
            Station station = stationRepository.findById(id).get();
            model.addAttribute("station", station);
            /** Validate the submitted form data */

            /** Update the course entity with the new data */
            station.setRouteName(stationDTO.getRouteName());
            station.setStationLocation(stationDTO.getStationLocation());
            station.setArrivalTime(stationDTO.getArrivalTime());
            station.setDepartureTime(stationDTO.getDepartureTime());

            /** Save the updated station to the database */
            stationRepository.save(station);
        } catch(Exception ex) {
            log.error("----->ERROR: Error while updating station with ID: {}", id, ex);
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/stations";
    }

    /** Delete selected course */
    @GetMapping("/delete")
    public String deleteStaion (
            @RequestParam int id
    ) {
        log.info("----->INFO: Deleting station with ID: {}", id);
        try{
            /** Retrieve the station by ID and delete it */
            Station station = stationRepository.findById(id).get();
            stationRepository.delete(station);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/stations";
    }

    /** Search for a course */
    @GetMapping("/search")
    public String searchStations(@RequestParam String input, @RequestParam String searchCriteria, Model model, @PageableDefault(size = 5)  Pageable pageable) {
        log.info("----->INFO: Searching for stations with input: {} and criteria: {}", input, searchCriteria);
        Page<Station> stationsPage = null;
        String errorMessage = null;
        /** Search for courses by specified criteria */
        switch (searchCriteria) {
            case "routeName":
                stationsPage = stationService.getStationByRouteName(input, pageable);
                break;
            case "stationLocation":
                stationsPage = stationService.getStationByStationLocation(input, pageable);
                break;
            case "credits":
                    stationsPage = stationService.getStationByArrivalTime(input, pageable);
                break;
            default:
                errorMessage = searchCriteria + " " + input + " does not exist.";
                break;
        }
        if (stationsPage == null || stationsPage.isEmpty()) {
            if (errorMessage == null) {
                errorMessage = searchCriteria.toUpperCase() + " '" + input + "' does not exist.";
            }
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("page", stationsPage);
        } else {
            model.addAttribute("stations", stationsPage.getContent());
            model.addAttribute("page", stationsPage);
        }
        return "stations/list";
    }

    /** Sorting */
    @GetMapping("/sort")
    public String sortStations(@RequestParam(required = false) String field, Model model, Pageable pageable) {
        log.info("----->INFO: Sorting stations by field: {}", field);
        if (field != null) {
            /** Sort courses by the specified field */
            Page<Station> stationPage = stationService.getStationsSortedByField(field, pageable);
            model.addAttribute("stations", stationPage.getContent());
            model.addAttribute("page", stationPage);
        } else {
            /** Handle case where no field is selected */
            Page<Station> stationPage = stationService.getAllStation(pageable);
            model.addAttribute("stations", stationPage.getContent());
            model.addAttribute("page", stationPage);
        }
        return "stations/list";
    }
}