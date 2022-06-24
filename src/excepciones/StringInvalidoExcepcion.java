/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Usuario
 */
public class StringInvalidoExcepcion extends Exception {

    public StringInvalidoExcepcion() {
        System.out.println("String ingresado invalido");
    }

    public StringInvalidoExcepcion(String message) {
        super(message);
    }
    
    
    
}
