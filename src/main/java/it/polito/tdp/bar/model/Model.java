package it.polito.tdp.bar.model;

public class Model {

	private Simulator sim;
	
	public Model() {
		sim = new Simulator();
	}
	
	
	public void run() {
		this.sim.run();
	}
	
	
	public int getClientiTotali() {
		return this.sim.getClienti();
	}
	
	public int getClientiSoddisfatti() {
		return this.sim.getClientiSoddisfatti();
	}
	
	public int getClientiInsoddisfatti() {
		return this.sim.getClientiInsoddisfatti();
	}
}
