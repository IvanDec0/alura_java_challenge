# Exchange Rate API Client

This project is a Java-based client for interacting with the Exchange Rate API. It allows users to make various types of requests to retrieve exchange rate information.

## Features

- **Standard Request**: Get the latest exchange rates for a specified currency.
- **Pair Conversion Request**: Get the exchange rate between two specified currencies.
- **Pair Conversion with Amount Request**: Get the converted amount between two specified currencies.

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/IvanDec0/alura_java_challenge.git
   ```
2. Open the project in IntelliJ IDEA.

## Usage

1. Run the `Main` class located in the `src/alura_java_challenge` package.

2. Follow the on-screen prompts to choose a request type and enter the required information.

## Code Overview

### Main Class

The `Main` class contains the main loop that prompts the user for input and makes the appropriate API requests.

### ExchangeApi Class

The `ExchangeApi` class contains methods for making different types of requests to the Exchange Rate API.

### SupportedCurrencies Enum

The `SupportedCurrencies` enum lists all the supported currency codes.

## Example

```plaintext
Choose a request type:
1. Standard request
2. Pair conversion request
3. Pair conversion with amount request
Enter 'q' to quit.
Your choice: 1
Enter the currency code: USD
{
  "result": "success",
  "time_last_update_unix": 1726963202,
  "time_last_update_utc": "Sun, 22 Sep 2024 00:00:02 +0000",
  "base_code": "USD",
  "conversion_rates": {
    "AED": 3.6725,
    "AFN": 69.4544,
    ...
  }
}
--------------------------------------------------
```
