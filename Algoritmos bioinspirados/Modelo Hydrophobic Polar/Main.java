import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * @author J. Carrero
 *
 */
public class Main {

	/**
	 * M�todo que recibe una cadena de amino�cidos como argumento
	 * @param args
	 */
    public static void main(String[] args) {
        String chain = args[0];
        System.out.println("Tu cadena de amino�cidos es: " + chain);
        for (int i = 0; i < chain.length(); i++) { // Comprobar si la cadena de amino�cidos es correcta
            char c = chain.charAt(i);
            if (c != 'H' && c != 'P')
            	throw new IllegalArgumentException("\n Ops! Parece que tu cadena de amino�cidos no es correcta. \n" +
                									" Por favor, ejecuta el programa con una cadena v�lida.");
        }
        Protein.run(chain);
    }
}
