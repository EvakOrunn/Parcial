/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Luis Cruz
 */
public class HoraInvalidaExcepcion  extends Exception{

    public HoraInvalidaExcepcion() {
        super("Hora invalida");
    }
    
    public HoraInvalidaExcepcion(String message){
        super(message);
    }
    
}
