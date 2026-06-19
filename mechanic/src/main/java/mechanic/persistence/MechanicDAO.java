package mechanic.persistence;

import model.Mechanic;
import java.util.ArrayList;
import java.util.List;

public class MechanicDAO implements GenericDAO<Mechanic> {
    private static final List<Mechanic> data = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public void save(mechanic.model.Mechanic mecanico) {
        mecanico.setId(idCounter++);
        data.add(mecanico);
    }

    @Override
    public List<Mechanic> listAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Mechanic findById(int id) {
        for (mechanic.model.Mechanic m : data) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean update(mechanic.model.Mechanic updatedMechanic) {
        mechanic.model.Mechanic old = findById(updatedMechanic.getId());
        if (old != null) {
            old.setName(updatedMechanic.getName());
            old.setPhoneNumber(updatedMechanic.getPhoneNumber());
            old.setSpecialty(updatedMechanic.getSpecialty());
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
