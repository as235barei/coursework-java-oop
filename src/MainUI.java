import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class MainUI extends JFrame {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private WaterSupplySystem waterSupplySystem; // Екземпляр класу бізнес-логіки

    public MainUI(WaterSupplySystem waterSupplySystem) {
        this.waterSupplySystem = waterSupplySystem;

        setTitle("Міський водопровід - Управління");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 5, 5));  // Змінено кількість рядків на 8 для додаткових кнопок

        // Кнопки для запитів
        JButton btnCountMasters = new JButton("Кількість майстрів");
        JButton btnAccidentsInRange = new JButton("Кількість аварій з .. по ..");
        JButton btnAverageCost = new JButton("Середня вартість усунення аварії");
        JButton btnAccidentTypes = new JButton("Типи аварій");
        JButton btnMasterEarnings = new JButton("Заробіток майстра з .. по ..");
        JButton btnCountBrigades = new JButton("Кількість бригад");

        // Нові кнопки для додавання клієнта і аварії
        JButton btnAddClient = new JButton("Додати Клієнта");
        JButton btnAddAccident = new JButton("Записати нову Ситуацію");

        // Додаємо кнопки на панель
        panel.add(btnCountMasters);
        panel.add(btnAccidentsInRange);
        panel.add(btnAverageCost);
        panel.add(btnAccidentTypes);
        panel.add(btnMasterEarnings);
        panel.add(btnCountBrigades);
        panel.add(btnAddClient);
        panel.add(btnAddAccident);

        add(panel, BorderLayout.CENTER);

        // Текстова область для виведення результатів
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        resultArea.setRows(5);

        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Обробники для нових кнопок
        btnAddClient.addActionListener(e -> addClient());
        btnAddAccident.addActionListener(e -> addAccident());

        // Існуючі обробники для кнопок
        btnCountMasters.addActionListener(e -> showCountMasters(resultArea));
        btnAccidentsInRange.addActionListener(e -> showAccidentsInRange(resultArea));
        btnAverageCost.addActionListener(e -> showAverageCost(resultArea));
        btnAccidentTypes.addActionListener(e -> showAccidentTypes(resultArea));
        btnMasterEarnings.addActionListener(e -> showMasterEarnings(resultArea));
        btnCountBrigades.addActionListener(e -> showCountBrigades(resultArea));

        setVisible(true);
    }


    // Метод для додавання нового клієнта
    private void addClient() {
        JDialog dialog = new JDialog(this, "Додати Клієнта", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Поля вводу
        JLabel nameLabel = new JLabel("Ім'я:");
        JTextField nameField = new JTextField();
        JLabel addressLabel = new JLabel("Адреса:");
        JTextField addressField = new JTextField();
        JLabel phoneLabel = new JLabel("Телефон:");
        JTextField phoneField = new JTextField();
        JLabel deptNumLabel = new JLabel("Номер відділу:");
        JTextField deptNumField = new JTextField();

        // Кнопка створення
        JButton createButton = new JButton("Створити");

        // Обробник події кнопки "Створити"
        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String deptNumStr = deptNumField.getText().trim();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || deptNumStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Усі поля мають бути заповнені!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int deptNum = Integer.parseInt(deptNumStr);

                // Створення нового клієнта
                Client newClient = new Client(name, address, phone, deptNum, new ArrayList<>());
                waterSupplySystem.addClient(newClient);

                JOptionPane.showMessageDialog(dialog, "Клієнта успішно додано!");
                dialog.dispose(); // Закриває діалогове вікно після успішного створення
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Номер відділу має бути числом.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Додавання компонентів до діалогу
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(addressLabel);
        dialog.add(addressField);
        dialog.add(phoneLabel);
        dialog.add(phoneField);
        dialog.add(deptNumLabel);
        dialog.add(deptNumField);
        dialog.add(new JLabel()); // Заповнення сітки
        dialog.add(createButton);

        dialog.setVisible(true); // Відображення діалогу
    }



    // Метод для додавання нової аварії
    private void addAccident() {
        JDialog dialog = new JDialog(this, "Додати Аварію", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Ім'я клієнта:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Тип аварії:");
        JTextField typeField = new JTextField();
        JLabel dateLabel = new JLabel("Дата аварії (дд.мм.рррр):");
        JTextField dateField = new JTextField();
        JLabel costLabel = new JLabel("Вартість:");
        JTextField costField = new JTextField();

        JButton createButton = new JButton("Створити");
        createButton.addActionListener(e -> {
            String clientName = nameField.getText().trim();
            String type = typeField.getText().trim();
            String dateStr = dateField.getText().trim();
            String costStr = costField.getText().trim().replace(",", ".");
            if (clientName.isEmpty() || type.isEmpty() || dateStr.isEmpty() || costStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Усі поля мають бути заповнені!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                double cost = Double.parseDouble(costStr);

                boolean added = waterSupplySystem.addAccidentForClient(clientName, type, date, cost);
                if (added) {
                    JOptionPane.showMessageDialog(dialog, "Аварію успішно додано!");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Клієнта не знайдено.", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Невірний формат дати або вартості. Спробуйте знову.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(typeLabel);
        dialog.add(typeField);
        dialog.add(dateLabel);
        dialog.add(dateField);
        dialog.add(costLabel);
        dialog.add(costField);
        dialog.add(new JLabel());
        dialog.add(createButton);
        dialog.setVisible(true);
    }


    // Виведення кількості майстрів
    private void showCountMasters(JTextArea resultArea) {
        int count = waterSupplySystem.getMasterCount(); // Виклик методу з бізнес-логіки
        resultArea.setText("Кількість майстрів: " + count);
    }

    // Виведення кількості аварій в діапазоні дат
    private void showAccidentsInRange(JTextArea resultArea) {
        String startDateStr = JOptionPane.showInputDialog(this, "Введіть початкову дату (дд.мм.рррр):");
        String endDateStr = JOptionPane.showInputDialog(this, "Введіть кінцеву дату (дд.мм.рррр):");

        if (startDateStr != null && endDateStr != null) {
            try {
                LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
                LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

                int accidentCount = waterSupplySystem.getAccidentsCountInRange(startDate, endDate);
                resultArea.setText("Кількість аварій з " + startDate + " по " + endDate + ": " + accidentCount);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Невірний формат дати. Будь ласка, використовуйте дд.мм.рррр", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Виведення середньої вартості усунення аварії
    private void showAverageCost(JTextArea resultArea) {
        double averageCost = waterSupplySystem.getAverageRepairCost();
        resultArea.setText("Середня вартість усунення аварії: " + averageCost);
    }

    // Виведення типів аварій
    private void showAccidentTypes(JTextArea resultArea) {
        List<String> accidentTypes = waterSupplySystem.getAccidentTypes();
        resultArea.setText("Типи аварій: " + String.join(", ", accidentTypes));
    }

    // Виведення заробітку майстра за період
    private void showMasterEarnings(JTextArea resultArea) {
        String masterName = JOptionPane.showInputDialog(this, "Введіть ім'я майстра:");
        if (masterName == null || masterName.isEmpty()) {
            resultArea.setText("Ім'я майстра не було введено.");
            return;
        }

        Master master = waterSupplySystem.getMasterByName(masterName);  // Метод для пошуку майстра за ім'ям
        if (master == null) {
            resultArea.setText("Майстра з ім'ям " + masterName + " не знайдено.");
            return;
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Формат дати в JSON
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Формат для відображення

        LocalDate startDate, endDate;

        try {
            String startDateInput = JOptionPane.showInputDialog(this, "Введіть початкову дату (формат: dd.MM.yyyy):");
            startDate = LocalDate.parse(startDateInput, outputFormatter);

            String endDateInput = JOptionPane.showInputDialog(this, "Введіть кінцеву дату (формат: dd.MM.yyyy):");
            endDate = LocalDate.parse(endDateInput, outputFormatter);
        } catch (DateTimeParseException e) {
            resultArea.setText("Некоректний формат дати. Спробуйте знову.");
            return;
        }

        List<Map<String, Object>> earningsList = master.getEarnings();
        if (earningsList == null || earningsList.isEmpty()) {
            resultArea.setText("У майстра " + masterName + " немає записів про заробіток.");
            return;
        }

        double totalEarnings = 0.0;
        boolean earningsFound = false;

        for (Map<String, Object> earning : earningsList) {
            LocalDate earningDate = LocalDate.parse(earning.get("date").toString(), inputFormatter);
            if ((earningDate.isEqual(startDate) || earningDate.isAfter(startDate)) &&
                    (earningDate.isEqual(endDate) || earningDate.isBefore(endDate))) {
                totalEarnings += Double.parseDouble(earning.get("amount").toString());
                earningsFound = true;
            }
        }

        if (earningsFound) {
            resultArea.setText("Загальна сума заробітку майстра " + masterName + " з " +
                    startDate.format(outputFormatter) + " по " + endDate.format(outputFormatter) +
                    " становить: " + totalEarnings + " грн.");
        } else {
            resultArea.setText("Записів про заробітки у вказаному періоді не знайдено.");
        }
    }

    // Виведення кількості бригад
    private void showCountBrigades(JTextArea resultArea) {
        List<Master> mastersList = waterSupplySystem.getMasters();

        if (mastersList == null || mastersList.isEmpty()) {
            resultArea.setText("У системі немає жодного майстра.");
            return;
        }

        Set<Integer> brigadeNumbers = new HashSet<>();

        for (Master master : mastersList) {
            brigadeNumbers.add(master.getBrigadeNumber());  // Додаємо номер бригади кожного майстра до Set
        }

        int brigadeCount = brigadeNumbers.size();
        resultArea.setText("Кількість бригад у системі: " + brigadeCount);
    }

    public static void main(String[] args) {
        // Ініціалізація бізнес-логіки (можна передати реальні дані з файлів або бази даних)
        WaterSupplySystem waterSupplySystem = new WaterSupplySystem();
        waterSupplySystem.generateBrigadesFile(); // Виклик методу для оновлення файлу
        SwingUtilities.invokeLater(() -> new MainUI(waterSupplySystem));
    }


}
