package Task2_StockTradingPlatform;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {

    String symbol;
    String companyName;
    double price;

    Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    void displayStock() {
        System.out.printf(
            "%-10s %-25s Rs. %.2f%n",
            symbol, companyName, price
        );
    }
}

class Trader {

    double cash;
    Map<String, Integer> portfolio = new HashMap<>();

    Trader(double cash) {
        this.cash = cash;
    }

    void buyStock(Stock stock, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        double cost = stock.price * quantity;

        if (cost > cash) {
            System.out.println("Insufficient balance!");
            return;
        }

        cash -= cost;

        portfolio.put(
            stock.symbol,
            portfolio.getOrDefault(stock.symbol, 0) + quantity
        );

        System.out.println("Stock purchased successfully!");
    }

    void sellStock(Stock stock, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        int owned = portfolio.getOrDefault(stock.symbol, 0);

        if (quantity > owned) {
            System.out.println("You do not own enough shares.");
            return;
        }

        cash += stock.price * quantity;

        int remaining = owned - quantity;

        if (remaining == 0) {
            portfolio.remove(stock.symbol);
        } else {
            portfolio.put(stock.symbol, remaining);
        }

        System.out.println("Stock sold successfully!");
    }

    void displayPortfolio(Map<String, Stock> market) {

        double stockValue = 0;

        System.out.println("\n===== YOUR PORTFOLIO =====");

        if (portfolio.isEmpty()) {
            System.out.println("You do not own any stocks.");
        }

        for (String symbol : portfolio.keySet()) {

            int quantity = portfolio.get(symbol);
            Stock stock = market.get(symbol);

            double value = quantity * stock.price;
            stockValue += value;

            System.out.printf(
                "%s | Shares: %d | Value: Rs. %.2f%n",
                symbol, quantity, value
            );
        }

        double totalValue = cash + stockValue;

        System.out.printf("Available Cash: Rs. %.2f%n", cash);
        System.out.printf("Stock Value: Rs. %.2f%n", stockValue);
        System.out.printf("Total Portfolio Value: Rs. %.2f%n", totalValue);
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Stock> market = new HashMap<>();

        market.put("TCS", new Stock("TCS", "TCS Limited", 3500));
        market.put("INFY", new Stock("INFY", "Infosys", 1800));
        market.put(
            "RELIANCE",
            new Stock("RELIANCE", "Reliance Industries", 2500)
        );

        Trader trader = new Trader(100000);

        int choice = 0;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n===== MARKET DATA =====");

                    for (Stock stock : market.values()) {
                        stock.displayStock();
                    }
                    break;

                case 2:
                case 3:

                    System.out.print("Enter stock symbol: ");
                    String symbol = sc.next().toUpperCase();

                    Stock stock = market.get(symbol);

                    if (stock == null) {
                        System.out.println("Stock not found!");
                        break;
                    }

                    System.out.print("Enter quantity: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Enter a valid quantity.");
                        sc.nextLine();
                        break;
                    }

                    int quantity = sc.nextInt();

                    if (choice == 2) {
                        trader.buyStock(stock, quantity);
                    } else {
                        trader.sellStock(stock, quantity);
                    }
                    break;

                case 4:
                    trader.displayPortfolio(market);
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using the platform!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}