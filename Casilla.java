package parteLogica;

public class Casilla<T> {
	//atributos
	private T ficha = null;
	private boolean ocupado = false;
	//constructor
	public Casilla(T ficha){
		this.ficha = ficha;
	}
	//metodos
	public void ocupada() {
		this.ocupado = true;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	public boolean isOcupado() {
		return ocupado;
	}
}