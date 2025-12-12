package Vista;

import java.util.List;
import java.util.Scanner;
import Modelo.CocheDTO;
import Modelo.ClienteDTO;
import Modelo.VentaDTO;

public class ConcesionarioVista{

    Scanner sc = new Scanner(System.in);

    /**
     * Muestra el menu principal por pantalla
     * @return la opción elegida por el usuario como entero.
     */
    public int menu(){
        System.out.println("                           ");//añado este sout vacio porque si no, al mostrar el "mensajeErr" lo mete dentro de la palabra "Concesionario" en la consola.
        System.out.println("╔-------------------------╗");
        System.out.println("║      CONCESIONARIO      ║");
        System.out.println("╠-------------------------╣");
        System.out.println("║ 1| -Añadir coche        ║");
        System.out.println("║ 2| -Mostrar coches      ║");
        System.out.println("║ 3| -Buscar coches       ║");
        System.out.println("║ 4| -Registrar cliente   ║");
        System.out.println("║ 5| -Registrar venta     ║");
        System.out.println("║ 6| -Listar ventas       ║");
        System.out.println("║ 0| -Salir               ║");
        System.out.println("╚-------------------------╝");
        return pedirInt("- Elije una de estas opciones:");
    }

    /**
       Pide los datos necesarios para crear un coche.
     @return un objeto CocheDTO con los datos introducidos.
     */
    public CocheDTO leerCoche(){
        System.out.println("Ingrese los datos del coche");

        System.out.print("Marca del coche:");
        String marca = sc.nextLine();

        System.out.print("Modelo del coche:");
        String modelo = sc.nextLine();

        System.out.print("Matrícula del coche:");
        String matricula = sc.nextLine();

        System.out.print("Precio del coche:");
        double precio = sc.nextDouble();

        System.out.print("Año del coche:");
        int anio = sc.nextInt();

        System.out.print("Kilómetros del coche:");
        int kms = sc.nextInt();
        sc.nextLine();

        return new CocheDTO(marca, modelo, matricula, precio, anio, kms);
    }


    /**
      Pide los datos necesarios para registrar un cliente.
     @return un objeto llamado ClienteDTO con los datos del cliente.
     */
    public ClienteDTO datosCliente(){
        System.out.println("Ingrese los datos del cliente");

        System.out.print("DNI del cliente:");
        String dni = sc.nextLine();

        System.out.print("Nombre del cliente:");
        String nombre = sc.nextLine();

        System.out.print("Teléfono del cliente:");
        String telefono = sc.nextLine();

        return new ClienteDTO(dni, nombre, telefono);
    }

    /**
      Muestra las opciones de búsqueda de coches.
      @return la opción elegida por el usuario.
     */
    public int pedirOpcionBusqueda(){
        System.out.println("Buscar coches por:");

        System.out.println("1. Marca:");

        System.out.println("2. Precio:");

        System.out.println("3. Año:");

        System.out.println("4. Salir");

        return pedirInt("Elije una de estas opciones:");
    }

    /**
     Pide una marca al usuario.
     @return la marca introducida como String.
     */
    public String pedirMarca(){
        System.out.print("Introduce la marca:");
        return sc.nextLine();
    }

    /**
      Pide un rango de precios, un minimo y un maximo.
      @return array de dos enteros donde [0] será el precio mínimo y [1] será precio máximo
     */
    public int[] pedirRangoPrecio(){ //se usa un array porque queremos devolver los dos valores juntos, el mínimo y el máximo del rango de precio.
        System.out.println("Dame un rango de precios");

        System.out.print("Precio mínimo de busqueda:");
        int min = sc.nextInt();

        System.out.print("Precio máximo de busqueda:");
        int max = sc.nextInt();
        sc.nextLine();

        // Creamos un array para guardar el rango
        int[] rango = new int[2];

        rango[0] = min;
        rango[1] = max;

        return rango;
    }

    /** Pide un año al usuario
      @return el año introducido como entero */

    public int pedirAnio(){
        System.out.print("Introduce el año:");
        int anio = sc.nextInt();
        sc.nextLine();
        return anio;
    }

    /**
      Muestra una lista de coches por pantalla.
     */
    public void mostrarCoches(List<CocheDTO> lista){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           COCHES DISPONIBLES           ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (lista.isEmpty()){
            System.out.println("No hay coches disponibles");
        }

        for (CocheDTO coche : lista){
            System.out.println(coche);
        }
    }


    //Muestra por pantalla una lista de ventas que se registraron

    public void mostrarVentas(List<VentaDTO> lista){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                 VENTAS                 ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (lista.isEmpty()){
            System.out.println("No hay ventas");
        }

        for (VentaDTO venta : lista){
            System.out.println(venta);
        }
    }

    /**
      Muestra un mensaje simple por pantalla.
     */
    public void mensaje(String texto){
        System.out.println(texto);
    }

    /**
      Muestra un mensaje de error por pantalla.
     */
    public void mensajeErr(String texto){
        System.err.println(texto); // muestra el mensaje de error en rojo
    }

    /**
      Pide un texto al usuario.
     @return lo que el usuario escribió como String.
     */
    public String pedir(String texto){
        System.out.print(texto);
        return sc.nextLine();
    }

    /** Pide un número entero al usuario.
     @return el número entero introducido.
     */

    public int pedirInt(String texto){
        System.out.print(texto);
        return Integer.parseInt(sc.nextLine());
    }

    /**
     Pide un número decimal al usuario.
      @return el número decimal introducido como double.
     */
    public double pedirDouble(String texto){
        System.out.print(texto);
        return Double.parseDouble(sc.nextLine());
    }
}

