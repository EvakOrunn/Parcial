/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidad.Fecha;
import utilidad.Hora;
import persistencia.Grabable;
import persistencia.Registro;

/**
 *
 * @author Luis Cruz
 */
public class Vuelo implements Grabable{
    
    private int codVuelo; //4bytes
    private String destino; //40*2 = 80bytes
    private Fecha fechaDestino; //12bytes
    private Hora hora; //8bytes
    
    private final static int TAMREG = 104; //tamaño del registro
    private final static int TAMARCH = 100; //crea 100 registros vacios;

    public Vuelo(int codVuelo, String destino, Fecha fechaDestino, Hora hora) {
        this.codVuelo = codVuelo;
        this.destino = destino;
        this.fechaDestino = fechaDestino;
        this.hora = hora;
    }

    public Vuelo() {
        this.codVuelo = 0;
        this.destino = "NaN";
        this.fechaDestino = new Fecha();
        this.hora = new Hora();
    }

    @Override
    public int tamRegistro() {
        return TAMREG;
    }

    @Override
    public int tamArchivo() {
       return TAMARCH;
    }

    @Override
    public void grabar(RandomAccessFile a) {
        try {
            a.writeInt(this.codVuelo);
            Registro.writeString(a, destino, 40);
            a.writeInt(fechaDestino.getDia());
            a.writeInt(fechaDestino.getMes());
            a.writeInt(fechaDestino.getAño());
            a.writeInt(hora.getHora());
            a.writeInt(hora.getMinuto());
        } catch (IOException ex) {
            Logger.getLogger(Vuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void leer(RandomAccessFile a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
}
