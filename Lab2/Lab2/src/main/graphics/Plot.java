package main.graphics;

import main.Charging;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Plot extends JFrame {

    public Plot(final String title, final List<Charging.ChargeItem> chargeItems) {
        super(title);

        final XYDataset dataset = createDataset(chargeItems);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Traffic/Time",
                "",
                "Traffic (Mb)",
                dataset,
                true,
                true,
                false
        );

        setContentPane(new ChartPanel(chart));
    }

    private XYDataset createDataset(final List<Charging.ChargeItem> chargeItems)
    {
        final TimeSeries s1 = new TimeSeries("Traffic/Time");
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        final int factor = Charging.FACTOR;

        for (final Charging.ChargeItem chargeItem : chargeItems) {
            final LocalDateTime dateTime = chargeItem.getDateFirstSeen().atTime(chargeItem.getTimeFirstSeen());

            s1.addOrUpdate(
                    new Millisecond(
                            Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())
                    ),
                    chargeItem.getInByte() / factor / factor
            );
        }

        dataset.addSeries(s1);

        return dataset;
    }


}
