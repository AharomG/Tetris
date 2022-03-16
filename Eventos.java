package parteLogica;

import java.util.ArrayList;

public class Eventos {
	
	//atributos
	private static int altura;
	//constructor
	
	//metodos
	public static void imprimirTC(Tablero<Casilla<FichaCuadrada>> tablero, int columna, int fila) {
		int impresion = 0;
		for(int i = 0; i < columna*fila;) {
			for(int j = 0; j < fila; j++) {
				if(tablero.getCasillas().get(j+i).isOcupado()) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
				if(j == fila-1)
					impresion = j+1+i;
			}
			i = impresion;
			System.out.println();
			
		}
	}
	public static boolean insertarFTabC(Tablero<Casilla<FichaCuadrada>> tablero, FichaCuadrada ficha, int columna, int fila, int posicionF) {
		int posicion = posicionF-1;
		int arriba = 0, abajo = 0, izquierda = 0, derecha = 0;
		int ladosDisp = 0;
		if(!tablero.getCasillas().get(posicion).isOcupado()) {
			if(ficha.getArriba() != null ) {
				arriba++;
				if(posicion > (fila-1) && !tablero.getCasillas().get(posicion-fila).isOcupado()) {
					ladosDisp++;
				}
			}
			if(ficha.getAbajo() != null) {
				abajo++;
				if(posicion < ((fila*columna)-fila) && !tablero.getCasillas().get(posicion+fila).isOcupado()) {
					ladosDisp++;
				}
			}
			if(ficha.getDerecha() != null) {
				derecha++;
				altura = 1;
				for(int e = 1; e <= columna; e++) {
					if(posicion <= e*fila-1) {
						altura = e;
						break;
					}
				}
				if(posicion < (fila*altura)-1 && !tablero.getCasillas().get(posicion+1).isOcupado()) {
					ladosDisp++;
				}
			}
			if(ficha.getIzquierda() != null) {
				izquierda++;
				altura = 1;
				for(int e = 1; e <= columna; e++) {
					if(posicion <= e*fila-1) {
						altura = e;
						break;
					}
				}
				if(posicion != 0 && posicion != (fila*altura)-fila && !tablero.getCasillas().get(posicion-1).isOcupado()) {
					ladosDisp++;
				}
			}
			if((arriba+abajo+derecha+izquierda) == ladosDisp) {
				Casilla<FichaCuadrada> fichaC = new Casilla<FichaCuadrada>(ficha);
				tablero.getCasillas().set(posicion, fichaC);
				tablero.getCasillas().get(posicion).ocupada();
				if(arriba == 1) {
					Casilla<FichaCuadrada> fichaA = new Casilla<FichaCuadrada>(ficha.getArriba());
					tablero.getCasillas().set(posicion-fila, fichaA);
					tablero.getCasillas().get(posicion-fila).ocupada();
				}
				if(abajo == 1) {
					Casilla<FichaCuadrada> fichaAb = new Casilla<FichaCuadrada>(ficha.getAbajo());
					tablero.getCasillas().set(posicion+fila, fichaAb);
					tablero.getCasillas().get(posicion+fila).ocupada();
				}
				if(derecha == 1) {
					Casilla<FichaCuadrada> fichaD = new Casilla<FichaCuadrada>(ficha.getDerecha());
					tablero.getCasillas().set(posicion+1, fichaD);
					tablero.getCasillas().get(posicion+1).ocupada();
				}
				if(izquierda == 1) {
					Casilla<FichaCuadrada> fichaI = new Casilla<FichaCuadrada>(ficha.getIzquierda());
					tablero.getCasillas().set(posicion-1, fichaI);
					tablero.getCasillas().get(posicion-1).ocupada();
				}
				return true;
			}else {
				//System.out.println(" La ficha no se puede colocar en este lugar");
				return false;
			}
		}else {
			//System.out.println(" Este espacio esta ocupado");
			return false;
		}
	}
	public static ArrayList<FichaCuadrada> verificarTabC(Tablero<Casilla<FichaCuadrada>> tablero, int columna, int fila, ArrayList<FichaCuadrada> fichasJuego) {
		ArrayList<Integer> ficha1 = new ArrayList<>();
		ArrayList<Integer> ficha2 = new ArrayList<>();
		ArrayList<Integer> ficha3 = new ArrayList<>();
		for(int i=0; i < (columna*fila);i++) {
			if(!tablero.getCasillas().get(i).isOcupado()) {
				for(int j=0; j< 3;j++) {
					if(fichasJuego.get(j).getStatus()) {
						int lados = 0;
						int ladosLibres = 0;
						if(fichasJuego.get(j).getArriba() != null) {
							lados++;
							if(i > (fila-1) && !tablero.getCasillas().get(i-fila).isOcupado()) {
								ladosLibres++;
							}
						}
						if(fichasJuego.get(j).getAbajo() != null) {
							lados++;
							if(i < ((fila*columna)-fila) && !tablero.getCasillas().get(i+fila).isOcupado()) {
								ladosLibres++;
							}
						}
						if(fichasJuego.get(j).getDerecha() != null) {
							lados++;
							altura = 1;
							for(int e = 1; e <= columna; e++) {
								if(i <= e*fila-1) {
									altura = e;
									break;
								}
							}
							if(i < (fila*altura)-1 && !tablero.getCasillas().get(i+1).isOcupado()) {
								ladosLibres++;
							}
						}
						if(fichasJuego.get(j).getIzquierda() != null) {
							lados++;
							altura = 1;
							for(int e = 1; e <= columna; e++) {
								if(i <= e*fila) {
									altura = e;
									break;
								}
							}
							if(i != 0 && i != (fila*altura)-fila && !tablero.getCasillas().get(i-1).isOcupado()) {
								ladosLibres++;
							}
						}
						if(lados == ladosLibres && j == 0) {
							ficha1.add(i);
						}else if(lados == ladosLibres && j == 1) {
							ficha2.add(i);
						}else if(lados == ladosLibres && j == 2) {
							ficha3.add(i);
						}
					}	
				}
			}
		}
		if(ficha1.size() == 0)
			fichasJuego.get(0).setStatus(false);
		else
			fichasJuego.get(0).setStatus(true);
		if(ficha2.size() == 0)
			fichasJuego.get(1).setStatus(false);
		else
			fichasJuego.get(1).setStatus(true);
		if(ficha3.size() == 0)
			fichasJuego.get(2).setStatus(false);
		else
			fichasJuego.get(2).setStatus(true);
		
		return fichasJuego;
	}
	public static int vaciarTabC(Tablero<Casilla<FichaCuadrada>> tablero, int columna, int fila) {
		int completaC = 0;
		int completaF = 0;
		ArrayList<Integer> borrarC = new ArrayList<>();
		ArrayList<Integer> borrarF = new ArrayList<>();
		int multiplicador = 1;
		int puntuacion = 0;
		for(int i = 0; i < columna; i++) {
			for(int j = i; j < columna*fila; j+=columna) {
				if(tablero.getCasillas().get(j).isOcupado()) {
					completaC++;
				}
			}
			if(completaC == columna) {
				borrarC.add(i);
			}
			completaC = 0;
		}
		for(int i = 0; i < fila*columna; i+=fila) {
			int repetir = 0;
			for(int j = i; repetir < fila; j++) {
				if(j < fila*columna) {
					if(tablero.getCasillas().get(j).isOcupado()) {
						completaF++;
					}
				}
				repetir++;
			}
			if(completaF == fila && i >= fila) {
				borrarF.add(i);
				completaF = 0;	
			}else if(i < fila && completaF == fila) {
				borrarF.add(0);
				completaF = 0;
			}else {
				completaF = 0;
			}
		}
		multiplicador = borrarC.size() + borrarF.size();
		if(borrarC.size() > 0) {
			for(int i = 0; i < borrarC.size(); i++) {
				for(int j = borrarC.get(i); j < columna*fila; j+=columna) {
					tablero.getCasillas().get(j).setOcupado(false);
					puntuacion++;
				}
			}
		}
		if(borrarF.size() > 0) {
			for(int i = 0; i < borrarF.size(); i++) {
				int repetir = 0;
				for(int j = borrarF.get(i); repetir < fila; j++) {
					tablero.getCasillas().get(j).setOcupado(false);
					puntuacion++;
					repetir++;
				}
			}	
		}
		return puntuacion*multiplicador;
	}
}
