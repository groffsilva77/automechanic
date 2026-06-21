package mechanic.persistence;

import java.util.ArrayList;

import java.util.List;

import mechanic.model.ServiceOrder;

public class ServiceOrderDAO implements GenericDAO<ServiceOrder> {
    private static final List<ServiceOrder> data = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public void save(ServiceOrder os) {
        os.setId(idCounter++);
        data.add(os);
    }

    @Override
    public List<ServiceOrder> listAll() {
        return new ArrayList<>(data);
    }

    @Override
    public ServiceOrder findById(int id) {
        for (ServiceOrder os : data) {
            if (os.getId() == id) {
                return os;
            }
        }
        return null;
    }

    @Override
    public boolean update(ServiceOrder updatedOS) {
        ServiceOrder old = findById(updatedOS.getId());
        if (old != null) {
            old.setDescription(updatedOS.getDescription());
            old.setEstimatedCost(updatedOS.getEstimatedCost());
            old.setStatus(updatedOS.getStatus());
            old.setVehicle(updatedOS.getVehicle());
            old.setMechanic(updatedOS.getMechanic());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ServiceOrder os = findById(id);
        if (os != null) {
            return data.remove(os);
        }
        return false;
    }
}
