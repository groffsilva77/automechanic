package mechanic.persistence;

import model.Vehicle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.SupportedSourceVersion;

public class VehicleDAO implements GenericDAO<Vehicle> {
    private static final List<Vehicle> data = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public void save(Vehicle veiculo) {
        veiculo.setId(idCounter++);
        data.add(veiculo);
    }

    @Override
    public List<Vehicle> listAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Vehicle findById(int id) {
        for (Vehicle v : data) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean update(Vehicle updatedVehicle) {
        Vehicle old = findById(updatedVehicle.getId());
        if (old != null) {
            old.setBrand(updatedVehicle.getBrand());
            old.setModel(updatedVehicle.getModel());
            old.setPlateNumber(updatedVehicle.getPlateNumber());
            old.setOwner(updatedVehicle.getOwner());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Vehicle v = findById(id);
        if (v != null) {
            return data.remove(v);
        }
        return false;
    }
}
