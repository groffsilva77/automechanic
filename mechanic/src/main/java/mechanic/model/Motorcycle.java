package mechanic.model;

public final class Motorcycle extends Vehicle {
    private int cylinderCapacity;

    public Motorcycle() {
        super();
        this.cylinderCapacity = 0;
    }

    public Motorcycle(int id,  String plateNumber, String brand, String model, Client owner, int cylinderCapacity) {
        super(id, plateNumber, brand, model, owner);
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
