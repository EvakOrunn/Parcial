package datos;

import entradaSalida.EntradaSalida;
import excepciones.CadenaLargaExcepcion;
import excepciones.NumeroNegativoExcepcion;
import excepciones.StringInvalidoExcepcion;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistencia.Grabable;
import persistencia.Registro;

public class VueloModificado implements Grabable {

    private int codigo; //4 bytes
    private String lugarDestino; //40*2 = 80 bytes
    private LocalDate fecha; //12 bytes
    private LocalTime hora; //8 bytes

    private final static int TAMREG = 104;
    private final static int TAMARCH = 100;

    public VueloModificado() {
        this.codigo = 0;
        this.lugarDestino = "NaN";
        this.fecha = LocalDate.of(2000, 1, 1);
        this.hora = LocalTime.of(0,0);
    }

    public VueloModificado(int codigo, String lugarDestino, LocalDate fecha, LocalTime hora) {
        this.codigo = codigo;
        this.lugarDestino = lugarDestino;
        this.fecha = fecha;
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
            a.writeInt(this.codigo);
            Registro.writeString(a, lugarDestino, 40);
            a.writeInt(this.fecha.get(ChronoField.YEAR));
            a.writeInt(this.fecha.get(ChronoField.MONTH_OF_YEAR));
            a.writeInt(this.fecha.get(ChronoField.DAY_OF_MONTH));
            a.writeInt(this.hora.getHour());
            a.writeInt(this.hora.getMinute());
        } catch (IOException e) {
            System.out.println("Error al grabar: " + e.getMessage());
        }

    }

    @Override
    public void leer(RandomAccessFile a) {
        try {
            this.codigo = a.readInt();
            this.lugarDestino = Registro.readString(a, 40);
            this.fecha = LocalDate.of(a.readInt(), a.readInt(), a.readInt());
            this.hora = LocalTime.of(a.readInt(), a.readInt());
        } catch (IOException ex) {
            System.out.println("Error al leer: " + ex.getMessage());
        }

    }

    @Override
    public void mostrarRegistro() {
        this.toString();

    }

    @Override
    public void cargarDatos() {
        leerCodigo();
        leerLugarDestino();
        leerFecha();
        leerHora();
    }

    private void leerCodigo() {
        int codigoAux;
        boolean bandera = true;
        do {
            try {
                EntradaSalida.mostrarMensaje("Ingrese el codigo de vuelo: ");
                codigoAux = EntradaSalida.leerInt();
                this.setCodigo(codigoAux);
                bandera = false;
            } catch (NumeroNegativoExcepcion | NumberFormatException ex) {
                System.out.println("Error al setear el codigo: " + ex.getMessage());
            }
        } while (bandera);
    }

    private void leerLugarDestino() {
        String lugarAux;
        boolean bandera = true;
        do {
            try {
                EntradaSalida.mostrarMensaje("Ingrese el lugar de destino: ");
                lugarAux = EntradaSalida.leerString();
                this.setLugarDestino(lugarAux);
                bandera = false;
            } catch (StringInvalidoExcepcion | CadenaLargaExcepcion ex) {
                System.out.println("Error al setear el lugar de destino: " + ex.getMessage());
            }
        } while (bandera);
    }

    private void leerFecha() {
        int dia, mes, año;
        boolean bandera = true;
        do {
            try {
                EntradaSalida.mostrarMensajeLN("Ingrese la fecha del vuelo: ");
                EntradaSalida.mostrarMensaje("Ingrese el dia: ");
                dia = EntradaSalida.leerInt();
                EntradaSalida.mostrarMensaje("Ingrese el mes: ");
                mes = EntradaSalida.leerInt();
                EntradaSalida.mostrarMensaje("Ingrese el año: ");
                año = EntradaSalida.leerInt();
                this.fecha = LocalDate.of(año, mes, dia);
                bandera = false;
            } catch (NumberFormatException | DateTimeException ex) {
                System.out.println("Error al setear la fecha: " + ex.getMessage());
            }
        } while (bandera);
    }

    private void leerHora() {
        int hora,minuto;
        boolean bandera = true;
        do{
            try {
                EntradaSalida.mostrarMensajeLN("Ingrese la fecha del vuelo: ");
                EntradaSalida.mostrarMensaje("Ingrese la hora: ");
                hora = EntradaSalida.leerInt();
                EntradaSalida.mostrarMensaje("Ingrese los minutos: ");
                minuto = EntradaSalida.leerInt();
                this.hora = LocalTime.of(hora, minuto);
                bandera = false;
            } catch (NumberFormatException | DateTimeException ex) {
                System.out.println("Error al setear la hora: " + ex.getMessage());
            }
        }while(bandera);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws NumeroNegativoExcepcion {
        if (codigo < 0) {
            throw new NumeroNegativoExcepcion();
        }
        this.codigo = codigo;
    }

    public String getLugarDestino() {

        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) throws StringInvalidoExcepcion, CadenaLargaExcepcion {
        Pattern pat = Pattern.compile("[^(\\D*)(\\s*)]|[^(\\w*)(\\s)]|[()*_]");
        Matcher mat = pat.matcher(lugarDestino);
        if (mat.find()) {
            throw new StringInvalidoExcepcion();
        }
        if (lugarDestino.length() > 40) {
            throw new CadenaLargaExcepcion();
        }

        this.lugarDestino = lugarDestino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {

        this.hora = hora;
    }
    
    private String mostrarFecha(){
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         return fecha.format(formato);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s", this.codigo));
        sb.append(String.format("%-40s", this.lugarDestino));
        sb.append(String.format("%-16s", mostrarFecha()));
        sb.append(String.format("%-15s", this.hora.toString()));
        return sb.toString();
    }

}
