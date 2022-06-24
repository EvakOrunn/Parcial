/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import utilidad.Fecha;
import utilidad.Hora;
import persistencia.Grabable;
import persistencia.Registro;
import entradaSalida.EntradaSalida;
import excepciones.*;

/**
 *
 * @author Luis Cruz
 */
public class Vuelo implements Grabable {

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

    public int getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(int codVuelo) throws NumeroNegativoExcepcion {
        if (codVuelo < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.codVuelo = codVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) throws CadenaLargaExcepcion {
        if (destino.length() > 40) {
            throw new CadenaLargaExcepcion();
        }
        this.destino = destino;
    }

    public Fecha getFechaDestino() {
        return fechaDestino;
    }

    public void setFechaDestino(Fecha fechaDestino) {
        this.fechaDestino = fechaDestino;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
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
            System.out.println("Error al grabar el registro: " + ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile a) {
        try {
            codVuelo = a.readInt();
            destino = Registro.readString(a, 40);
            fechaDestino = new Fecha(a.readInt(), a.readInt(), a.readInt());
            hora = new Hora(a.readInt(), a.readInt());
        } catch (IOException ex) {
            System.out.println("Error al leer el registro: " + ex.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void mostrarRegistro() {
        this.toString();
        System.out.println("\t");
    }

    @Override
    public void cargarDatos() {
        cargarCodigoVuelo();
        cargarDestino();
        System.out.println("\tHora del Vuelo");
        this.hora.cargarHora();
        System.out.println("\tFecha del Vuelo");
        this.fechaDestino.cargarFecha();
    }
    
    private void cargarCodigoVuelo(){
        boolean flag = false;
        while (!flag) {            
            try {
                EntradaSalida.mostrarMensaje("Codigo de Vuelo:");
                int codAx = EntradaSalida.leerInt();
                setCodVuelo(codAx);
                flag = true;
            } catch (NumeroNegativoExcepcion | NumberFormatException e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar el Codigo de Vuelo:" + e.getMessage());
            }
        }
    }
    
    private void cargarDestino(){
        boolean flag = false;
        while (!flag) {            
            try {
                EntradaSalida.mostrarMensaje("Destino del Vuelo:");
                String desAx = EntradaSalida.leerString();
                setDestino(desAx);
                flag = true;
            } catch (CadenaLargaExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar el Destino del Vuelo:");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15d", codVuelo));
        sb.append(String.format("%-40s", destino));
        sb.append(String.format("%-15s", fechaDestino.toString()));
        sb.append(String.format("%-15s", hora.toString()));
        return sb.toString();
    }

}
