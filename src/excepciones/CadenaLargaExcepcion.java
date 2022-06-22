/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Luis Cruz
 */
public class CadenaLargaExcepcion extends Exception{

    public CadenaLargaExcepcion() {
        super("La cadena supero el limite de caracteres");
    }

    public CadenaLargaExcepcion(String message) {
        super(message);
    }
    
}
