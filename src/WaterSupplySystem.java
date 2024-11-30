import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class WaterSupplySystem {
    private static final String MASTERS_FILE = "masters.json";
    private static final String BRIGADES_FILE = "brigades.json";
    private static final String CLIENTS_FILE = "clients.json";
    private static final String ACCIDENTS_FILE = "accidents.json";
    private List<Master> masters = new ArrayList<>();
    private List<Accident> accidents = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    // Конструктор, який зчитує дані з JSON файлів
    public WaterSupplySystem() {
        loadMasters();
        loadClients();
        loadAccidents();
    }

    // Завантажуємо дані з файлу майстрів
    private void loadMasters() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            masters = objectMapper.readValue(
                    new File(MASTERS_FILE),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Master.class)
            );
        } catch (IOException e) {
            masters = new ArrayList<>();
            System.out.println("Помилка завантаження даних майстрів: " + e.getMessage());
        }
    }

    // Завантажуємо дані з файлу клієнтів
    private void loadClients() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            clients = objectMapper.readValue(
                    new File(CLIENTS_FILE),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class)
            );
        } catch (IOException e) {
            clients = new ArrayList<>();
            System.out.println("Помилка завантаження даних клієнтів: " + e.getMessage());
        }
    }

    // Завантажуємо дані з файлу аварій
    private void loadAccidents() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            accidents = new ArrayList<>(List.of(mapper.readValue(
                    new File(ACCIDENTS_FILE), Accident[].class)));
        } catch (IOException e) {
            accidents = new ArrayList<>();
            System.out.println("Помилка завантаження даних аварій: " + e.getMessage());
        }
    }

    // Метод для групування майстрів по бригадам і збереження в JSON
    public void generateBrigadesFile() {
        Map<Integer, List<Master>> brigadesMap = masters.stream()
                .collect(Collectors.groupingBy(Master::getBrigadeNumber));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File(BRIGADES_FILE), brigadesMap);
            System.out.println("Файл бригад успішно створено.");
        } catch (IOException e) {
            System.out.println("Помилка запису файлу бригад: " + e.getMessage());
        }
    }

    // Метод для отримання кількості майстрів
    public int getMasterCount() {
        return masters.size();
    }

    // Метод для отримання кількості аварій у певному діапазоні дат
    public int getAccidentsCountInRange(LocalDate startDate, LocalDate endDate) {
        return (int) accidents.stream()
                .filter(accident -> !accident.getDateAccident().isBefore(startDate) &&
                        !accident.getDateAccident().isAfter(endDate))
                .count();
    }

    // Метод для отримання середньої вартості усунення аварії
    public double getAverageRepairCost() {
        return accidents.stream()
                .mapToDouble(Accident::getDecisionCost)
                .average()
                .orElse(0.0);
    }

    // Метод для отримання унікальних типів аварій
    public List<String> getAccidentTypes() {
        return accidents.stream()
                .map(Accident::getTypeAccident)
                .distinct()
                .collect(Collectors.toList());
    }

    // Метод для отримання списку майстрів
    public List<Master> getMasters() {
        return masters;
    }

    public Master getMasterByName(String fullName) {
        return masters.stream()
                .filter(master -> master.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }

    // Метод для додавання нового клієнта
    public void addClient(Client client) {
        clients.add(client);
        saveClients();
    }


    // Метод для додавання аварії для клієнта
    public boolean addAccidentForClient(String clientName, String type, LocalDate date, double cost) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getFullName().equalsIgnoreCase(clientName))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            String accidentId = UUID.randomUUID().toString();
            Accident newAccident = new Accident(type, date, cost, accidentId, client.getAddress(), "New");
            accidents.add(newAccident);
            client.addAccidentId(accidentId);
            saveAccidents();
            saveClients();
            return true;
        } else {
            System.out.println("Клієнта з іменем " + clientName + " не знайдено.");
            return false;
        }
    }

    // Збереження клієнтів у файл
    private void saveClients() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(CLIENTS_FILE), clients);
        } catch (IOException e) {
            System.out.println("Помилка запису клієнтів: " + e.getMessage());
        }
    }

    // Збереження аварій у файл
    private void saveAccidents() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // Реєстрація модуля для підтримки LocalDate

        try {
            mapper.writeValue(new File(ACCIDENTS_FILE), accidents); // Запис списку аварій у файл
        } catch (IOException e) {
            System.out.println("Помилка запису аварій: " + e.getMessage());
        }
    }
}
