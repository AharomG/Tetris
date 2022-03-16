package parteLogica;

import java.util.ArrayList;

public class GeneradorF {
	//atributos
	
	//constructor
	public GeneradorF() {
	}
	//metodos
	public static FichaCuadrada generarFCuadrado(){
		FichaCuadrada nueva = new FichaCuadrada();
		int num = (int)(Math.random()*4+1);
		for(; num > 0; num--) {
			Cuadrado lado = nueva.getLado();
			if(lado == Cuadrado.UP) {
				FichaCuadrada arriba = new FichaCuadrada();
				nueva.setArriba(arriba);
			}else if(lado == Cuadrado.DOWN) {
				FichaCuadrada abajo = new FichaCuadrada();
				nueva.setAbajo(abajo);
			}else if(lado == Cuadrado.LEFT) {
				FichaCuadrada izquierda = new FichaCuadrada();
				nueva.setIzquierda(izquierda);
			}else if(lado == Cuadrado.RIGHT) {
				FichaCuadrada derecha = new FichaCuadrada();
				nueva.setDerecha(derecha);
			}
		}
		//dependiendo de la ficha se selecciona uno o otro lado
		return nueva;
	}	
	public static FichaTriangular generarFTriangulo() {
		FichaTriangular nueva = new FichaTriangular();
		int num = (int)(Math.random()*3+1);
		for(int i=num; num > 0; num--) {
			Triangulo lado = nueva.getLado();
			if(lado == Triangulo.DOWN) {
				FichaTriangular abajo = new FichaTriangular();
				nueva.setAbajo(abajo);
			}else if(lado == Triangulo.LEFT) {
				FichaTriangular izquierda = new FichaTriangular();
				nueva.setIzquierda(izquierda);
			}else if(lado == Triangulo.RIGHT) {
				FichaTriangular derecha = new FichaTriangular();
				nueva.setDerecha(derecha);
			}
		}
		return nueva;
	}
	public static ArrayList<FichaCuadrada> setFichasC(ArrayList<FichaCuadrada> ficha) {
		if(ficha == null) {
			ficha = new ArrayList<FichaCuadrada>();
			for(int i = 0; i < 3; i++) {
				ficha.add(generarFCuadrado());
			}
		}else {
			ficha.add(generarFCuadrado());	
		}
		return ficha;		
	}
	public static ArrayList<FichaTriangular> setFichasT(ArrayList<FichaTriangular> ficha){
		if(ficha == null) {
			ficha = new ArrayList<FichaTriangular>();
			for(int i = 0; i < 3; i++) {
				ficha.add(generarFTriangulo());
			}
		}else {
			for(int i = ficha.size(); i < 3; i++) {
				ficha.add(generarFTriangulo());
			}
		}
		return ficha;
	}
}