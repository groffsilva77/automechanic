package mechanic.persistence;

import mechanic.model.ServiceOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceOrderDAO implements GenericDAO<ServiceOrder> {
    private static final List<ServiceOrder> data = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public void save(ServiceOrder os) {
        if (os == null) {
            throw new IllegalArgumentException("Não é possível salvar uma Ordem de Serviço nula.");
        }
        if (os.getVehicle() == null) {
            throw new IllegalArgumentException("Ordem de serviço inválida: nenhuma referência de veículo encontrada.");
        }
        if (os.getMechanic() == null) {
            throw new IllegalArgumentException("Ordem de serviço inválida: nenhum mecânico responsável foi atribuído.");
        }
        
        os.setId(idCounter.getAndIncrement());
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
        if (updatedOS == null) return false;

        ServiceOrder old = findById(updatedOS.getId());
        if (old != null) {
            if (updatedOS.getVehicle() == null || updatedOS.getMechanic() == null) {
                throw new IllegalArgumentException("Atualização falhou: Veículo e Mecânico são obrigatórios na OS.");
            }
            
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