package mechanic.presentation;

import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

import mechanic.model.Client;
import mechanic.model.Mechanic;
import mechanic.model.Car;
import mechanic.model.Motorcycle;
import mechanic.model.Vehicle;
import mechanic.model.ServiceOrder;
import mechanic.persistence.ClientDAO;
import mechanic.persistence.MechanicDAO;
import mechanic.persistence.VehicleDAO;
import mechanic.persistence.ServiceOrderDAO;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientDAO clientDAO = new ClientDAO();
        MechanicDAO mechanicDAO = new MechanicDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        ServiceOrderDAO serviceDAO = new ServiceOrderDAO();

        while (true) {
            try {
                System.out.println("\n=== Bem-vindo à AutoSystem ===");
                System.out.println("O que você gostaria de fazer?");
                System.out.println("1. Gerenciar Clientes");
                System.out.println("2. Gerenciar Mecânicos");
                System.out.println("3. Gerenciar Veículos");
                System.out.println("4. Gerenciar Ordens de Serviço");
                System.out.println("0. Sair do Sistema");
                
                int option = readInt(scanner, "Digite a opção desejada: ");
    
                switch (option) {
                    case 1:
                        clientMenu(clientDAO, scanner);
                        break;
                    case 2:
                        mechanicMenu(mechanicDAO, scanner);
                        break;
                    case 3:
                        vehicleMenu(vehicleDAO, clientDAO, scanner);
                        break;
                    case 4:
                        serviceOrderMenu(serviceDAO, vehicleDAO, mechanicDAO, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nOcorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void clientMenu(ClientDAO clientDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Clientes ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Excluir Cliente");
        
        int op = readInt(scanner, "Opção: ");

        switch (op) {
            case 1:
                Client newClient = new Client();
                newClient.setName(readString(scanner, "Digite o nome do cliente: "));
                newClient.setPhoneNumber(readString(scanner, "Digite o telefone do cliente: "));
                newClient.setEmail(readString(scanner, "Digite o email do cliente: "));
                clientDAO.save(newClient);
                System.out.println("Cliente cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("\n--- Lista de Clientes ---");
                List<Client> clients = clientDAO.listAll();
                if (clients.isEmpty()) {
                    System.out.println("Nenhum cliente cadastrado.");
                } else {
                    for (Client c : clients) {
                        System.out.println("ID: " + c.getId() + " | Nome: " + c.getName() + " | Tel: " + c.getPhoneNumber() + " | Email: " + c.getEmail());
                    }
                }
                break;
            case 3:
                int editId = readInt(scanner, "Digite o ID do cliente que deseja editar: ");
                Client editCli = clientDAO.findById(editId);
                if (editCli == null) {
                    System.out.println("Cliente não encontrado!");
                } else {
                    editCli.setName(readString(scanner, "Novo nome (" + editCli.getName() + "): "));
                    editCli.setPhoneNumber(readString(scanner, "Novo telefone (" + editCli.getPhoneNumber() + "): "));
                    editCli.setEmail(readString(scanner, "Novo Email (" + editCli.getEmail() + "): "));

                    clientDAO.update(editCli);
                    System.out.println("Cliente atualizado com sucesso!");
                }
                break;
            case 4:
                int deleteId = readInt(scanner, "Digite o ID do cliente que deseja excluir: ");
                if (clientDAO.delete(deleteId)) {
                    System.out.println("Cliente excluído com sucesso!");
                } else {
                    System.out.println("Erro: Cliente não encontrado.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void mechanicMenu(MechanicDAO mechanicDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Mecânicos ===");
        System.out.println("1. Cadastrar Mecânico");
        System.out.println("2. Listar Mecânicos");
        System.out.println("3. Editar Mecânicos");
        System.out.println("4. Excluir Mecânicos");
        
        int op = readInt(scanner, "Opção: ");

        switch (op) {
            case 1:
                Mechanic newMechanic = new Mechanic();
                newMechanic.setName(readString(scanner, "Digite o nome do mecânico: "));
                newMechanic.setPhoneNumber(readString(scanner, "Digite o telefone do mecânico: "));
                newMechanic.setSpecialization(readString(scanner, "Digite a especialização do mecânico: "));
                newMechanic.setHourlyRate(readDouble(scanner, "Digite o valor da hora do mecânico: "));
                
                mechanicDAO.save(newMechanic);
                System.out.println("Mecânico cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("\n--- Lista de Mecânicos ---");
                List<Mechanic> mechanics = mechanicDAO.listAll();
                if (mechanics.isEmpty()) {
                    System.out.println("Nenhum mecânico cadastrado.");
                } else {
                    for (Mechanic m : mechanics) {
                        System.out.println("ID: " + m.getId() + " | Nome: " + m.getName() + " | Esp: " + m.getSpecialization() + " | Valor/Hora: R$ " + m.getHourlyRate());
                    }
                }
                break;
            case 3:
                int editId = readInt(scanner, "Digite o ID do mecânico que quer editar: ");
                Mechanic editMechanic = mechanicDAO.findById(editId);
                if (editMechanic == null) {
                    System.out.println("Mecânico não encontrado!");
                } else {
                    editMechanic.setName(readString(scanner, "Novo nome (" + editMechanic.getName() + "): "));
                    editMechanic.setPhoneNumber(readString(scanner, "Novo telefone (" + editMechanic.getPhoneNumber() + "): "));
                    editMechanic.setSpecialization(readString(scanner, "Nova especialização (" + editMechanic.getSpecialization() + "): "));
                    editMechanic.setHourlyRate(readDouble(scanner, "Novo valor da hora (R$ " + editMechanic.getHourlyRate() + "): "));
                    
                    mechanicDAO.update(editMechanic);
                    System.out.println("Mecânico atualizado com sucesso!");
                }
                break;
            case 4:
                int deleteId = readInt(scanner, "Digite o ID do mecânico que deseja excluir: ");
                if (mechanicDAO.delete(deleteId)) {
                    System.out.println("Mecânico excluído com sucesso!");
                } else {
                    System.out.println("Erro: Mecânico não encontrado.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void vehicleMenu(VehicleDAO vehicleDAO, ClientDAO clientDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Veículos ===");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Listar Veículos");
        System.out.println("3. Editar Veículos");
        System.out.println("4. Excluir Veículos");
        
        int op = readInt(scanner, "Opção: ");

        switch (op) {
            case 1:
                int clientId = readInt(scanner, "Digite o ID do cliente proprietário do veículo: ");
                Client client = clientDAO.findById(clientId);
                if (client == null) {
                    System.out.println("Cliente não encontrado! Cadastre o cliente primeiro.");
                } else {
                    System.out.println("Qual o tipo do veículo?");
                    System.out.println("1 - Carro");
                    System.out.println("2 - Moto");
                    int type = readInt(scanner, "Opção: ");

                    String plateNum = readString(scanner, "Digite a placa: ");
                    String brand = readString(scanner, "Digite a marca: ");
                    String model = readString(scanner, "Digite o modelo: ");

                    if (type == 1) {
                        int doors = readInt(scanner, "Digite o número de portas: ");
                        Car newCar = new Car(0, plateNum, brand, model, client, doors);
                        vehicleDAO.save(newCar);
                        System.out.println("Carro cadastrado com sucesso para o cliente " + client.getName() + "!");
                    } else if (type == 2) {
                        int cc = readInt(scanner, "Digite as cilindradas (cc): ");
                        Motorcycle newMotorcycle = new Motorcycle(0, plateNum, brand, model, client, cc);
                        vehicleDAO.save(newMotorcycle);
                        System.out.println("Moto cadastrada com sucesso para o cliente " + client.getName() + "!");
                    } else {
                        System.out.println("Opção inválida! Cadastro cancelado.");
                    }
                }
                break;

            case 2:
                System.out.println("\n--- Lista de Veículos ---");
                List<Vehicle> vehicles = vehicleDAO.listAll();
                if (vehicles.isEmpty()) {
                    System.out.println("Nenhum veículo cadastrado.");
                } else {
                    for (Vehicle v : vehicles) {
                        String ownerName = (v.getOwner() != null) ? v.getOwner().getName() : "Sem Proprietário";
                        System.out.println("ID: " + v.getId() + " | Modelo: " + v.getModel() + " | Tipo: " + v.getVehicleType() + " | Dono: " + ownerName);
                    }
                }
                break;
            case 3:
                int editId = readInt(scanner, "Digite o ID do veículo que quer editar: ");
                Vehicle editVehicle = vehicleDAO.findById(editId);
                if (editVehicle == null) {
                    System.out.println("Veículo não encontrado");
                } else {
                    editVehicle.setPlateNumber(readString(scanner, "Nova placa (" + editVehicle.getPlateNumber() + "): "));
                    editVehicle.setBrand(readString(scanner, "Nova marca (" + editVehicle.getBrand() + "): "));
                    editVehicle.setModel(readString(scanner, "Novo modelo (" + editVehicle.getModel() + "): "));

                    if (editVehicle instanceof Car) {
                        Car car = (Car) editVehicle;
                        car.setNumberOfDoors(readInt(scanner, "Novo número de portas (" + car.getNumberOfDoors() + "): "));
                    } else if (editVehicle instanceof Motorcycle) {
                        Motorcycle moto = (Motorcycle) editVehicle;
                        moto.setCylinderCapacity(readInt(scanner, "Novas cilindradas (" + moto.getCylinderCapacity() + "): "));
                    }
                    vehicleDAO.update(editVehicle);
                    System.out.println("Veículo atualizado com sucesso");
                }
                break;
            case 4:
                int deleteId = readInt(scanner, "Digite o ID do veículo que deseja excluir: ");
                if (vehicleDAO.delete(deleteId)) {
                    System.out.println("Veículo excluído com sucesso!");
                } else {
                    System.out.println("Erro: Veículo não encontrado.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void serviceOrderMenu(ServiceOrderDAO serviceDAO, VehicleDAO vehicleDAO, MechanicDAO mechanicDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Ordens de Serviço ===");
        System.out.println("1. Abrir Nova Ordem de Serviço");
        System.out.println("2. Listar Todas as Ordens de Serviço");
        System.out.println("3. Editar Ordem de Serviço");
        System.out.println("4. Excluir Ordem de Serviço");
        
        int op = readInt(scanner, "Opção: ");

        switch (op) {
            case 1:
                int vehicleId = readInt(scanner, "Digite o ID do veículo: ");
                Vehicle securedVehicle = vehicleDAO.findById(vehicleId);
                
                if (securedVehicle == null) {
                    System.out.println("Erro: Veículo não encontrado! Verifique o ID.");
                } else {
                    int mechanicID = readInt(scanner, "Digite o ID do mecânico responsável: ");
                    Mechanic respMechanic = mechanicDAO.findById(mechanicID);

                    if (respMechanic == null) {
                        System.out.println("Erro: Mecânico não encontrado! Verifique o ID.");
                    } else {
                        String description = readString(scanner, "Descrição do problema/serviço: ");
                        double value = readDouble(scanner, "Valor estimado do serviço (R$): ");

                        String startStatus = "Pendente";

                        ServiceOrder newOS = new ServiceOrder(0, description, value, startStatus, securedVehicle, respMechanic);
                        serviceDAO.save(newOS);

                        System.out.println("\nOrdem de Serviço aberta com sucesso!");
                        System.out.println("\n--- Resumo da OS gerada ---");
                        System.out.println(newOS.getSummary());
                    }
                }
                break;

            case 2:
                System.out.println("\n--- Todas as Ordens de Serviço ---");
                List<ServiceOrder> orders = serviceDAO.listAll();
                if (orders.isEmpty()) {
                    System.out.println("Nenhuma ordem de serviço cadastrada.");
                } else {
                    for (ServiceOrder os : orders) {
                        System.out.println(os.getSummary());
                        System.out.println("----------------------------------------");
                    }
                }
                break;
            case 3:
                int editId = readInt(scanner, "Digite o ID da ordem de serviço que deseja editar: ");
                ServiceOrder serviceEdit = serviceDAO.findById(editId);
                if (serviceEdit == null) {
                    System.out.println("Ordem de serviço não encontrada. Verifique o ID");
                } else {
                    serviceEdit.setDescription(readString(scanner, "Nova descrição (" + serviceEdit.getDescription() + "): "));
                    serviceEdit.setEstimatedCost(readDouble(scanner, "Novo valor estimado (R$ " + serviceEdit.getEstimatedCost() + "): "));

                    System.out.println("Escolha o novo Status:");
                    System.out.println("1 - Pendente");
                    System.out.println("2 - Em Andamento");
                    System.out.println("3 - Concluído");
                    
                    int statusOp = readInt(scanner, "Opção: ");

                    if (statusOp == 1) {
                        serviceEdit.setStatus("Pendente");
                    } else if (statusOp == 2) {
                        serviceEdit.setStatus("Em Andamento");
                    } else if (statusOp == 3) {
                        serviceEdit.setStatus("Concluído");
                    } else {
                        System.out.println("Opção de status inválida! Mantendo o status anterior.");
                    }

                    serviceDAO.update(serviceEdit);
                    System.out.println("Ordem de Serviço atualizada com sucesso!");
                }
                break;
            case 4:
                int deleteId = readInt(scanner, "Digite o ID da ordem de serviço que deseja excluir: ");
                if (serviceDAO.delete(deleteId)) {
                    System.out.println("Ordem de serviço excluída com sucesso!");
                } else {
                    System.out.println("Erro: Ordem de serviço não encontrada.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Erro: O campo não pode ser vazio!");
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 0) {
                    return value;
                }
                System.out.println("Erro: Digite um número maior ou igual a zero!");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
                scanner.nextLine();
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value >= 0) {
                    return value;
                }
                System.out.println("Erro: O valor não pode ser negativo!");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite um número decimal válido.");
                scanner.nextLine();
            }
        }
    }
}