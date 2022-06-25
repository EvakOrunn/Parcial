package utilidad;

import excepciones.NumeroNegativoExcepcion;
import entradaSalida.*;

/**
 *
 * @author Luis Cruz
 * 
 * Una clase para representar el funcionamiento de una Fecha con el formato gregoriano
 * por tanto la salida de la informacion sera en el siguiente formato: DD/MM/AAAA
 */
public class Fecha {

    private int dia;
    private int mes;
    private int año;

    /**
     * Constructor por defecto de la clase, este inicializa los valores de las variables
     * miembro en 0
     */
    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.año = 0;
    }

    /**
     * El constructor solicita los datos para luego asígnarlos a las variables miembro de
     * la clase por medio de los parametros de entrada
     * @param dia               dia del mes que sera asígnado a la variable dia
     * @param mes               mes del año que sera asígando a la variable mes
     * @param año               año que sera asignado a la variable año
     */
    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    /**
     * El metodo devuelve el dia del año 
     * 
     * @return                  retorna el dia del mes
     */
    public int getDia() {
        return dia;
    }

    /**
     * Establece el dia del mes
     * 
     * @param dia               dia del mes asígando a la variable miembro dia
     * @throws NumeroNegativoExcepcion  de ingresarse un numero negativo para
     * la asignacion del dia del mes
     */
    public void setDia(int dia) throws NumeroNegativoExcepcion {
        if (dia < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.dia = dia;
    }

    /**
     * El metodo devuelve el mes del año
     * 
     * @return                  retorna el mes del año
     */
    public int getMes() {
        return mes;
    }

    /**
     * Establece el mes del año
     * 
     * @param mes               mes del año que sera asignado a la variable miembro
     *                          del mes
     * @throws NumeroNegativoExcepcion  de ingresarse un numero negativo para
     * la asignacion del mes del año
     */
    public void setMes(int mes) throws NumeroNegativoExcepcion {
        if (mes < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.mes = mes;
    }

    /**
     * El metodo devuelve el año ingresado
     * 
     * @return                  retorna el año ingresado
     */
    public int getAño() {
        return año;
    }

    /**
     * Establece el año 
     * 
     * @param año               año que sera asígnado a la variable miembro año
     * @throws NumeroNegativoExcepcion  de ingresarse un numero negativo para
     * la asignacion del año 
     */
    public void setAño(int año) throws NumeroNegativoExcepcion {
        if (año < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.año = año;
    }

    /**
     * El metodo se encargar de grabar los datos de la Fecha llamando otros tres
     * metodos privados que se encargan de hacer la carga unicataria de cada dato
     */
    public void cargarFecha() {
        grabarAño();
        grabarMes();
        grabarDia();
    }

    /**
     * Solicita al usuario el ingreso del dia, este sera validado por un metodo
     * privado que determinara si el dato cumple con los requisitos para ser
     * asignado, de no serlo se mostrara un mensaje al usuario avisando de 
     * dicho resultado y se le solicitara su ingreso nuevamente hasta cumplir
     * con los parametros.
     */
    private void grabarDia() {
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

    /**
     * Solicita al usuario que ingrese el mes, este sera validado por un metodo
     * privado que determinara si el dato cumple con los requisitos para ser
     * asignado, de no serlo se mostrara un mensaje al usuario avisando de
     * dicho resultado y se le solicitara su ingreso nuevamente hasta cumplir
     * con los parametros
     */
    private void grabarMes() {
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

    /**
     * Solicita al usuario que ingrese el año, este sera validado por un metodo
     * privado que determinara si el dato cumple con los requisitos para ser
     * asignado, de no serlo se mostrara un mensaje al usuario avisando de
     * dicho resultado y se le solicitara su ingreso nuevamente hasta cumplir
     * con los parametros
     */
    private void grabarAño() {
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

    /**
     * El metodo esta comentado devido a que es una version para cuando el programa
     * es ejecutado en una version inferior al JDK 14 que permite un formato distitno
     * el switch
     * 
     * @param dia
     * @return
    private boolean validarDia(int dia) {
        boolean valid = false;
        switch (this.mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                valid = dia > 0 && dia < 32;
                break;
            case 2:
                if (bisiesto(this.año)) {
                    valid = dia > 0 && dia < 30;
                } else {
                    valid = dia > 0 && dia < 29;
                }
                break;
                case 4:
                case 6:
                case 9:
                case 11:
                    valid = dia > 0 && dia > 31;
                    break;
            default:
                throw new AssertionError();
        }
    }
    */

    /**
     * El metodo valida el dia ingresado por el usuario segun el mes correspondiente
     * dado que para cada mes la cantidad de dias no es la misma y ademas, puede que
     * el año en que se encuentra puede ser bisiesto. Se realiza lo siguiente:
     * Para los meses de: Enero, Marzo, Mayo, Julio, Agosto, Octubre y Diciembre el
     * dia sera valido si y solo si es mayor a 0 y menos a 32.
     * 
     * Para el mes de Febreso primero se llamara a un metodo privado para validar si
     * el año en que se encuentra es bisiesto o no. De ser bisiesto el dia sera
     * valido si y solo si es mayor a 0 y menor a 30, caso contrario el dia sera 
     * valido si y solo si es mayor a 0 y menor a 29.
     * 
     * Y para los meses de Abril, Junio, Septiembre y Noviembre el dia sera valido
     * si y solo si es mayor a 0 y menor a 31.
     * 
     * @param dia               dia a validar.
     * 
     * @return                  devuelve true en caso de que el dia ingresado para
     *                          ser validado cumple con los parametros, y false en
     *                          caso de que no cumpla con ellos.
     */
    private boolean validarDia(int dia) {
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

    /**
     * El metodo valida el mes ingresado por el usuario, este sera valido en caso de
     * que el mes sea mayor a 0 y menos 13
     * 
     * @param mes               mes a validar
     * 
     * @return                  devuelve true en caso de que el mes ingresado para
     *                          ser validado cumple con los parametros, y false en
     *                          caso de que no cumpla con ellos
     * 
     */
    private boolean validarMes(int mes) {
        return (mes > 0 && mes < 13);
    }

    /**
     * El metodo valida el año ingresado por el usuario, este sera valido en caso de
     * que el año ingresado sea mayor a 1900
     * 
     * @param año               año a validar
     * 
     * @return                  devuelve true en caso de que el año ingresado para
     *                          ser validado cumple con los parametros, y false en
     *                          caso de que no cumpla con ellos
     */
    private boolean validarAño(int año) {
        return año > 1900;
    }

    /**
     * El metodo determina si el año ingesado es bisiesto, para ello se debe aplicar
     * una formula, siendo esta la aplicacion del modulo para obtener el resto de la
     * operacion, si es igual a 0 entonces el año es bisiesto. 
     * 
     * @param anio              año a validar
     * 
     * @return                  devuelve true en caso de que el año ingresado para
     *                          ser validado cumple con los parametros, y false en
     *                          caso de que no cumplca con ellos.
     */
    private boolean bisiesto(int anio) {
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
