package model;

import java.util.List;

public class Flight
{
	private int flightId;
	private String flightName;
	private String source;
	private String destination;
	private int seats;
	private int ticketFare;
	private String date;
	private List<String> routes;
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getTicketFare() {
		return ticketFare;
	}
	public void setTicketFare(int ticketFare) {
		this.ticketFare = ticketFare;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getRoutes() {
		return routes;
	}
	public void setRoutes(List<String> routes) {
		this.routes=routes;
	}
	
	
}
