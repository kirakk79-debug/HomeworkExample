import java.time.LocalDate;

public class Transaction {
    public LocalDate date;
    public double amount;
    public String category;
    public String type;

    public Transaction(LocalDate date, double amount, String category, String type) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }


    public LocalDate getDate() {
        return date;
    }
    public double getAmount() {
        return amount;
    }
    public String getCategory () {
        return category;
    }
    public String getType () {
        return type;
    }

    public String toString() {
        return date + " | " + type + " | " + amount + " руб. | " + category;
    }
}


