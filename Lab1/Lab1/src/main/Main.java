package main;

import main.parser.CSVParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {
        final String filePath = "./resources/data.csv";
        final String phoneNumber = "915783624";
        final CSVParser parser = new CSVParser();
        final Charging charging = new Charging();
        final List<Charging.ChargeItem> chargeItems;
        final List<String> content;

        try {
            content = parser.readFile(filePath).collect(Collectors.toList());
        } catch (final IOException e) {
            System.out.println("Error reading file:");
            e.printStackTrace();
            return;
        }

        try {
             chargeItems = parser.collectChargeItems(
                    content,
                    phoneNumber
            );
        } catch (final Exception e) {
            System.out.println("Error parsing file:");
            e.printStackTrace();
            return;
        }

        final float callsPrice = charging.countCallsPrice(chargeItems, phoneNumber);
        final float smsPrice = charging.countSmsPrice(chargeItems, phoneNumber);

        System.out.println(String.join("\n", content));
        System.out.println("\nPhone number: " + phoneNumber);
        System.out.println("Calls price: " + callsPrice);
        System.out.println("Sms price: " + smsPrice);
        System.out.println("Total price: " + (callsPrice + smsPrice));
    }

}
