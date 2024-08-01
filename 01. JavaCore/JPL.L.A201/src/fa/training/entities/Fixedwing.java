package fa.training.entities;

import fa.training.exceptions.IncorrectFormatException;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class Fixedwing extends Airplane {
    private String planeType;
    private double minNeededRunwaySize;
    
    public Fixedwing() {
	super("fixed wing");
    }

    public Fixedwing(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeOffWeight, String planeType, double minNeededRunwaySize) {
	super(id, model, cruiseSpeed, emptyWeight, maxTakeOffWeight, "fixed wing");
	this.planeType = planeType;
	this.minNeededRunwaySize = minNeededRunwaySize;
    }

    public String getPlaneType() {
        return planeType;
    }
    
    /**
     * Valid and set plane type
     * @param planeType
     * @throws IncorrectFormatException
     */
    public void setPlaneType(String planeType) throws IncorrectFormatException  {
	if (Validator.isPlaneType(planeType)) {
	    this.planeType = planeType;
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_PLANE_TYPE_MESSAGE);
	}
    }

    public double getMinNeededRunwaySize() {
        return minNeededRunwaySize;
    }

    public void setMinNeededRunwaySize(String minNeededRunwaySize) throws NumberFormatException {
        this.minNeededRunwaySize = Double.parseDouble(minNeededRunwaySize);
    }

    @Override
    public String toString() {
	return "Fixedwing [" + super.toString() + ", planeType=" + planeType + ", minNeededRunwaySize=" + minNeededRunwaySize + "]";
    }

}
