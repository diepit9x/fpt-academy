package fa.training.entities;

import fa.training.exceptions.IncorrectFormatException;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public abstract class Airplane {
    private String id;
    private String model;
    private double cruiseSpeed;
    private double emptyWeight;
    private double maxTakeOffWeight;
    private String flyMethod;

    public Airplane(String flyMethod) {
	this.flyMethod = flyMethod;
    }
    
    public Airplane(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeOffWeight, String flyMethod) {
	super();
	this.id = id;
	this.model = model;
	this.cruiseSpeed = cruiseSpeed;
	this.emptyWeight = emptyWeight;
	this.maxTakeOffWeight = maxTakeOffWeight;
	this.flyMethod = flyMethod;
    }

    public String getId() {
        return id;
    }

    /**
     * Validate and set airplane id
     * @param id
     * @throws IncorrectFormatException
     */
    public void setId(String id) throws IncorrectFormatException {
	if (Validator.isId(getClass(), id)) {
	    this.id = id;
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_ID_MESSAGE);
	}
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    /**
     * Validate and set airplane model
     * @param model
     * @throws IncorrectFormatException
     */
    public void setModel(String model) throws IncorrectFormatException  {
	if (Validator.isModel(model)) {
	        this.model = model;
	} else {
	    throw new IncorrectFormatException(Constant.INCORRECT_MODEL_MESSAGE);
	}
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(String cruiseSpeed) throws NumberFormatException {
        this.cruiseSpeed = Double.parseDouble(cruiseSpeed);
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(String emptyWeight) throws NumberFormatException {
        this.emptyWeight = Double.parseDouble(emptyWeight);
    }

    public double getMaxTakeOffWeight() {
        return maxTakeOffWeight;
    }

    public void setMaxTakeOffWeight(String maxTakeOffWeight) throws NumberFormatException {
        this.maxTakeOffWeight = Double.parseDouble(maxTakeOffWeight);
    }

    public String getFlyMethod() {
        return flyMethod;
    }

    @Override
    public String toString() {
	return "id=" + id + ", model=" + model + ", cruiseSpeed=" + cruiseSpeed + ", emptyWeight="
		+ emptyWeight + ", maxTakeOffWeight=" + maxTakeOffWeight + ", flyMethod=" + flyMethod;
    }
    
    
}
