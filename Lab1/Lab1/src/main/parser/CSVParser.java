package main.parser;

import main.Charging;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVParser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Stream<String> readFile(final String path) throws IOException {
        return Files.lines(Paths.get(path));
    }

    public List<Charging.ChargeItem> collectChargeItems(final List<String> content, final String phoneNumber) {
        return content.stream()
                .filter(s -> s.contains(phoneNumber))
                .map(s -> s.split(","))
                .map(el -> {
                    final Charging.ChargeItem chargeItem = new Charging.ChargeItem();

                    chargeItem.setTimestamp(LocalDateTime.parse(el[0], formatter));
                    chargeItem.setFrom(el[1]);
                    chargeItem.setTo(el[2]);
                    chargeItem.setCallDuration(Float.parseFloat(el[3]));
                    chargeItem.setSmsNumber(Integer.parseInt(el[4]));

                    return chargeItem;
                }).collect(Collectors.toList());
    }

}
