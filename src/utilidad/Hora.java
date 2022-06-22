/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidad;

import entradaSalida.EntradaSalida;
import excepciones.*;

/**
 *
 * @author Luis Cruz
 */
public class Hora {

    private int hora;
    private int minuto;

    public Hora() {
        this.hora = 0;
        this.minuto = 0;
    }

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) throws HoraInvalidaExcepcion, NumeroNegativoExcepcion {
        if (!(hora > -1 && hora < 24)) {
            throw new HoraInvalidaExcepcion();
        }
        if (hora < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) throws MinutoInvalidoExcepcion, NumeroNegativoExcepcion {
        if (!(minuto > -1 && minuto < 60)) {
            throw new MinutoInvalidoExcepcion();
        }
        if (minuto < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.minuto = minuto;
    }

    public void cargarHora() {
        grabarHora();
        grabarMinuto();
    }

    private void grabarHora() {
        boolean flag = false;
        while (!flag) {
            try {
                EntradaSalida.mostrarMensaje("Hora:");
                int auxH = EntradaSalida.leerInt();
                setHora(auxH);
                flag = true;
            } catch (NumeroNegativoExcepcion | HoraInvalidaExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar la hora:" + e.getMessage());
            }
        }
    }

    private void grabarMinuto() {
        boolean flag = false;
        while (!flag) {
            try {
                EntradaSalida.mostrarMensaje("Minuto:");
                int auxM = EntradaSalida.leerInt();
                setMinuto(auxM);
                flag = true;
            } catch (NumeroNegativoExcepcion | MinutoInvalidoExcepcion e) {
                EntradaSalida.mostrarMensajeLN("Error al grabar lo/s minuto/s:" + e.getMessage());
            }
        }

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.hora;
        hash = 53 * hash + this.minuto;
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
        final Hora other = (Hora) obj;
        if (this.hora != other.hora) {
            return false;
        }
        return this.minuto == other.minuto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hora).append(":").append(minuto).append("\n");
        return sb.toString();
    }

}
