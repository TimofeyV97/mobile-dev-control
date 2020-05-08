package main;

import java.time.LocalDateTime;
import java.util.List;

public class Charging {

    /** outbound calls price (rub/min) */
    private static final float OUTBOUND_CALLS_PRICE = 2;

    /** inbound calls price (rub/min) */
    private static final float INBOUND_CALLS_PRICE = 0;

    /** SMS price (rub) after first {@link FREE_SMS} */
    private static final float SMS_PRICE = 1;

    /** Number of free SMS */
    private static final int FREE_SMS = 10;

    public float countCallsPrice(final List<ChargeItem> chargeItems, final String phone) {
        float total = 0;

        for (final ChargeItem chargeItem : chargeItems) {
            if (chargeItem.getFrom().equals(phone)) {
                total += (chargeItem.getCallDuration() * OUTBOUND_CALLS_PRICE);
            }

            if (chargeItem.getTo().equals(phone)) {
                total += (chargeItem.getCallDuration() * INBOUND_CALLS_PRICE);
            }
        }

        return total;
    }

    public float countSmsPrice(final List<ChargeItem> chargeItems, final String phone) {
        float total = 0;

        for (final ChargeItem chargeItem : chargeItems) {
            if (chargeItem.getFrom().equals(phone)) {
                total += ((chargeItem.getSmsNumber() - FREE_SMS) * SMS_PRICE);
            }
        }

        return total;
    }

    public static class ChargeItem {

        private LocalDateTime timestamp;

        private String from;

        private String to;

        private float callDuration;

        private int smsNumber;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(final LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(final String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(final String to) {
            this.to = to;
        }

        public float getCallDuration() {
            return callDuration;
        }

        public void setCallDuration(final float callDuration) {
            this.callDuration = callDuration;
        }

        public int getSmsNumber() {
            return smsNumber;
        }

        public void setSmsNumber(final int smsNumber) {
            this.smsNumber = smsNumber;
        }

    }

}
