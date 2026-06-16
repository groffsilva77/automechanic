package mechanic.model;

public class Motorcycle extends Vehicle {
    private int cylinderCapacity;

    public Motorcycle() {
        super();
        this.cylinderCapacity = 0;
    }

    public Motorcycle(int id, String brand, String model, String plateNumber, Client owner, int cylinderCapacity) {
        super(id, brand, model, plateNumber, owner);
        this.cylinderCapacity = cylinderCapacity;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Moto (" + cylinderCapacity + "cc)";
    }
}
