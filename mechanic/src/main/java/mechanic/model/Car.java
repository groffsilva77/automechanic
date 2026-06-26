package mechanic.model;

public final class Car extends Vehicle {
    private int numberOfDoors;

    public Car() {
        super();
        this.numberOfDoors = 0;
    }

    public Car(int id, String plateNumber, String brand, String model, Client owner, int numberOfDoors) {
        super(id, plateNumber, brand, model, owner);
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
