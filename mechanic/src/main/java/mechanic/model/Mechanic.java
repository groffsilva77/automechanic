package mechanic.model;

public class Mechanic extends Person {
    private String specialization;
    private double hourlyRate;

    public Mechanic() {
        super();
        this.specialization = "";
        this.hourlyRate = 0.0;
    }

    public Mechanic(int id, String name, String phoneNumber, String specialization, double hourlyRate) {
        super(id, name, phoneNumber);
        this.specialization = specialization;
        this.hourlyRate = hourlyRate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
