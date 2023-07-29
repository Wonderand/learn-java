package reflection.pojo;

public class Car {
    public String brand = "宝马";
    public String color = "黑色";
    public int price = 1000000;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
