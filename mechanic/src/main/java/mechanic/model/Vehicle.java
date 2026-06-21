package mechanic.model;

public abstract sealed class Vehicle permits Car, Motorcycle {
    private int id;
    private String brand;
    private String model;
    private String plateNumber;
    private Client owner;

    public abstract String getVehicleType();

    public Vehicle() {
        this.id = 0;
        this.brand = "";
        this.model = "";
        this.plateNumber = "";
        this.owner = null;
    }

    public Vehicle(int id, String brand, String model, String plateNumber, Client owner) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
