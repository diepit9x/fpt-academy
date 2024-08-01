package fa.training.entities;

public class Ford extends Car{
    private int year;
    private int manufacetureDiscount;

    public Ford(int speed, double regularPrice, String color, int year, int manufacetureDiscount) {
        super(speed, regularPrice, color);
        this.year = year;
        this.manufacetureDiscount = manufacetureDiscount;
    }

    @Override
    public double getSalePrice() {
        return getRegularPrice()*(100 - manufacetureDiscount)*0.01 ;
    }

    @Override
    public String toString() {
        return "Ford{"+ super.toString() +", salePrice = "+ getSalePrice() +", manufacetureDiscount=" + manufacetureDiscount +", year=" + year +"}";
    }
}
