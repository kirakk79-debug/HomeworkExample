import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FinancialManager {
    List<Transaction>transactions = new ArrayList<>();
    
public static String filename = "Transactions.txt";


public void currentBalance () { // Метод: Показать текущий баланс
    double balance = 0;
        for(Transaction t : transactions) {
            if(t.getType().equals("Пополнение")) {
                balance = balance + t.getAmount();
            } else {
                balance = balance - t.getAmount();
            }
        }
        System.out.print("Текущий баланс: " + balance + " руб.");
}

public void showHistory() { // Метод: показать историю оппераций
    if(transactions.isEmpty()) {
        System.out.println("История операций пуста!");
    return;
    }
    System.out.println("История операций: ");
    for(int i = 0; i < transactions.size(); i++) {
        System.out.println((i + 1) + "." + transactions.get(i));
    }

}
    
public void searchByAmount(double amount) { // Метод: поиск по сумме
    System.out.println("Результат поиска по сумме: " + amount + " руб.");
    boolean found = false;
    for(Transaction t : transactions) {
        if(t.getAmount() == amount) {
        System.out.println(t);
        found = true;
        }
    }

    if(!found) {
        System.out.println("НЕ НАЙДЕНЫ.");
    }
}

public void searchByDate(LocalDate date) { // Метод: поиск по дате
    System.out.println("Результаты поиска по дате: " + date + " ");
    boolean found = false;
    for(Transaction t : transactions) {
        if(t.getDate().equals(date)) {
            System.out.println(t);
            found = true;
        }
    }
    if(!found) {
        System.out.print("НЕ НАЙДЕНЫ.");
    }
}

public void load() { // Метод: загрузка
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if(parts.length == 4) {
                LocalDate date = LocalDate.parse(parts[0]);
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                String type = parts[3];
                transactions.add(new Transaction(date, amount, category, type));
            }
        }
    } catch (Exception ex) {
        System.out.println("Ошибка: " + ex.getMessage());
    }
    
}

public void save() { // Метод: сохранение
    try (FileWriter wr = new FileWriter(filename)) {
        for (Transaction t : transactions) {
            wr.write(t.getDate() + ";" + t.getAmount() + ";" + t.getCategory() + ";"  + t.getType() + "\n");
        }
        wr.close();
    } catch (Exception ex) {
        System.out.println("Ошибка: " + ex.getMessage());
    }

}

public void addTransaction(LocalDate date, double amount, String category, String type) { // Метод: добавления новой операции
    transactions.add(new Transaction(date, amount, category, type));
}
}

