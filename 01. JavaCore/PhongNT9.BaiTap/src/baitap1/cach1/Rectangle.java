package baitap1.cach1;

public class Rectangle extends Shape {

    public Rectangle(String color) {
	super(color);
    }

    @Override
    public String draw() {
	return "I'm a " + this.getColor() + " rectangle.";
    }

}
