package fa.training.entities;

import java.util.Set;

import fa.training.exceptions.IncorrectFormatException;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class Airport {
    private String id;
    private String name;
    private double runwaySize;
    private int maxFixedWingParkingPlace;
    private Set<String> listOfFixedWingAirplaneId;
    private int maxRotatedWingParkingPlace;
    private Set<String> listOfHelicoptersId;

    public Airport() {
    }

    public Airport(String id, String name, double runwaySize, int maxFixedWingParkingPlace,
	    Set<String> listOfFixedWingAirplaneId, int maxRotatedWingParkingPlace, Set<String> listOfHelicoptersId) {
	super();
	this.id = id;
	this.name = name;
	this.runwaySize = runwaySize;
	this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
	this.listOfFixedWingAirplaneId = listOfFixedWingAirplaneId;
	this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
	this.listOfHelicoptersId = listOfHelicoptersId;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) throws IncorrectFormatException {
	if (Validator.isId(getClass(), id)) {
	    this.id = id;
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_ID_MESSAGE);
	}
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getRunwaySize() {
	return runwaySize;
    }

    public void setRunwaySize(String runwaySize) throws NumberFormatException {
	this.runwaySize = Double.parseDouble(runwaySize);
    }

    public int getMaxFixedWingParkingPlace() {
	return maxFixedWingParkingPlace;
    }

    public void setMaxFixedWingParkingPlace(String maxFixedWingParkingPlace) throws NumberFormatException {
	int n = Integer.parseInt(maxFixedWingParkingPlace);
	if (n > 0) {
	    this.maxFixedWingParkingPlace = n;
	} else {
	    throw new NumberFormatException(Constant.NUMBER_OF_PLACE);
	}
    }

    public Set<String> getListOfFixedWingAirplaneId() {
	return listOfFixedWingAirplaneId;
    }

    public void setListOfFixedWingAirplaneId(Set<String> listOfFixedWingAirplaneId) {
	this.listOfFixedWingAirplaneId = listOfFixedWingAirplaneId;
    }

    /**
     * Add a FixedWing to listOfHelicoptersId
     * 
     * @param airplaneId
     * @return
     */
    public boolean addListOfFixedWingAirplaneId(String airplaneId) throws Exception {
	if (Validator.isId(Fixedwing.class, airplaneId)) {
	    if (this.listOfFixedWingAirplaneId.size() < this.maxFixedWingParkingPlace) {
		this.listOfFixedWingAirplaneId.add(airplaneId);
		return true;
	    } else {
		throw new Exception(Constant.FULL_PLACE_MESSAGE);
	    }
	}
	return false;
    }

    public int getMaxRotatedWingParkingPlace() {
	return maxRotatedWingParkingPlace;
    }

    public void setMaxRotatedWingParkingPlace(String maxRotatedWingParkingPlace) throws NumberFormatException {
	int n = Integer.parseInt(maxRotatedWingParkingPlace);
	if (n > 0) {
	    this.maxFixedWingParkingPlace = n;
	} else {
	    throw new NumberFormatException(Constant.NUMBER_OF_PLACE);
	}
    }

    public Set<String> getListOfHelicoptersId() {
	return listOfHelicoptersId;
    }

    public void setListOfHelicoptersId(Set<String> listOfHelicoptersId) {
	this.listOfHelicoptersId = listOfHelicoptersId;
    }

    /**
     * Add a Helicopter to listOfHelicoptersId
     * 
     * @param airplaneId
     * @return
     */
    public boolean addListOfHelicoptersId(String airplaneId) throws Exception {
	if (Validator.isId(Helicopter.class, airplaneId)) {
	    if (this.listOfHelicoptersId.size() < this.maxRotatedWingParkingPlace) {
		this.listOfHelicoptersId.add(airplaneId);
		return true;
	    } else {
		throw new Exception(Constant.FULL_PLACE_MESSAGE);
	    }

	}
	return false;
    }

    @Override
    public String toString() {
	return "Airport [id=" + id + ", name=" + name + ", runwaySize=" + runwaySize + ", maxFixedWingParkingPlace="
		+ maxFixedWingParkingPlace + ", listOfFixedWingAirplaneId=" + listOfFixedWingAirplaneId
		+ ", maxRotatedWingParkingPlace=" + maxRotatedWingParkingPlace + ", listOfHelicoptersId="
		+ listOfHelicoptersId + "]";
    }
}
