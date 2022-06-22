/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Luis Cruz
 */
public class NumeroNegativoExcepcion extends Exception{

    public NumeroNegativoExcepcion(String message) {
        super(message);
    }

    public NumeroNegativoExcepcion() {
        super("Se ingreso un numero negativo");
    }
    
}
