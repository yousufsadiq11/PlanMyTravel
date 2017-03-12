package com.uncc.planmytravel.servlet;

import java.sql.Date;

public class flight_details {
public int flight_id;
	public int getFlight_id() {
	return flight_id;
}
public void setFlight_id(int flight_id) {
	this.flight_id = flight_id;
}
	public String flight_number;
	public String flight_name;
	public String source;
	public String destination;
	public String arrival;
	public String departure;
	public String date_of_arrival;
	public String date_of_departure;
	public int economy_seat_count;
	public int business_seat_count;
	public int booking_id;
	
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public String getFlight_name() {
		return flight_name;
	}
	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
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
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDate_of_arrival() {
		return date_of_arrival;
	}
	public void setDate_of_arrival(String date_of_arrival) {
		this.date_of_arrival = date_of_arrival;
	}
	public String getDate_of_departure() {
		return date_of_departure;
	}
	public void setDate_of_departure(String date_of_departure) {
		this.date_of_departure = date_of_departure;
	}
	public int getEconomy_seat_count() {
		return economy_seat_count;
	}
	public void setEconomy_seat_count(int economy_seat_count) {
		this.economy_seat_count = economy_seat_count;
	}
	public int getBusiness_seat_count() {
		return business_seat_count;
	}
	public void setBusiness_seat_count(int business_seat_count) {
		this.business_seat_count = business_seat_count;
	}
	public int getbooking_id() {
		return booking_id;
	}
	public void setbooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	
}
