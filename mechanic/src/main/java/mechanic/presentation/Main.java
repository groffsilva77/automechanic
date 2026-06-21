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
                System.out.println("=== Bem-vindo à AutoSystem ===");
                System.out.println("O que você gostaria de fazer?");
                System.out.println("1. Gerenciar Clientes");
                System.out.println("2. Gerenciar Mecânicos");
                System.out.println("3. Gerenciar Veículos");
                System.out.println("4. Gerenciar Ordens de Serviço");
                System.out.println("0. Sair do Sistema");
                System.out.print("Digite a opção desejada: ");
                int option = scanner.nextInt();
                scanner.nextLine();
    
                switch (option) {
                    case 1:
                        clientMenu(clientDAO, scanner);
                        break;
                    case 2:
                        mechanicMenu(mechanicDAO, scanner);
                        break;
                    case 3:
                        vehicleMenu(vehicleDAO, clientDAO, scanner);
                    case 4:
                        serviceOrderMenu(serviceDAO, vehicleDAO, mechanicDAO, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n ERRO: Você deve digitar um número inteiro válido!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n Ocorreu um erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void clientMenu(ClientDAO clientDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Clientes ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Excluir Cliente");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                Client newClient = new Client();
                System.out.print("Digite o nome do cliente: ");
                newClient.setName(scanner.nextLine());
                System.out.print("Digite o telefone do cliente: ");
                newClient.setPhoneNumber(scanner.nextLine());
                System.out.print("Digite o email do cliente: ");
                newClient.setEmail(scanner.nextLine());
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
                System.out.print("Digite o ID do cliente que deseja editar: ");
                int editId = scanner.nextInt();
                scanner.nextLine();
                Client editCli = clientDAO.findById(editId);
                if (editCli == null) {
                    System.out.println("Cliente não encontrado!");
                } else {
                    System.out.print("Novo nome (" + editCli.getName() + "): ");
                    editCli.setName(scanner.nextLine());
                    System.out.print("Novo telefone (" + editCli.getPhoneNumber() + "): ");
                    editCli.setPhoneNumber(scanner.nextLine());
                    System.out.print("Novo Email (" + editCli.getEmail() + "): ");
                    editCli.setEmail(scanner.nextLine());

                    clientDAO.update(editCli);
                    System.out.println("Cliente atualizado com sucesso!");
                }
                break;
            case 4:
                System.out.print("Digite o ID do cliente que deseja excluir: ");
                int deleteId = scanner.nextInt();
                scanner.nextLine();
                if (clientDAO.delete(deleteId)) {
                    System.out.println("Cliente excluído com sucesso!");
                } else {
                    System.out.println("Erro: Cliente não encontrado.");
                }
                break;
            default:
                break;
        }
    }

    private static void mechanicMenu(MechanicDAO mechanicDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Mecânicos ===");
        System.out.println("1. Cadastrar Mecânico");
        System.out.println("2. Listar Mecânicos");
        System.out.println("3. Editar Mecânicos");
        System.out.println("4. Excluir Mecânicos");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                Mechanic newMechanic = new Mechanic();
                System.out.print("Digite o nome do mecânico: ");
                newMechanic.setName(scanner.nextLine());
                System.out.print("Digite o telefone do mecânico: ");
                newMechanic.setPhoneNumber(scanner.nextLine());
                System.out.print("Digite a especialização do mecânico: ");
                newMechanic.setSpecialization(scanner.nextLine());
                System.out.print("Digite o valor da hora do mecânico: ");
                newMechanic.setHourlyRate(scanner.nextDouble());
                scanner.nextLine();
                mechanicDAO.save(newMechanic);
                System.out.println("Mecânico cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("\n--- Lista de Mecânicos ---");
                for (Mechanic m : mechanicDAO.listAll()) {
                    System.out.println("ID: " + m.getId() + " | Nome: " + m.getName() + " | Esp: " + m.getSpecialization());
                }
                break;
            case 3:
                System.out.print("Digite o ID do mecânico que quer editar: ");
                int editId = scanner.nextInt();
                scanner.nextLine();
                Mechanic editMechanic = mechanicDAO.findById(editId);
                if (editMechanic == null) {
                    System.out.println("Mecânico não encontrado!");
                } else {
                    System.out.print("Novo nome (" + editMechanic.getName() + "): ");
                    editMechanic.setName(scanner.nextLine());
                    System.out.print("Novo telefone (" + editMechanic.getPhoneNumber() + "): ");
                    editMechanic.setPhoneNumber(scanner.nextLine());
                    System.out.print("Nova especialização (" + editMechanic.getSpecialization() + "): ");
                    editMechanic.setSpecialization(scanner.nextLine());
                    System.out.print("Novo valor da hora (R$ " + editMechanic.getHourlyRate() + "): ");
                    editMechanic.setHourlyRate(scanner.nextDouble());
                    scanner.nextLine();
                }
                break;
            case 4:
                System.out.print("Digite o ID do mecânico que deseja excluir: ");
                int deleteId = scanner.nextInt();
                scanner.nextLine();
                if (mechanicDAO.delete(deleteId)) {
                    System.out.println("Mecânico excluído com sucesso!");
                } else {
                    System.out.println("Erro: Mecânico não encontrado.");
                }
                break;
            default:
                break;
        }
    }

    private static void vehicleMenu(VehicleDAO vehicleDAO, ClientDAO clientDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Veículos ===");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Listar Veículos");
        System.out.println("3. Editar Veículos");
        System.out.println("4. Excluir Veículos");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("Digite o ID do cliente proprietário do veículo: ");
                int clientId = scanner.nextInt();
                scanner.nextLine();
                Client client = clientDAO.findById(clientId);
               if (client == null) {
                    System.out.println("Cliente não encontrado! Cadastre o cliente primeiro.");
                } else {
                    System.out.println("Qual o tipo do veículo?");
                    System.out.println("1 - Carro");
                    System.out.println("2 - Moto");
                    System.out.print("Opção: ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite a placa: ");
                    String plateNum = scanner.nextLine();
                    System.out.print("Digite a marca: ");
                    String brand = scanner.nextLine();
                    System.out.print("Digite o modelo: ");
                    String model = scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Digite o número de portas: ");
                        int doors = scanner.nextInt();
                        scanner.nextLine();

                        Car newCar = new Car(0, plateNum, brand, model, client, doors);
                        vehicleDAO.save(newCar);
                        System.out.println("Carro cadastrado com sucesso para o cliente " + client.getName() + "!");
                    } else if (type == 2) {
                        System.out.print("Digite as cilindradas (cc): ");
                        int cc = scanner.nextInt();
                        scanner.nextLine();
                        
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
                for (Vehicle v : vehicleDAO.listAll()) {
                    System.out.println("ID: " + v.getId() + " | Modelo: " + v.getModel() + " | Tipo: " + v.getVehicleType() + " | Dono: " + v.getOwner().getName());
                }
                break;
            case 3:
                System.out.print("Digite o ID do veículo que quer editar: ");
                int editId = scanner.nextInt();
                scanner.nextLine();
                Vehicle editVehicle = vehicleDAO.findById(editId);
                if (editVehicle == null) {
                    System.out.println("Veículo não encontrado");
                } else {
                    System.out.print("Nova placa (" + editVehicle.getPlateNumber() + "): ");
                    editVehicle.setPlateNumber(scanner.nextLine());
                    System.out.print("Nova marca (" + editVehicle.getBrand() + "): ");
                    editVehicle.setBrand(scanner.nextLine());
                    System.out.print("Novo modelo (" + editVehicle.getModel() + "): ");
                    editVehicle.setModel(scanner.nextLine());

                    if (editVehicle instanceof Car) {
                        Car car = (Car) editVehicle;
                        System.out.print("Novo número de portas (" + car.getNumberOfDoors() + "): ");
                        car.setNumberOfDoors(scanner.nextInt());
                        scanner.nextLine();
                    } else if (editVehicle instanceof Motorcycle) {
                        Motorcycle moto = (Motorcycle) editVehicle;
                        System.out.print("Novas cilindradas (" + moto.getCylinderCapacity() + "): ");
                        moto.setCylinderCapacity(scanner.nextInt());
                        scanner.nextLine();
                    }
                    vehicleDAO.update(editVehicle);
                    System.out.println("Veículo atualizado com sucesso");
                }
                break;
            case 4:
                System.out.print("Digite o ID do veículo que deseja excluir: ");
                int deleteId = scanner.nextInt();
                scanner.nextLine();
                if (vehicleDAO.delete(deleteId)) {
                    System.out.println("Veículo excluído com sucesso!");
                } else {
                    System.out.println("Erro: Veículo não encontrado.");
                }
                break;
            default:
                break;
        }
    }

    private static void serviceOrderMenu(ServiceOrderDAO serviceDAO, VehicleDAO vehicleDAO, MechanicDAO mechanicDAO, Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Ordens de Serviço ===");
        System.out.println("1. Abrir Nova Ordem de Serviço");
        System.out.println("2. Listar Todas as Ordens de Serviço");
        System.out.println("3. Editar Ordem de Serviço");
        System.out.println("4. Excluir Ordem de Serviço");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("Digite o ID do veículo: ");
                int vehicleId = scanner.nextInt();
                scanner.nextLine();
                Vehicle securedVehicle = vehicleDAO.findById(vehicleId);
                
                if (securedVehicle == null) {
                    System.out.println("Erro: Veículo não encontrado! Verifique o ID.");
                } else {
                    System.out.print("Digite o ID do mecânico responsável: ");
                    int mechanicID = scanner.nextInt();
                    scanner.nextLine();

                    Mechanic respMechanic = mechanicDAO.findById(mechanicID);

                    if (respMechanic == null) {
                        System.out.println("Erro: Mecânico não encontrado! Verifique o ID.");
                    } else {
                        System.out.print("Descrição do problema/serviço: ");
                        String description = scanner.nextLine();
                        System.out.print("Valor estimado do serviço (R$): ");
                        double value = scanner.nextDouble();
                        scanner.nextLine();

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
                for (ServiceOrder os : serviceDAO.listAll()) {
                    System.out.println(os.getSummary());
                    System.out.println("----------------------------------------");
                }
                break;
            case 3:
                System.out.print("Digite o ID da ordem de serviço que deseja editar: ");
                int editId = scanner.nextInt();
                scanner.nextLine();
                ServiceOrder serviceEdit = serviceDAO.findById(editId);
                if (serviceEdit == null) {
                    System.out.println("Ordem de serviço não encontrada. Verifique o ID");
                } else {
                    System.out.print("Nova descrição (" + serviceEdit.getDescription() + "): ");
                    serviceEdit.setDescription(scanner.nextLine());
                    System.out.print("Novo valor estimado (R$ " + serviceEdit.getEstimatedCost() + "): ");
                    serviceEdit.setEstimatedCost(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.println("Escolha o novo Status:");
                    System.out.println("1 - Pendente");
                    System.out.println("2 - Em Andamento");
                    System.out.println("3 - Concluído");
                    System.out.print("Opção: ");
                    int statusOp = scanner.nextInt();
                    scanner.nextLine();

                    if (statusOp == 1) serviceEdit.setStatus("Pendente");
                    else if (statusOp == 2) serviceEdit.setStatus("Em Andamento");
                    else if (statusOp == 3) serviceEdit.setStatus("Concluído");

                    serviceDAO.update(serviceEdit);
                    System.out.println("Ordem de Serviço atualizada");
                }
                break;
            case 4:
                System.out.print("Digite o ID da ordem de serviço que deseja excluir: ");
                int deleteId = scanner.nextInt();
                scanner.nextLine();
                if (serviceDAO.delete(deleteId)) {
                    System.out.println("Ordem de serviço excluída com sucesso!");
                } else {
                    System.out.println("Erro: Ordem de serviço não encontrada.");
                }
                break;
            default:
                break;
        }
    }
}
