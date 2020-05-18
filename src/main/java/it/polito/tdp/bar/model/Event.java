package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event>{

	public enum EventType{
		
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO4, TAVOLO_LIBERATO6, TAVOLO_LIBERATO8, TAVOLO_LIBERATO10
	}
	
	private LocalTime time;
	private EventType type;
	private int numPersone;
	private double tolleranza;
	
	public Event(LocalTime time, EventType type, int numPersone, double tolleranza) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.tolleranza = tolleranza;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", numPersone=" + numPersone + ", tolleranza=" + tolleranza
				+ "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}
	
	
}
