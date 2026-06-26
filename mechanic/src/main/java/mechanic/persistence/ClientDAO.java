package mechanic.persistence;

import mechanic.model.Client;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientDAO implements GenericDAO<Client> {
    private static final List<Client> data = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public void save(Client cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Não é possível salvar um cliente nulo.");
        }
        for (Client c : data) {
            if (c.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com o e-mail: " + cliente.getEmail());
            }
        }
        cliente.setId(idCounter.getAndIncrement());
        data.add(cliente);
    }

    @Override
    public List<Client> listAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Client findById(int id) {
        for (Client c : data) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean update(Client updatedClient) {
        if (updatedClient == null) return false;
        
        Client old = findById(updatedClient.getId());
        if (old != null) {
            for (Client c : data) {
                if (c.getId() != updatedClient.getId() && c.getEmail().equalsIgnoreCase(updatedClient.getEmail())) {
                    throw new IllegalArgumentException("O e-mail " + updatedClient.getEmail() + " já está em uso por outro cliente.");
                }
            }
            old.setName(updatedClient.getName());
            old.setPhoneNumber(updatedClient.getPhoneNumber());
            old.setEmail(updatedClient.getEmail());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Client c = findById(id);
        if (c != null) {
            return data.remove(c);
        }
        return false;
    }
}