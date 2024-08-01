package baitap1.cach1;

public class Shape {
    private String color;
    
    public Shape(String color) {
	this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String draw() {
	return "I'm a " + this.color + " shape.";
    }

}
