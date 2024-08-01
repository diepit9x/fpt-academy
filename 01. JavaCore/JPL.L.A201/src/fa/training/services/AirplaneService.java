package fa.training.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.exceptions.DataNotFoundException;
import fa.training.exceptions.IncorrectFormatException;
import fa.training.exceptions.InvalidDataException;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class AirplaneService {
    private List<Airplane> airplanes;

    public AirplaneService() {
	this.airplanes = new ArrayList<>();
    }

    public AirplaneService(List<Airplane> airplanes) {
	this.airplanes = airplanes;
	this.write();
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    /**
     * Create a new airplane
     * 
     * @param scanner
     * @return
     * @throws IncorrectFormatException
     * @throws ParseException
     */
    public Airplane create(Scanner scanner) throws Exception {
	Airplane airplane;
	String choice, id, model, cruiseSpeed, emptyWeight, maxTakeOffWeight, planeType, minNeededRunwaySize, range;

	System.out.println("----Add a new airplane----");
	System.out.println("1. Fixedwing");
	System.out.println("2. Helicopter");
	System.out.print("Choice: ");
	choice = scanner.nextLine();

	if (choice.equals("1")) {
	    airplane = new Fixedwing();
	} else {
	    airplane = new Helicopter();
	}
	// inut data
	System.out.print("ID: ");
	id = scanner.nextLine();
	if (!findByAirplaneId(id).isEmpty()) {
	    throw new InvalidDataException(Constant.DATA_EXISTS_MESSAGE);
	}
	airplane.setId(id);
	System.out.print("Model: ");
	model = scanner.nextLine().toUpperCase();
	airplane.setModel(model);
	System.out.print("Cruise speed: ");
	cruiseSpeed = scanner.nextLine();
	airplane.setCruiseSpeed(cruiseSpeed);
	System.out.print("Empty weight: ");
	emptyWeight = scanner.nextLine();
	airplane.setEmptyWeight(emptyWeight);
	System.out.print("Max take off weight: ");
	maxTakeOffWeight = scanner.nextLine();
	airplane.setMaxTakeOffWeight(maxTakeOffWeight);
	if (!Validator.isWeightTimes(airplane)) {
	    throw new InvalidDataException(Constant.INVALID_DATA_MESSAGE);
	}
	if (choice.equals("1")) {
	    System.out.print("Plane type: ");
	    planeType = scanner.nextLine().toUpperCase();
	    ((Fixedwing) airplane).setPlaneType(planeType);
	    System.out.print("Min needed runway size: ");
	    minNeededRunwaySize = scanner.nextLine();
	    ((Fixedwing) airplane).setMinNeededRunwaySize(minNeededRunwaySize);
	} else {
	    System.out.print("Range: ");
	    range = scanner.nextLine();
	    ((Helicopter) airplane).setRange(range);
	}
	this.airplanes.add(airplane);
	write();
	System.out.println("Create a new airplane successfully");
	return airplane;
    }

    /**
     * Save airplane to file
     * 
     * @param airplanes
     */
    public void write() {
	List<String> strings = this.airplanes.stream().map(Airplane::toString).collect(Collectors.toList());
	FileIOService fileIOService = new FileIOService();
	boolean status = fileIOService.write(Constant.FILE_AIRPLANE, strings);
	if (!status) {
	    System.err.println("Can not save data to file");
	}
    }

    /**
     * Search airplane by Id
     * 
     * @param airplaneId
     * @return
     * @throws IncorrectFormatException
     * @throws DataNotFoundException 
     */
    public List<Airplane> findByAirplaneId(String airplaneId) throws IncorrectFormatException, DataNotFoundException {
	if (!Validator.isId(Airplane.class, airplaneId)) {
	    throw new IncorrectFormatException(Constant.INCORRECT_ID_MESSAGE);
	}
	return this.airplanes.stream().filter(airplane -> airplaneId.equals(airplane.getId())).toList();
    }
    
    /**
     * change plane type and min needed runway size of fixed wing airplane.
     * @param airportService
     * @param changeAirplane
     * @return
     * @throws Exception
     */
    public boolean changeAirplane(AirportService airportService, Airplane changeAirplane) throws Exception {
	//Check airplane already exist
	List<Airplane> existingAirplanes = this.findByAirplaneId(changeAirplane.getId());
	if (existingAirplanes.isEmpty()) {
	    throw new DataNotFoundException(Constant.DATA_NOT_FOUND_MESSAGE);
	}
	//Check airport already exist
	List<Airport> existingAirports = airportService.findByAirplaneId(changeAirplane.getId()); 
	if (!existingAirports.isEmpty()) {
	    Airport airport = existingAirports.get(0);
	    //check new airplane's runway size is valid
	    if (airport.getRunwaySize() < ((Fixedwing) changeAirplane).getMinNeededRunwaySize()) {
		throw new Exception(Constant.NOT_ENOUGH_RUNWAY_SIZE);
	    }
	}
	Airplane oldAirplane = existingAirplanes.get(0);
	int index = this.airplanes.indexOf(oldAirplane);
	boolean status = this.airplanes.set(index, changeAirplane) != null;
	if (status) {
	    this.write();
	}
	return status;
    }
    
    /**
     * display airplane placed at airport
     * @param airportService
     * @param airplaneType
     * @return
     * @throws IncorrectFormatException
     */
    public List<Airplane> displayAirplaneHasAirport(AirportService airportService, String airplaneType) throws IncorrectFormatException {
	if (airplaneType.equals("Fixedwing")) {
	    return this.airplanes.stream()
			.filter(airplane ->airplane instanceof Fixedwing)
			.filter(airplane -> !airportService.findByAirplaneId(airplane.getId()).isEmpty())
			.toList();
	} else if (airplaneType.equals("Helicopter")) {
	    return this.airplanes.stream()
			.filter(airplane ->airplane instanceof Helicopter)
			.filter(airplane -> !airportService.findByAirplaneId(airplane.getId()).isEmpty())
			.toList();
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_ID_MESSAGE);
	}
    }
}
