package mechanic.model;

public class Client extends Person{
    private String email;

    public Client() {
        super();
        this.email = "";
    }

    public Client(int id, String name, String phoneNumber, String email) {
        super(id, name, phoneNumber);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
