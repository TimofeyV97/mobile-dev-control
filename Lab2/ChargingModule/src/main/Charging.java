package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Charging {

	public static final int FACTOR = 1024;

	/** charging factor (rub/Mb) */
	private static final double CHARGE_FACTOR = 0.5;

	public double countTrafficPrice(final List<ChargeItem> chargeItems, final String ipAddress) {
		double total = 0;

		for (final ChargeItem chargeItem : chargeItems) {
			total += chargeItem.getInByte();
		}

		return total  / FACTOR / FACTOR * CHARGE_FACTOR;
	}

	public static class ChargeItem {

		private LocalDate dateFirstSeen;

		private LocalTime timeFirstSeen;

		private String event;

		private String xEvent;

		private String proto;

		private String srcIpAddr;

		private String dstIpAddr;

		private String xSrcIpAddr;

		private String xDstIpAddr;

		private double inByte;

		private double outByte;

		public LocalDate getDateFirstSeen() {
			return dateFirstSeen;
		}

		public void setDateFirstSeen(final LocalDate dateFirstSeen) {
			this.dateFirstSeen = dateFirstSeen;
		}

		public LocalTime getTimeFirstSeen() {
			return timeFirstSeen;
		}

		public void setTimeFirstSeen(final LocalTime timeFirstSeen) {
			this.timeFirstSeen = timeFirstSeen;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(final String event) {
			this.event = event;
		}

		public String getXEvent() {
			return xEvent;
		}

		public void setXEvent(final String xEvent) {
			this.xEvent = xEvent;
		}

		public String getProto() {
			return proto;
		}

		public void setProto(final String proto) {
			this.proto = proto;
		}

		public String getSrcIpAddr() {
			return srcIpAddr;
		}

		public void setSrcIpAddr(final String srcIpAddr) {
			this.srcIpAddr = srcIpAddr;
		}

		public String getDstIpAddr() {
			return dstIpAddr;
		}

		public void setDstIpAddr(final String dstIpAddr) {
			this.dstIpAddr = dstIpAddr;
		}

		public String getXSrcIpAddr() {
			return xSrcIpAddr;
		}

		public void setXSrcIpAddr(final String xSrcIpAddr) {
			this.xSrcIpAddr = xSrcIpAddr;
		}

		public String getXDstIpAddr() {
			return xDstIpAddr;
		}

		public void setXDstIpAddr(final String xDstIpAddr) {
			this.xDstIpAddr = xDstIpAddr;
		}

		public double getInByte() {
			return inByte;
		}

		public void setInByte(final double inByte) {
			this.inByte = inByte;
		}

		public double getOutByte() {
			return outByte;
		}

		public void setOutByte(final double outByte) {
			this.outByte = outByte;
		}

	}

	public enum Unit {

		K,

		M,

		G

	}

}
