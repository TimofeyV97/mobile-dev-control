package main.parser;

import main.Charging;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public Stream<String> readFile(final String path) throws IOException {
        return Files.lines(Paths.get(path));
    }

    public List<Charging.ChargeItem> collectChargeItems(final List<String> content, final String ipAddress) {
        return content.stream()
                .filter(s -> s.contains(ipAddress))
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(this::removeEmptyStrings)
                .map(list -> list.toArray(new String[0]))
                .map(s -> {
                    final Charging.ChargeItem chargeItem = new Charging.ChargeItem();

                    chargeItem.setDateFirstSeen(LocalDate.parse(s[0], dateFormatter));
                    chargeItem.setTimeFirstSeen(LocalTime.parse(s[1], timeFormatter));
                    chargeItem.setEvent(s[2]);
                    chargeItem.setXEvent(s[3]);
                    chargeItem.setProto(s[4]);
                    chargeItem.setSrcIpAddr(s[5]);
                    chargeItem.setDstIpAddr(s[7]);
                    chargeItem.setXSrcIpAddr(s[8]);
                    chargeItem.setXDstIpAddr(s[10]);

                    final double inBytes = Double.parseDouble(s[11]);

                    if (Character.isLetter(s[12].charAt(0))) {
                        chargeItem.setInByte(calculateBytes(inBytes, s[12]));
                        chargeItem.setOutByte(Long.parseLong(s[13]));
                    } else {
                        chargeItem.setInByte(inBytes);
                        chargeItem.setOutByte(Long.parseLong(s[12]));
                    }

                    return chargeItem;
                })
                .collect(Collectors.toList());
    }

    private List<String> removeEmptyStrings(final List<String> content) {
        return content.stream()
                .filter(s -> !"".equals(s))
                .collect(Collectors.toList());
    }

    private double calculateBytes(final double bytes, final String unit) {
        final int factor = Charging.FACTOR;

        switch (Charging.Unit.valueOf(unit)) {
            case K:
                return bytes * factor;

            case M:
                return bytes * factor * factor;

            case G:
                return bytes * factor * factor * factor;

            default:
                throw new RuntimeException("Unknown unit: " + unit);
        }
    }

}
