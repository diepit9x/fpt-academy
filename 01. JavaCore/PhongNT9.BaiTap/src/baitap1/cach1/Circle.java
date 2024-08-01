package baitap1.cach1;

public class Circle extends Shape {

    public Circle(String color) {
	super(color);
    }

    @Override
    public String draw() {
	return "I'm a " + this.getColor() + " circle.";
    }
    

}
