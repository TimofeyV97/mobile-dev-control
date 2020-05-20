package main;

import main.graphics.Plot;
import main.parser.Parser;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class Main {

    public static void main(final String[] args) {
        final String ipAddress = "217.15.20.194";
        final String filePath = "./resources/data.txt";
        final Parser parser = new Parser();
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
                    ipAddress
            );
        } catch (final Exception e) {
            System.out.println("Error parsing file:");
            e.printStackTrace();
            return;
        }

        final double trafficPrice = charging.countTrafficPrice(chargeItems, ipAddress);

        System.out.println("IP address: " + ipAddress);
        System.out.printf("Traffic price: %.2f", trafficPrice);

        final Plot example = new Plot("Traffic/Time", chargeItems);

        example.setSize(900, 400);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }

}
