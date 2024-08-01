package fa.training.entities;

public class Helicopter extends Airplane {
    private int range;

    public Helicopter() {
	super("rotated wing");
    }

    public Helicopter(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeOffWeight, int range) {
	super(id, model, cruiseSpeed, emptyWeight, maxTakeOffWeight, "rotated wing");
	this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(String range) throws NumberFormatException {
        this.range = Integer.parseInt(range);
    }

    @Override
    public String toString() {
	return "Helicopter [" + super.toString() + ", range=" + range + "]";
    }
    
}
