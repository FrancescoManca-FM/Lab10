package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	//CODA AD EVENTI
	private PriorityQueue<Event> queue = new PriorityQueue<Event>();
	//PARAMETRI DI SIMULAZIONE
	private int nTavoli10 = 2;
	private int nTavoli8 = 4;
	private int nTavoli6 = 4;
	private int nTavoli4 = 5;
	
	private Duration time_in;
	
	private final LocalTime orarioApertura = LocalTime.of(7, 00);
	private final int maxEventi = 2000;
	
	//MODELLO DEL MONDO
	private int disponibili10;
	private int disponibili8;
	private int disponibili6;
	private int disponibili4;
	
	//VALORI DA CALCOLARE
	private int clienti;
	private int clientiSoddisfatti;
	private int clientiInsoddisfatti;

	
	//METODI PER RESTITUIRE I VALORI DA CALCOLARE
	
	public int getClienti() {
		return clienti;
	}
	public int getClientiSoddisfatti() {
		return clientiSoddisfatti;
	}
	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}
	
	//SIMULAZIONE VERA E PROPRIA
	
	public  double generateRandomDouble(double min, double max) {
		  return ThreadLocalRandom.current().nextDouble(min, max);
		}
	
	public int generateRandomInt(int min, int max) {
		  return ThreadLocalRandom.current().nextInt(min, max);
		}
	
	public void run() {
		
		//SETTO VARIABILI MODELLO DEL MONDO
		this.disponibili10 = this.nTavoli10;
		this.disponibili8 = this.nTavoli8;
		this.disponibili6 = this.nTavoli6;
		this.disponibili4 = this.nTavoli4;
		
		this.clienti = 0;
		this.clientiInsoddisfatti = 0;
		this.clientiSoddisfatti = 0;
		
		//RIEMPIO CODA
		int i = 0;
		this.queue.clear();
		LocalTime arrivoCliente = this.orarioApertura;
		while(i<this.maxEventi) {
			time_in = Duration.of(this.generateRandomInt(1, 10), ChronoUnit.MINUTES);
			int numPersoneTavolo = this.generateRandomInt(1, 10);
			double intolleranza = this.generateRandomDouble(0.00, 0.90);
			Event e = new Event(arrivoCliente, EventType.ARRIVO_GRUPPO_CLIENTI, numPersoneTavolo, intolleranza);
			this.queue.add(e);
			arrivoCliente = arrivoCliente.plus(time_in);
			i++;			
		}
		
		while(!this.queue.isEmpty()) {
			this.processEvent(this.queue.poll());
		}
		
	}
	private void processEvent(Event e) {
		System.out.println(e);
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			clienti += e.getNumPersone();

			if(e.getNumPersone()<=4 && e.getNumPersone()>=2 && disponibili4>0) {
				disponibili4--;
				clientiSoddisfatti += e.getNumPersone();
				Duration durata = Duration.of(this.generateRandomInt(60, 120), ChronoUnit.MINUTES);
				Event nuovo = new Event(e.getTime().plus(durata), EventType.TAVOLO_LIBERATO4, e.getNumPersone(), e.getTolleranza());
				this.queue.add(nuovo);
				
			}else if(e.getNumPersone()<=6 && e.getNumPersone()>=3 && disponibili6>0){
				disponibili6--;
				clientiSoddisfatti += e.getNumPersone();
				Duration durata = Duration.of(this.generateRandomInt(60, 120), ChronoUnit.MINUTES);
				Event nuovo = new Event(e.getTime().plus(durata), EventType.TAVOLO_LIBERATO6, e.getNumPersone(), e.getTolleranza());
				this.queue.add(nuovo);

				
			}else if(e.getNumPersone()>=4 && e.getNumPersone()<=8 && disponibili8>0) {
				disponibili8--;
				clientiSoddisfatti += e.getNumPersone();
				Duration durata = Duration.of(this.generateRandomInt(60, 120), ChronoUnit.MINUTES);
				Event nuovo = new Event(e.getTime().plus(durata), EventType.TAVOLO_LIBERATO8, e.getNumPersone(), e.getTolleranza());
				this.queue.add(nuovo);

				
			}else if(e.getNumPersone()>=5 && e.getNumPersone()<=10 && disponibili10>0) {
				disponibili10--;
				clientiSoddisfatti += e.getNumPersone();
				Duration durata = Duration.of(this.generateRandomInt(60, 120), ChronoUnit.MINUTES);
				Event nuovo = new Event(e.getTime().plus(durata), EventType.TAVOLO_LIBERATO10, e.getNumPersone(), e.getTolleranza());
				this.queue.add(nuovo);

				
			}else {
				if(e.getTolleranza()<0.45) {
					this.clientiInsoddisfatti += e.getNumPersone();
				}else {
					this.clientiSoddisfatti += e.getNumPersone();
				}
			}
			
			break;
			
			
		case TAVOLO_LIBERATO4:			
			disponibili4++;
			break;
		
		case TAVOLO_LIBERATO6:
			disponibili6++;
			break;
		
		case TAVOLO_LIBERATO8:
			disponibili8++;
			break;
		
		case TAVOLO_LIBERATO10:
			disponibili10++;
			break;
		
		}
		
	}
	
}
