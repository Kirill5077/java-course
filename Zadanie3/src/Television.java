public class Television {
    private String brand;
    private int size;
    private boolean isOn;

    public Television(String brand, int size) {
        this.brand = brand;
        this.size = size;
        this.isOn = false;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(brand + " TV включен.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(brand + " TV выключен.");
    }

    public void displayInfo() {
        System.out.println("Марка: " + brand + ", Размер: " + size + ", Состояние: " + (isOn ? "Включен" : "Выключен"));
    }
}
