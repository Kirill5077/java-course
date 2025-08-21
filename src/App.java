import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Считываем покупателей
        String personsLine = sc.nextLine();
        Map<String, Person> people = new LinkedHashMap<>();
        try {
            parsePersons(personsLine, people);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        // 2) Считываем продукты
        String productsLine = sc.nextLine();
        Map<String, Product> products = new HashMap<>();
        try {
            parseProducts(productsLine, products);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        // 3) Считываем покупки
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.equals("END")) break;
            if (line.isEmpty()) continue;

            String[] parts = line.split("-", 2);
            if (parts.length < 2) continue;
            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person person = people.get(personName);
            Product product = products.get(productName);
            if (person == null || product == null) continue;

            boolean bought = person.buyProduct(product);
            if (bought) {
                System.out.println(person.getName() + " купил " + product.getName());
            } else {
                System.out.println(person.getName() + " не может позволить себе " + product.getName());
            }
        }

        // 4) Итог
        for (Person p : people.values()) {
            System.out.println(p.toString());
        }
    }

    private static void parsePersons(String line, Map<String, Person> people) {
        if (line == null || line.trim().isEmpty()) return;
        String[] entries = line.split(";");
        for (String entry : entries) {
            if (entry == null) continue;
            entry = entry.trim();
            if (entry.isEmpty()) continue;

            String[] kv = entry.split("=", 2);
            if (kv.length < 2) continue;
            String name = kv[0].trim();
            double money = Double.parseDouble(kv[1].trim());
            Person p = new Person(name, money);
            people.put(p.getName(), p);
        }
    }

    private static void parseProducts(String line, Map<String, Product> products) {
        if (line == null || line.trim().isEmpty()) return;
        String[] entries = line.split(";");
        for (String entry : entries) {
            if (entry == null) continue;
            entry = entry.trim();
            if (entry.isEmpty()) continue;

            // формат: Название = цена (обычный продукт)
            // или Название = цена:скидка:год-месяц-день (скидочный)
            String[] kv = entry.split("=", 2);
            if (kv.length < 2) continue;
            String name = kv[0].trim();
            String[] values = kv[1].trim().split(":");

            if (values.length == 1) {
                double cost = Double.parseDouble(values[0].trim());
                Product prod = new Product(name, cost);
                products.put(prod.getName(), prod);
            } else if (values.length == 3) {
                double cost = Double.parseDouble(values[0].trim());
                double discount = Double.parseDouble(values[1].trim());
                LocalDate expiry = LocalDate.parse(values[2].trim());
                Product prod = new DiscountProduct(name, cost, discount, expiry);
                products.put(prod.getName(), prod);
            }
        }
    }
}
