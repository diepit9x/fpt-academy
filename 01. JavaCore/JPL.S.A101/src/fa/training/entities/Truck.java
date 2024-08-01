package fa.training.entities;

public class Truck extends Car{
    private int weight;

    public Truck() {
    }

    public Truck(int speed, double regularPrice, String color, int weight) {
        super(speed, regularPrice, color);
        this.weight = weight;
    }

    @Override
    public double getSalePrice() {
        return weight > 2000 ? getRegularPrice() * 0.9: getRegularPrice() * 0.8;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Truck {" + super.toString() +", salePrice = "+ getSalePrice() +", weight=" + weight +"}";
    }
}
