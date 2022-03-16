package parteLogica;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	//atributos
	private static int puntuacion = 0;
	private static String forma = null;
	private static String tamano = null;
	private static Scanner entradaDatos = new Scanner(System.in);
	private static String tablero;
	private static FichaCuadrada fichaSeleccionada = new FichaCuadrada();
	//main
	public static void main(String[] args) {
		tablero = Carga.cargar();
		int slash = tablero.indexOf("/");
		forma = tablero.substring(0, slash);
		tamano = tablero.substring(slash+1, tablero.length());
		if(forma.equalsIgnoreCase("cuadrado")) {
			FichaCuadrada fichas = new FichaCuadrada();
			Casilla<FichaCuadrada> casillas = new Casilla<>(fichas);
			Tablero<Casilla<FichaCuadrada>> tablero = new Tablero<>(casillas);
			while(true) {
				System.out.println("------- Bienvenido!");
				System.out.println("------- 1.- Jugar");
				System.out.println("------- 2.- Salir");
				System.out.print("------- ");
				String opcion = entradaDatos.next();
				if(opcion.isBlank()) {
					System.out.println("OPCION NO VALIDA! Tiene que ingresar una opcion");
				}else if(opcion.length() > 1){
					System.out.println("OPCION NO VALIDA! Ingrese un numero valido");
				}else if(opcion.equalsIgnoreCase("1")) {
					if(forma.equalsIgnoreCase("cuadrado")) {
						ArrayList<FichaCuadrada> fichasJuego = null;
						fichasJuego = GeneradorF.setFichasC(fichasJuego);	
						int x = tamano.indexOf("x");
						int columna = 0;
						int fila = 0;	
						try {
							columna = Integer.parseInt(tamano.substring(0, x));
							fila = Integer.parseInt(tamano.substring(x+1, tamano.length()));
							for(int i=0; i < (columna*fila);i++) {
								Casilla<FichaCuadrada> casilla = new Casilla<>(new FichaCuadrada());
								tablero.getCasillas().add(casilla);
							}
							while(true) {
								for(int i=0; i < 3; i++) {
									System.out.print("La ficha numero "+(i+1)+" tiene esta forma: CENTER ");
									fichasJuego.get(i).mostrarLados();
									System.out.println();
								}
								System.out.print("---------- Puntuacion: "+puntuacion+"\n");	
								System.out.println("---------- Tablero: ");	
								Eventos.imprimirTC(tablero, columna, fila); //imprime el tablero
								fichasJuego = Eventos.verificarTabC(tablero, columna, fila, fichasJuego); //evento para bloquear las fichas que no se pueden colocar en ningun lado
								if(fichasJuego.get(0).getStatus() || fichasJuego.get(1).getStatus() || fichasJuego.get(2).getStatus()) {
									//seleecionar la ficha
									System.out.print("---------- Escriba el numero de la ficha que desea jugar: ");
									String opcionF = entradaDatos.next();
									System.out.println();
									if(opcionF.isBlank() || opcionF.length() > 1) {
										System.out.println("OPCION NO VALIDA! Tiene que ingresar una opcion");
										break;
									}else if(opcionF.equalsIgnoreCase("1") || opcionF.equalsIgnoreCase("2") || opcionF.equalsIgnoreCase("3")) {
										//FichaCuadrada fichaSeleccionada = new FichaCuadrada();
										switch(opcionF){
											case "1": {if(fichasJuego.get(0).getStatus()) {
														 fichaSeleccionada = fichasJuego.get(0);
													  }
													  break;
											}
											case "2": {if(fichasJuego.get(1).getStatus()) {
															fichaSeleccionada = fichasJuego.get(1);
													  }
													  break;
												}
											case "3": {if(fichasJuego.get(2).getStatus()) {
															fichaSeleccionada = fichasJuego.get(2);
													  }
													  break;
											}
											default: {System.out.println("OPCION NO VALIDA! Ha introducido una opcion erronea");
													  break;
													  }
											}	
										//pedir los datos para colocar la ficha
										if(fichaSeleccionada.getStatus()) {
											System.out.print("------- Escriba la posicion donde desea colocar la ficha: ");
											String posicionF = entradaDatos.next();
											System.out.println();
											//verifico si se puede colocar la ficha y si se puede colocarla
											if(!posicionF.isBlank() && posicionF.length() >= 1) {
												try {
													if(Integer.parseInt(posicionF) <= fila*columna) {
														try {
																if(Eventos.insertarFTabC(tablero, fichaSeleccionada, columna,fila, Integer.parseInt(posicionF))) {
																	//verificar si llena la linea o columna para vaciarlo
																	puntuacion = puntuacion + Eventos.vaciarTabC(tablero, columna, fila);
																	fichasJuego = Eventos.verificarTabC(tablero, columna, fila, fichasJuego);
																	//quito la ficha y pido una nueva
																	fichasJuego.remove(Integer.parseInt(opcionF)-1);
																	fichasJuego = GeneradorF.setFichasC(fichasJuego);
																	//verifico el estado de las fichas para gamer over
																	if(!fichasJuego.get(0).getStatus() && !fichasJuego.get(1).getStatus() && !fichasJuego.get(2).getStatus()) {
																		System.out.println("--------- Juego Terminado! Todas sus fichas estan bloqueadas");
																		break;
																	}
																}else {
																	System.out.println("La ficha no se puede colocar");
																}	
														}catch(NumberFormatException e){
															System.out.println("ERROR! Ingrese un numero por columna o fila");
														}
													}else {
														System.out.println("La posicion no debe sobrepasar el numero de filas, en este caso: "+fila*columna);
													}
												}catch(NumberFormatException e){
													System.out.println("ERROR! Ingrese un numero para la fila de la ficha");
												}
											}
										}else{
											System.out.println("--------- Este ficha esta bloqueada, no se puede colocar en ninguna posicion, libere espacio en el tablero");
										}
									}							
								}else {
									System.out.println("--------- Juego Terminado! Todas sus fichas estan bloqueadas");
									break;
								}
							}
						} catch(NumberFormatException e) {
							System.out.println("ERROR! Revisa la opcion tamaño del JSON");
						}
					}else if(forma.equalsIgnoreCase("triangulo")) {
						ArrayList<FichaTriangular> fichasJuego = new ArrayList<>();
						GeneradorF.setFichasT(fichasJuego);
						for(int i=0; i < 3; i++) {
							System.out.print("La ficha numero "+(i+1)+" tiene esta forma: ");
							fichasJuego.get(i).mostrarLados();
							System.out.println();
							//seguir el juego con fichas triangulares
						}
					}
				}else if(opcion.equalsIgnoreCase("2")) {
					System.exit(0);
				}else if(opcion.equalsIgnoreCase("1") || opcion.equalsIgnoreCase("2")) {
					System.out.println("OPCION NO VALIDA! Ingrese un numero valido");
				}
			}
		}else if(forma.equalsIgnoreCase("triangulo")) {
			FichaTriangular fichas = new FichaTriangular();
			Casilla<FichaTriangular> casillas = new Casilla<>(fichas);
			Tablero<Casilla<FichaTriangular>> tablero = new Tablero<>(casillas);
			//Seguir el juego con casillas triangulares
		}
	}

}