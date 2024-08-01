package baitap1.cach3;

public class Shape {
    private String color;
    private String type;
    
    public Shape(String color) {
	this.color = color;
	this.type = "shape";
    }
    

    public Shape(String color, String type) {
	super();
	this.color = color;
	this.type = type;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String draw() {
	return "I'm a " + this.color + " " + getType();
    }
}

