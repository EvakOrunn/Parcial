/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Luis Cruz
 */
public class MinutoInvalidoExcepcion extends Exception{

    public MinutoInvalidoExcepcion() {
        super("Minuto Invalido");
    }

    public MinutoInvalidoExcepcion(String message) {
        super(message);
    }
    
}
