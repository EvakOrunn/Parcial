/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidad;

import excepciones.NumeroNegativoExcepcion;
import entradaSalida.*;

/**
 *
 * @author Luis Cruz
 */
public class Fecha {
    
    private int dia;
    private int mes;
    private int año;

    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.año = 0;
    }

    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) throws NumeroNegativoExcepcion{
        if (dia < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) throws NumeroNegativoExcepcion{
        if (mes < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) throws NumeroNegativoExcepcion{
        if (año < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.año = año;
    }
    
    public void cargarFecha(){
        grabarAño();
        grabarMes();
        grabarDia();
    }
    
    private void grabarDia(){
        boolean flag = false;
        while (!flag) {            
            try {
                EntradaSalida.mostrarMensaje("Dia:");
                int auxD = EntradaSalida.leerInt();
                setDia(auxD);
                if (validarDia(auxD)) {
                    flag = true;
                } else {
                    EntradaSalida.mostrarMensajeLN("Dia invalido");
                }
            } catch (NumberFormatException | NumeroNegativoExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar los datos:" + e.getMessage());
            }
        }
    }
    
    private void grabarMes(){
        boolean flag = false;
        while (!flag) {            
            try {
                EntradaSalida.mostrarMensaje("Mes:");
                int auxM = EntradaSalida.leerInt();
                setMes(auxM);
                if (validarMes(auxM)) {
                    flag = true;
                } else {
                    EntradaSalida.mostrarMensajeLN("Mes invalido");
                }
            } catch (NumberFormatException | NumeroNegativoExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar los datos:" + e.getMessage());
            }
        }
    }
    
    private void grabarAño(){
        boolean flag = false;
        while (!flag) {            
            try {
                EntradaSalida.mostrarMensaje("Año:");
                int auxA = EntradaSalida.leerInt();
                setAño(auxA);
                if (validarAño(auxA)) {
                    flag = true;
                } else {
                    EntradaSalida.mostrarMensajeLN("Año invalido");
                }
            } catch (NumberFormatException | NumeroNegativoExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar los datos:" + e.getMessage());
            }
        }
    }
    
    private boolean validarDia(int dia){
        boolean valid = false;
        switch (this.mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> valid = dia > 0 && dia < 32;
            case 2 -> {
                if (bisiesto(this.año)) {
                    valid = dia > 0 && dia < 30;
                } else {
                    valid = dia > 0 && dia < 29;
                }
            }
            case 4, 6, 9, 11 -> valid = dia > 0 && dia < 31;
            default -> throw new AssertionError();
        }
        return valid;
    }
    
    private boolean validarMes(int mes){
        return (mes > 0 && mes < 13);
    }
    
    private boolean validarAño(int año){
        return año > 1900;
    }

    private boolean bisiesto(int anio){
        return ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)));
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.dia;
        hash = 23 * hash + this.mes;
        hash = 23 * hash + this.año;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fecha other = (Fecha) obj;
        if (this.dia != other.dia) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        return this.año == other.año;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dia).append("/").append(mes).append("/").append(año).append("\n");
        return sb.toString();
    }
    
}
