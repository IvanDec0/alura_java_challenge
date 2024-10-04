package exchange_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeApi exchangeApi = new ExchangeApi();
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        while (true) {
            System.out.println("Choose a request type:");
            System.out.println("1. Standard request");
            System.out.println("2. Pair conversion request");
            System.out.println("3. Pair conversion with amount request");
            System.out.println("Enter 'q' to quit.");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String jsonResponse = "";
                switch (choice) {
                    case "1":
                        jsonResponse = gson.toJson(exchangeApi.sendRequestStandard());
                        break;
                    case "2":
                        jsonResponse = gson.toJson(exchangeApi.sendRequestPairConversion());
                        break;
                    case "3":
                        jsonResponse = gson.toJson(exchangeApi.sendRequestPairConversionWithAmount());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
                System.out.println(jsonResponse);
                System.out.println("--------------------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}