package mechanic.persistence;

import model.Client;
import java.util.List;

import javax.annotation.processing.SupportedSourceVersion;

import java.util.ArrayList;

public class ClientDAO implements GenericDAO<Client> {
    private static final List<com.sun.security.ntlm.Client> data = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public void save(mechanic.model.Client cliente) {
        cliente.setId(idCounter++);
        data.add(cliente);
    }

    @Override
    public List<Client> listALL() {
        return new ArrayList<>(data);
    }

    @Override
    public Client findByID(int id) {
        for (mechanic.model.Client c : data) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean update(mechanic.model.Client updatedClient) {
        mechanic.model.Client old = findByID(updatedClient.getId());
        if (old != null) {
            old.setName(updatedClient.getName());
            old.setPhoneNumber(updatedClient.getPhoneNumber());
            old.setEmail(updatedClient.getEmail());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Client c = findByID(id);
        if (c != null) {
            return data.remove(c);
        }
        return false;
    }
}
