package mechanic.persistence;

import mechanic.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleDAO implements GenericDAO<Vehicle> {
    private static final List<Vehicle> data = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public void save(Vehicle veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Não é possível salvar um veículo nulo.");
        }
        if (veiculo.getOwner() == null) {
            throw new IllegalArgumentException("Não é possível salvar um veículo sem um proprietário vinculado.");
        }
        
        for (Vehicle v : data) {
            if (v.getPlateNumber().equalsIgnoreCase(veiculo.getPlateNumber())) {
                throw new IllegalArgumentException("Já existe um veículo cadastrado com a placa: " + veiculo.getPlateNumber());
            }
        }
        
        veiculo.setId(idCounter.getAndIncrement());
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
        if (updatedVehicle == null) return false;

        Vehicle old = findById(updatedVehicle.getId());
        if (old != null) {
            for (Vehicle v : data) {
                if (v.getId() != updatedVehicle.getId() && v.getPlateNumber().equalsIgnoreCase(updatedVehicle.getPlateNumber())) {
                    throw new IllegalArgumentException("A placa " + updatedVehicle.getPlateNumber() + " já pertence a outro veículo.");
                }
            }
            if (updatedVehicle.getOwner() == null) {
                throw new IllegalArgumentException("O veículo precisa ter um proprietário.");
            }
            
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