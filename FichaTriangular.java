package parteLogica;

import java.util.ArrayList;

public class FichaTriangular extends Ficha<Triangulo> {
	//atributo
	private boolean status = true;
	private FichaTriangular abajo = null;
	private FichaTriangular izquierda = null;
	private FichaTriangular derecha = null;
	private static Triangulo forma;
	//constructor
	public FichaTriangular() {
		super(forma);
	}
	//metodos
	public Triangulo getLado() {
		int num = (int)(Math.random()*3+1);
		switch(num){
			case 1: 
				return Triangulo.DOWN;
			case 2:
				return Triangulo.LEFT;
			case 3:
				return Triangulo.RIGHT;
			default:
				return null;
		}
	}
	public void mostrarLados() {
		if(this.abajo != null) {
			System.out.print(Triangulo.DOWN+" ");
		}
		if(this.derecha != null) {
			System.out.print(Triangulo.RIGHT+" ");
		}
		if(this.izquierda != null) {
			System.out.print(Triangulo.LEFT+" ");
		}
	}
	public void setAbajo(FichaTriangular abajo) {
		this.abajo = abajo;
	}
	public void setIzquierda(FichaTriangular izquierda) {
		this.izquierda = izquierda;
	}
	public void setDerecha(FichaTriangular derecha) {
		this.derecha = derecha;
	}
}