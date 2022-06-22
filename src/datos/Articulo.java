package datos;

/**
 * Representa un artículo a la venta en un comercio cualquiera, que podrá ser
 * usado dentro de un Registro para grabar en un Archivo
 *
 */
import entradaDatos.Consola;
import java.io.*;
import persistencia.*;

public class Articulo implements Grabable {

    private int codArt;
    private String descripcion; // una cadena, que queremos sea de 50 caracteres máximo = 100 bytes en disco 
    private int codRubro;
    private int precio;

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 112; // cantidad de bytes que tendrá el registro
    // descripcion 100 bytes + codigo de artico 4 bytes + codigo de rubro 4 bytes + precio 4 bytes
    // este calculo debe realizarse para toda clase que pase a integrar un registro de archivo
 
    /**
     * Constructor por defecto. Los atributos quedan con valores por default
     */
    public Articulo() {
	        codRubro       = 0;
	        codArt          = 0;
	        descripcion     = "  ";
	        precio          = 0;
    }

    /**
     * Constructor. Inicializa cada atributo de acuerdo a los parámetros
     */
    public Articulo(int arti, String nom) {
        codArt = arti;
        descripcion = nom;
        // Completar el resto de los datos del registro
    }
    
    /**
     * Accede al valor del codArt
     *
     * @return el valor del atributo codArt
     */
    public int getCodArt() {
        return codArt;
    }

    /**
     * Accede al valor de la descripción
     *
     * @return el valor del atributo descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia el valor del codArt
     *
     * @param c el nuevo valor del atributo codArt
     */
    public void setCodArt(int c) {
        codArt = c;
    }

    /**
     * Cambia el valor de la descripcion
     *
     * @param nom el nuevo valor del atributo descripcion
     */
    public void setDescripcion(String nom) {
        descripcion = nom;
    }

    /**
     * Calcula el tamaño en bytes del objeto, tal como será grabado. Pedido por
     * Grabable
     *
     * @return el tamaño en bytes del objeto
     */
    @Override
    public int tamRegistro() {
        return TAMAREG; 
    }

    /**
     * Devuelve la cantidad de registros que debe haber en el archivo. Pedido por
     * Grabable
     *
     * @return cantidad de registros
     */
    @Override
    public int tamArchivo() {
        return TAMARCHIVO; 
    }
    
    /**
     * Carga el codigo de articulo
     */
    public void cargarCodigo() {
        System.out.print("Ingrese el codigo: ");
        int codi = Consola.readInt();
        setCodArt(codi);
    }

    /**
     * Carga el codigo de Rubro
     */
    public void cargarRubro() {
        System.out.print("Ingrese el codigo de Rubro: ");
        int codi = Consola.readInt();
        setCodArt(codi);
    }

    /**
     * Carga el Precio de articulo
     */
    public void cargarPrecio() {
        System.out.print("Ingrese el Precio: ");
        int codi = Consola.readInt();
        setCodArt(codi);
    }

    /**
     * Carga la descripcion de articulo
     */
    public void cargarDescripcion() {
        System.out.print("Ingrese la descripción: ");
        String nombre = Consola.readLine();
        setDescripcion(nombre);
    }
    
    /**
     * Carga la informacion de articulo
     */
    @Override
    public void cargarDatos() {
        cargarCodigo();
        cargarDescripcion();
        cargarRubro();
        cargarPrecio();
    }    

    /**
     * Indica cómo grabar un objeto. Pedido por Grabable.
     *
     * @param a el archivo donde será grabado el objeto
     */
    @Override
    public void grabar(RandomAccessFile a) {
        try {
            a.writeInt(codArt);
            Registro.writeString(a, descripcion, 50);
            // el metodo es estatico se convoca con el nombre de la clase
            a.writeInt(codRubro);
            a.writeInt(precio);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Indica cómo leer un objeto. Pedido por Grabable.
     *
     * @param a el archivo donde se hará la lectura
     */
    @Override
    public void leer(RandomAccessFile a) {
        try {
            codArt = a.readInt();
            descripcion = Registro.readString(a, 50).trim();
            codRubro = a.readInt();
            precio = a.readInt();
        } catch (IOException e) {
            System.out.println("Error al leer el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Redefinición del heredado desde Object. Considera que dos Articulos son
     * iguales si sus códigos lo son
     *
     * @param x el objeto contra el cual se compara
     * @return true si los códigos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }

        Articulo a = (Articulo) x;
        return (codArt == a.codArt);
    }

    /**
     * Redefinición del heredado desde Object. La convención es si equals() dice
     * que dos objetos son iguales, entonces hashCode() debería retornar el
     * mismo valor para ambos.
     *
     * @return el hashCode del Articulo. Se eligió el código para ese valor.
     */
    @Override
    public int hashCode() {
        return codArt;
    }

    /**
     * Redefinición del heredado desde Object.
     * Puede redefinirse segun lo que se necesite en el problema
     *
     * @return una cadena representando el contenido del objeto.
     */
    @Override
    public String toString() {
        return "Codigo: " + codArt + "\tDescripcion: " + descripcion;
    }

    /**
     * Muestra un registro por pantalla
     */
    @Override
    public void mostrarRegistro() {
        System.out.println(toString());
        System.out.println("\t");
    }
}