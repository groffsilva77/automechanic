package mechanic.model;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car() {
        super();
        this.numberOfDoors = 0;
    }

    public Car(int id, String brand, String model, String plateNumber, Client owner, int numberOfDoors) {
        super(id, brand, model, plateNumber, owner);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getVehicleType() {
        return "Carro (" + numberOfDoors + " portas)";
    }
}
