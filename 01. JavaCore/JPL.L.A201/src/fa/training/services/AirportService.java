package fa.training.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.exceptions.DataNotFoundException;
import fa.training.exceptions.IncorrectFormatException;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class AirportService {
    private List<Airport> airports;

    public AirportService() {
	this.airports = new ArrayList<>();
    }

    public AirportService(List<Airport> airports) {
	this.airports = airports;
	this.write();
    }

    /**
     * Create a new airport
     * 
     * @param scanner
     * @return
     * @throws IncorrectFormatException
     * @throws ParseException
     */
    public Airport create(Scanner scanner) throws IncorrectFormatException, ParseException {
	String id, name, runwaySize, maxFixedWingParkingPlace, maxRotatedWingParkingPlace;
	Set<String> listOfFixedWingAirplaneId = new HashSet<>();
	Set<String> listOfHelicoptersId = new HashSet<>();
	Airport airport = new Airport();

	System.out.println("----Add a new airport----");
	System.out.print("ID: ");
	id = scanner.nextLine().toUpperCase();
	if (!findByAirportID(id).isEmpty()) {
	    throw new IncorrectFormatException(Constant.DATA_EXISTS_MESSAGE);
	}
	airport.setId(id.toUpperCase());
	System.out.print("Name: ");
	name = scanner.nextLine();
	airport.setName(name);
	System.out.print("Runway size: ");
	runwaySize = scanner.nextLine();
	airport.setRunwaySize(runwaySize);
	System.out.print("Max fixed wing parking place: ");
	maxFixedWingParkingPlace = scanner.nextLine();
	airport.setMaxFixedWingParkingPlace(maxFixedWingParkingPlace);
	System.out.print("Max rotated wing parking place: ");
	maxRotatedWingParkingPlace = scanner.nextLine();
	airport.setMaxRotatedWingParkingPlace(maxRotatedWingParkingPlace);
	airport.setListOfFixedWingAirplaneId(listOfFixedWingAirplaneId);
	airport.setListOfHelicoptersId(listOfHelicoptersId);
	// add to list
	airports.add(airport);
	write();
	System.out.println("Create a new airport successfully");
	return airport;
    }

    /**
     * Save airports to file
     * 
     * @param airplanes
     */
    public void write() {
	List<String> strings = this.airports.stream().map(Airport::toString).collect(Collectors.toList());
	FileIOService fileIOService = new FileIOService();
	boolean status = fileIOService.write(Constant.FILE_AIRPORT, strings);
	if (!status) {
	    System.err.println("Can not save data to file");
	}
    }

    /**
     * Display airport by id
     * 
     * @param airportId
     * @return
     * @throws IncorrectFormatException
     */
    public List<Airport> findByAirportID(String airportId) throws IncorrectFormatException {
	if (Validator.isId(Airport.class, airportId)) {
	    return airports.stream().filter(airport -> airportId.equals(airport.getId())).toList();
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_ID_MESSAGE);
	}
    }

    /**
     * Find by airplaneId
     * 
     * @param airplaneId
     * @return
     */
    public List<Airport> findByAirplaneId(String airplaneId) {
	return airports.stream().filter(airport -> 
		airport.getListOfFixedWingAirplaneId().contains(airplaneId)
		|| airport.getListOfHelicoptersId().contains(airplaneId))
		.sorted(Comparator.comparing(Airport::getId))
		.toList();
    }

    /**
     * Remove airplane in airport
     * @param airplaineId
     * @return
     * @throws Exception
     */
    public boolean removeAirplane(String airplaineId) throws Exception {
	List<Airport> existingAirport = findByAirplaneId(airplaineId);
	if (existingAirport.isEmpty()) {
	    throw new Exception(Constant.DATA_NOT_FOUND_MESSAGE);
	}
	int index = this.airports.indexOf(existingAirport.get(0));
	Airport airport = this.airports.get(index);
	boolean status = false;
	if (airport.getListOfFixedWingAirplaneId().contains(airplaineId)) {
	    status = airport.getListOfFixedWingAirplaneId().remove(airplaineId);
	} else {
	    status = airport.getListOfHelicoptersId().remove(airplaineId);
	}
	if (status) {
	    this.airports.set(index, airport);
	    write();
	}
	return status;
    }

    /**
     * Add an airplane to airport
     * @param airportService
     * @param airportId
     * @param airplaneId
     * @return
     * @throws Exception
     */
    public boolean addAirplaneToAirport(Airplane airplane, String airportId) throws Exception {
	String airplaneId = airplane.getId();
	//Check airport already exist
	List<Airport> airports = this.findByAirportID(airportId);
	if (airports.isEmpty()) {
	    throw new DataNotFoundException(Constant.DATA_EXISTS_MESSAGE + ". AirportId");
	}
	//Check airplane is not in airport
	List<Airport> existingAirport = this.findByAirplaneId(airplaneId);
	if (!existingAirport.isEmpty()) {
	    throw new Exception(Constant.DATA_EXISTS_MESSAGE);
	}
	//get data
	Airport airport = airports.get(0);
	boolean status = false;
	if (airplane instanceof Fixedwing) {
	    if (airport.getRunwaySize() < ((Fixedwing) airplane).getMinNeededRunwaySize()) {
		throw new Exception(Constant.NOT_ENOUGH_RUNWAY_SIZE);
	    }
	    status = airport.addListOfFixedWingAirplaneId(airplaneId);
	} else if (airplane instanceof Helicopter) {
	    status = airport.addListOfHelicoptersId(airplaneId);
	}
	// update data into file
	if (status) {
	    this.write();
	}
	return status;
    }
    
    public List<Airport> displayAirportSortedByID() {
	return airports.stream()
		.sorted(Comparator.comparing(Airport::getId)).toList();
    }
}
