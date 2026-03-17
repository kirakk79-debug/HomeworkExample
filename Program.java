import java.time.LocalDate;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        FinancialManager manager = new FinancialManager();
        manager.load();

        Scanner input = new Scanner(System.in,"CP866");

        while (true) {
            System.out.println();
            System.out.println("\n---- Личный финансовый менеджер ----");
            System.out.println("1. Показать баланс.");
            System.out.println("2. Показать историю операций.");
            System.out.println("3. Добавить операцию.");
            System.out.println("4. Поиск по сумме.");
            System.out.println("5. Поиск по дате (ГГГГ-ММ-ДД).");
            System.out.println("0. Выход.");
            System.out.println();
            System.out.print("Введите № операции > ");

            String choice = input.nextLine();

            if(choice.equals("1")) {
                System.out.println();
                manager.currentBalance();

            } else if (choice.equals("2")) {
                System.out.println();
                manager.showHistory();

            } else if (choice.equals("3")) {
                addTransaction(manager, input);
                manager.save();

            } else if (choice.equals("4")) {
                System.out.print("Введите сумму > ");
                double amount = input.nextDouble();
                input.nextLine();
                manager.searchByAmount(amount);

            } else if (choice.equals("5")) {
                System.out.print("Введите дату (гггг-мм-дд) > ");
                String dateStr = input.nextLine(); 
                    try {
                        LocalDate date = LocalDate.parse(dateStr);
                        manager.searchByDate(date);
                    } catch (Exception ex) {
                        System.out.println("Неверный формат даты.");
                    }
                
            } else if(choice.equals("0")) {
                System.out.println();
                System.out.print("Goodbye!");
                break;
            }        
        }
    }
        public static void addTransaction(FinancialManager manager, Scanner input) {
        System.out.print("Дата (гггг-мм-дд) > ");
                String dateStr = input.nextLine();
                LocalDate date = LocalDate.parse(dateStr);

                System.out.print("Сумма > ");
                double amount = input.nextDouble();
                input.nextLine();

                System.out.print("Категория > " );
                String category = input.nextLine();

                System.out.print("Пополнение  / Списание  > ");
                String type = input.nextLine();
                manager.addTransaction(date, amount, category, type);
                System.out.println("Операция добавлена!");
        }
    }
