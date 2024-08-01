package fa.training.entities;

public class Sedan extends Car{
    private int length;

    public Sedan() {
    }

    public Sedan(int speed, double regularPrice, String color, int length) {
        super(speed, regularPrice, color);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public double getSalePrice() {
        return length > 20 ? getRegularPrice() * 0.95 : getRegularPrice() * 0.9;
    }

    @Override
    public String toString() {
        return "Sedan{" + super.toString()+", salePrice = "+ getSalePrice() +", length=" + length +
                "}";
    }
}
