package mechanic.model;

public class ServiceOrder {
    private int id;
    private String description;
    private double estimatedCost;
    private String status;
    private Vehicle vehicle;
    private Mechanic mechanic;

    public ServiceOrder() {
        this.id = 0;
        this.description = "";
        this.estimatedCost = 0.0;
        this.status = "Pendente";
        this.vehicle = null;
        this.mechanic = null;
    }

    public ServiceOrder(int id, String description, double estimatedCost, String status, Vehicle vehicle, Mechanic mechanic) {
        this.id = id;
        this.description = description;
        this.estimatedCost = estimatedCost;
        this.status = status;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
    }

    public String getSummary() {
        String vehicleModel = (this.vehicle != null) ? this.vehicle.getModel() : "Não informado";
        String vehicleType = (this.vehicle != null) ? this.vehicle.getVehicleType() : "N/A";
        
        String ownerName = "Não informado";
        if (this.vehicle != null && this.vehicle.getOwner() != null) {
            ownerName = this.vehicle.getOwner().getName();
        }

        String mechanicName = (this.mechanic != null) ? this.mechanic.getName() : "Não atribuído";

        return String.format("OS nº: %d | Status: %s\nCliente: %s | Veículo: %s (%s)\nMecânico Responsável: %s\nDescrição: %s\nValor: R$ %.2f",
            this.id,
            this.status,
            ownerName,
            vehicleModel,
            vehicleType,
            mechanicName,
            this.description,
            this.estimatedCost
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}