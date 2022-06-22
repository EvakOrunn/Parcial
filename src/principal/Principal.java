package principal;

import persistencia.*; 
import datos.*; 
import entradaDatos.*; 
import java.util.Scanner;

public class Principal { 
    private static Archivo m2;
    private static Registro reg;
    private static Articulo art;

    /**
     * Muestra el contenido de un archivo (incluidos los registros marcados como
     * borrados) es de utilidad para verificar el contenido del archivo a medida 
     * que vamos avanzando en la resolución de modo de controlar como estan 
     * cargados los registros incluyendo los vacios o los borrados
     */
    public static void mostrarTodo() {

        m2.abrirParaLeerEscribir();
        m2.irPrincipioArchivo();
        while (!m2.eof()) {
            reg = m2.leerRegistro();
            reg.mostrarRegistro();
        }
        m2.cerrarArchivo();
    }

    /**
     * Muestra el contenido de un archivo (sin incluir los registros marcados como
     * borrados) 
     */
    public static void mostrarActivos() {

        m2.abrirParaLectura();
        m2.irPrincipioArchivo();
        while (!m2.eof()) {
            reg = m2.leerRegistro();
            if(reg.getActivo())
            reg.mostrarRegistro(); /* En el caso de requerirse un modo determinado de impresión
            * por ejemplo en columnas, este método deberá organizar el titulo y encabezado del
            * listado y convocar a leerRegistro para luego mostrarlo de acuerdo a la solicitud
            * sin convocar al metodo mostrarRegistro */
        }
        m2.cerrarArchivo();
    }

    /**
     * Carga un registro de Articulo por teclado
     */
    public static Registro leerArticulo() {
        Registro aux = new Registro();
        aux.cargarNroOrden();
        art.cargarDatos();
        aux.setDatos(art);
        aux.setActivo(true);
        return aux; /* Si utilizaramos la instancia reg, nos encontrariamos modificando
         * el espacio de memoria relacionado con la lectura del archivo y por lo tanto
         * cambiaria a medida que avanzaramos sobre el archivo. OJO CON ESTO
         */
    }
    
    public static void cargarArticulos() {
        Scanner entrada = new Scanner(System.in);
        String op;
        char opcion = 's';
        m2.abrirParaLeerEscribir();

        try {
            while (opcion != 'n') {
                reg = leerArticulo();
                m2.altaRegistro(reg);
                System.out.print("Continuar Enter/n: ");
                op = entrada.next();
                op = (!op.equals("n")) ? "s" : op;
                opcion = op.charAt(0);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }

        m2.cerrarArchivo();
    }

    public static void main(String[] args) {
        int op;

        try {
            m2 = new Archivo("Articulos.dat", new Articulo()); // colocar el camino correcto del archivo que usemos
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        
        reg = new Registro();
        art = new Articulo();
        reg.setDatos(art);
        m2.abrirParaLeerEscribir();
        long cuantos = m2.cantidadRegistros();
        m2.cerrarArchivo();
        
        if(cuantos == 0)
            m2.crearArchivoVacio(new Registro(art, 0));
        
        do {
            System.out.println("Opciones ABM de archivos");
            System.out.println("1. Alta de un registro de Articulo");
            System.out.println("2. Cargar el archivo de Articulos");
            System.out.println("3. Baja de un registro de Articulo (lógica)");
            System.out.println("4. Modificacion de un registro de Articulo");
            System.out.println("5. Listado de Articulos Activos");
            System.out.println("6. Listado de todos los Articulos incluyendo borrados");
            System.out.println("7. Depuración de Artículos (bajas físicas)");
            System.out.println("8. Borrar contenido del archivo de Articulos");
            System.out.println("9. Salir");

            System.out.print("Ingrese opcion: ");
            op = Consola.readInt();
            switch (op) {
                case 1:
                    System.out.println("Ingrese los datos del registro de Artículo: ");
                    reg = leerArticulo();
                    m2.cargarUnRegistro(reg);
                    break;

                case 2:
                    System.out.println("Carga de registros de Artículos: ");
                    cargarArticulos();
                    break;

                case 3:
                    System.out.println("Ingrese el artículo a borrar: ");
                    reg.cargarNroOrden();                    
                    m2.bajaRegistro(reg);
                    break;

                case 4:
                    System.out.println("Ingrese el artículo a modificar: ");
                    reg.cargarNroOrden();
                    m2.modificarRegistro(reg); // Evitamos usar reg que ya está vinculado al archivo
                    break;

                case 5:
                    System.out.println("Se muestra el archivo de Articulos (sin incluir 'borrados')...");
                    mostrarActivos();
                    break;

                case 6:
                    System.out.println("Se muestra el archivo de Articulos (incluyendo 'borrados')...");
                    mostrarTodo();
                    break;

                case 7:
                    System.out.println("Se eliminan registros de Articulos marcados...");
                    m2.depurar();
                    System.out.println("\nOperacion terminada...");
                    break;

                case 8:
                    System.out.println("Borrar el contenido del Archivo de Articulos...");
                    m2.crearArchivoVacio(new Registro(art, 0));
                    System.out.println("\nOperacion terminada...");
                    break;

                case 9:
                    ;
            }
        } while (op != 9);
    }
}