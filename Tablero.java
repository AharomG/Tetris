package parteLogica;

import java.util.LinkedList;

public class Tablero<T> {
	//atributos
	private LinkedList<T> casillas = null; 
	//constructor
	public Tablero(T figura) {
		this.casillas = new LinkedList<T>();
	}
	//metodos
	public LinkedList<T> getCasillas() {
		return casillas;
	}
	
}