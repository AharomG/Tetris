package parteLogica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Carga {
	//atributos
	private static JSONParser parser = new JSONParser();
	private static JSONObject objeto;
	private static String tablero;
	//constructor
	
	//metodos
	public static String cargar() {
		try {
			objeto = (JSONObject) parser.parse(new FileReader("prototipo.json"));
			tablero = (String) objeto.get("forma");
			tablero = tablero + "/";
			tablero = tablero+(String) objeto.get("tamano");
		}catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ha habido un error mientras se trabajaba con el archivo");
        }catch (ParseException e) {
            e.printStackTrace();
            System.out.println("No se ha encontrado lo que se buscaba en el JSON");
        }
		return tablero;
	}
}