/**
 * Clase para facilitar operaciones de carga por teclado en consola estándar
 * Todos los métodos de esta clase son estáticos, y por lo tanto pueden ser invocados sin tener que 
 * crear objetos de la clase. Es suficiente con nombrar la clase al invocar el método: int x = Consola.readInt(); 
**/

package entradaSalida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntradaSalida {

    /**
     * Lee un Integer desde teclado
     * @return el Integer leido
     */
    public static Integer leerInteger(){
        while (true) {
            try {
                return Integer.parseInt(leerDato());
            } catch (NumberFormatException e) {
                System.out.println("No es un integer. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Muestra un Integer
     */
    public static void mostrarEntero(String mensaje, Integer valor){
        System.out.println(mensaje + valor);
    }

    /**
     * Lee un string desde teclado. El string termina con un salto de linea
     *
     * @return el string leido (sin el salto de linea)
     */
    public static String leerString() {
        while (true) {
            try {
                return leerDato();
            } catch (NumberFormatException e) {
                System.out.println("No es un String. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Muestra un String
     */
    public static void mostrarMensaje(String mensaje){
        System.out.print(mensaje);
    }

    /**
     * Muestra un String
     */
    public static void mostrarMensajeLN(String mensaje){
        System.out.println(mensaje);
    }

    /**
     * Lee un int desde teclado
     *
     * @return el valor cargado, como un int
     */
    public static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un integer. Por favor, pruebe otra vez!");
            }
        }
    }
    
    public static float leerFloat(){
        while (true){
            try {
                return Float.parseFloat(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un float. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Lee un double desde teclado. La entrada termina con un salto de linea
     *
     * @return el valor cargado, como un double
     */
    public static double leerPtoFlotante() {
        while (true) {
            try {
                return Double.parseDouble(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un double. " + "Por favor, pruebe otra vez!");
            }
        }
    }
    
    public static char leerChar() {
        while (true) {
            try {
                return (char) leerDato().charAt(0);
            } catch (NumberFormatException e) {
                System.out.println("No es un integer. Por favor, pruebe otra vez!");
            }
        }
    }

    public static String ajustarString(String cad, int longitud) {
        if (cad.length() > longitud) {
            cad = cad.substring(0, longitud);
        } else {
            for (int i = cad.length(); i < longitud; i++) {
                cad = cad + " ";
            }
        }
        return cad;
    }

    public static boolean confirmar() {
        mostrarMensaje("Confirmar S/N: ");
        char opc = ' ';
        while(opc != 'n'&& opc != 'N'&& opc != 's' && opc != 'N'){
            mostrarMensaje("...Ingrese s/S/n/N para continuar...");
            opc = leerChar();
        }
        return (opc != 'n' && opc != 'N');
    } 

    public static String leerDato() {
        String sdato = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader flujoE = new BufferedReader(isr);

        try {
            sdato = flujoE.readLine();
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }

        return sdato;
    }
}