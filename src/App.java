import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Считываем покупателей (одна строка, записи через ';')
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

        // 3) Считываем покупки построчно до END
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.equals("END")) break;
            if (line.isEmpty()) continue;

            // формат: Имя - Продукт
            String[] parts = line.split("-", 2);
            if (parts.length < 2) continue;
            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person person = people.get(personName);
            Product product = products.get(productName);
            if (person == null || product == null) continue; // игнорируем неизвестные имена

            boolean bought = person.buyProduct(product);
            if (bought) {
                System.out.println(person.getName() + " купил " + product.getName());
            } else {
                System.out.println(person.getName() + " не может позволить себе " + product.getName());
            }
        }

        // 4) Печатаем итог по каждому покупателю
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
            // ожидается формат: Имя = число
            String[] kv = entry.split("=", 2);
            if (kv.length < 2) continue;
            String name = kv[0].trim();
            String moneyStr = kv[1].replaceAll(";", "").trim();
            if (moneyStr.isEmpty()) continue;
            double money;
            try {
                money = Double.parseDouble(moneyStr);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Деньги не могут быть отрицательными");
            }
            // Создаём покупателя (конструктор проверит имя и деньги)
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
            // формат: Название = цена
            String[] kv = entry.split("=", 2);
            if (kv.length < 2) continue;
            String name = kv[0].trim();
            String costStr = kv[1].replaceAll(";", "").trim();
            if (costStr.isEmpty()) continue;
            double cost;
            try {
                cost = Double.parseDouble(costStr);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
            }
            Product prod = new Product(name, cost);
            products.put(prod.getName(), prod);
        }
    }
}