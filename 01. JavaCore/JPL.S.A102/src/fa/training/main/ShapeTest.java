package fa.training.main;

import fa.training.entities.Rectangle;
import java.util.Scanner;

public class ShapeTest {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("n = ");
        int n = sc.nextInt();

        //a
        Rectangle[] rectangles = new Rectangle[n];
        for (int i = 0; i < n; i++) {
            System.out.print("(length width)["+i+"] = ");
            rectangles[i] = new Rectangle();
            rectangles[i].setLengthWidth(sc.nextInt(), sc.nextInt());
        }

        //b
        for (int i = 0; i < n; i++) {
            System.out.println(rectangles[i].toString());
        }

        //C
        Rectangle maxRectangle = rectangles[0];
        for (int i = 1; i < n; i++) {
            maxRectangle = rectangles[i].calculateArea() > maxRectangle.calculateArea()
                    ? rectangles[i]: maxRectangle;
        }
        System.out.println("Max area = " + maxRectangle.toString());

        //d
        Rectangle minRectangle = rectangles[0];
        for (int i = 1; i < n; i++) {
            maxRectangle = rectangles[i].calculatePerimeter() < maxRectangle.calculatePerimeter()
                    ? rectangles[i]: maxRectangle;
        }
        System.out.println("Min perimeter = " + minRectangle.toString());

    }
}
