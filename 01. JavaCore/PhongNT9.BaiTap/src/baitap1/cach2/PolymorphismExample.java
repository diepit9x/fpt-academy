package baitap1.cach2;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismExample {
    private List<Shape> shapes = new ArrayList<>();

    public PolymorphismExample() {
	Shape shape = new Shape("Yellow");
	Shape myFirstCircle = new Circle("Red");
	Circle mySecondCircle = new Circle("Blue");
	Rectangle myFirstRectangle = new Rectangle("Green");
	shapes.add(shape);
	shapes.add(myFirstCircle);
	shapes.add(mySecondCircle);
	shapes.add(myFirstRectangle);
    }

    public List<Shape> getShapes() {
	return shapes;
    }

    public static void main(String[] args) {
	PolymorphismExample example = new PolymorphismExample();
	for (Shape shape : example.getShapes()) {
	    System.out.println(shape.draw());
	}
    }

}

