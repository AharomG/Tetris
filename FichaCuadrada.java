package parteLogica;

import java.util.ArrayList;

public class FichaCuadrada extends Ficha<Cuadrado>{
	//atributos
	private boolean status = true;
	private FichaCuadrada arriba = null;
	private FichaCuadrada abajo = null;
	private FichaCuadrada izquierda = null;
	private FichaCuadrada derecha = null;
	private static Cuadrado ficha;
	//constructor
	public FichaCuadrada() {
		super(ficha);
	}
	//metodo
	public Cuadrado getLado() {
		int num = (int)(Math.random()*4+1);
		switch(num){
			case 1: 
				return Cuadrado.UP;
			case 2:
				return Cuadrado.DOWN;
			case 3:
				return Cuadrado.LEFT;
			case 4:
				return Cuadrado.RIGHT;
			default:
				return null;
		}
	}
	public void mostrarLados() {
		if(this.abajo != null) {
			System.out.print(Cuadrado.DOWN+" ");
		}
		if(this.arriba != null) {
			System.out.print(Cuadrado.UP+" ");
		}
		if(this.derecha != null) {
			System.out.print(Cuadrado.RIGHT+" ");
		}
		if(this.izquierda != null) {
			System.out.print(Cuadrado.LEFT+" ");
		}	
	}
	public FichaCuadrada getArriba() {
		return arriba;
	}
	public FichaCuadrada getAbajo() {
		return abajo;
	}
	public FichaCuadrada getIzquierda() {
		return izquierda;
	}
	public FichaCuadrada getDerecha() {
		return derecha;
	}
	public void setArriba(FichaCuadrada arriba) {
		this.arriba = arriba;
	}
	public void setAbajo(FichaCuadrada abajo) {
		this.abajo = abajo;
	}
	public void setIzquierda(FichaCuadrada izquierda) {
		this.izquierda = izquierda;
	}
	public void setDerecha(FichaCuadrada derecha) {
		this.derecha = derecha;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}