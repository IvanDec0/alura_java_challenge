package exchange_api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ExchangeApi {

    private static final String API = "141d301582e18bc5cda73768";

    public JsonObject sendRequestStandard() throws IOException, InterruptedException {
        String currencyCode = getValidCurrencyCode("Enter the currency code: ");
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API, currencyCode);
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return convertToJson(response);
    }

    public JsonObject sendRequestPairConversion() throws IOException, InterruptedException {
        String baseCurrency = getValidCurrencyCode("Enter the base currency code: ");
        String targetCurrency = getValidCurrencyCode("Enter the target currency code: ");
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", API, baseCurrency, targetCurrency);
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return convertToJson(response);
    }

    public JsonObject sendRequestPairConversionWithAmount() throws IOException, InterruptedException {
        String baseCurrency = getValidCurrencyCode("Enter the base currency code: ");
        String targetCurrency = getValidCurrencyCode("Enter the target currency code: ");
        Float amount = getValidAmount();
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%f", API, baseCurrency, targetCurrency, amount);
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return convertToJson(response);
    }

    public String getValidCurrencyCode(String message) {
        Scanner scanner = new Scanner(System.in);
        String currencyCode;
        while (true) {
            System.out.print(message);
            currencyCode = scanner.nextLine().toUpperCase();
            try {
                SupportedCurrencies.valueOf(currencyCode);
                return currencyCode; // Return the valid currency code
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid currency code. Please try again.");
            }
        }
    }

    public Float getValidAmount() {
        Scanner scanner = new Scanner(System.in);
        float amount;
        while (true) {
            System.out.print("Enter the amount: ");
            try {
                amount = Float.parseFloat(scanner.nextLine());
                return amount; // Return the valid currency code
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }

    public JsonObject convertToJson(HttpResponse<String> response) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        jsonObject.remove("documentation");
        jsonObject.remove("terms_of_use");
        return jsonObject;
    }
}
