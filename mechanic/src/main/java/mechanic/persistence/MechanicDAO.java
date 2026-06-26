package mechanic.persistence;

import mechanic.model.Mechanic;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MechanicDAO implements GenericDAO<Mechanic> {
    private static final List<Mechanic> data = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public void save(Mechanic mecanico) {
        if (mecanico == null) {
            throw new IllegalArgumentException("Não é possível salvar um mecânico nulo.");
        }
        for (Mechanic m : data) {
            if (m.getName().equalsIgnoreCase(mecanico.getName()) && m.getPhoneNumber().equals(mecanico.getPhoneNumber())) {
                throw new IllegalArgumentException("Mecânico já cadastrado com este nome e telefone.");
            }
        }
        mecanico.setId(idCounter.getAndIncrement());
        data.add(mecanico);
    }

    @Override
    public List<Mechanic> listAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Mechanic findById(int id) {
        for (Mechanic m : data) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean update(Mechanic updatedMechanic) {
        if (updatedMechanic == null) return false;

        Mechanic old = findById(updatedMechanic.getId());
        if (old != null) {
            old.setName(updatedMechanic.getName());
            old.setPhoneNumber(updatedMechanic.getPhoneNumber());
            old.setSpecialization(updatedMechanic.getSpecialization());
            old.setHourlyRate(updatedMechanic.getHourlyRate());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Mechanic m = findById(id);
        if (m != null) {
            return data.remove(m);
        }
        return false;
    }
}