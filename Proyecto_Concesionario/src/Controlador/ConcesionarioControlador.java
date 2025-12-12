package Controlador;

import java.util.ArrayList;
import java.util.List;
import Modelo.CocheDTO;
import Modelo.ClienteDTO;
import Modelo.VentaDTO;
import Vista.ConcesionarioVista;

public class ConcesionarioControlador{

    // Lista de coches disponibles en el concesionario
    private List<CocheDTO> coches = new ArrayList<>();

    // Lista de clientes que están registrados en el sistema
    private List<ClienteDTO> clientes = new ArrayList<>();

    // Lista de ventas que se han realizado
    private List<VentaDTO> ventas = new ArrayList<>();

    // Este es el objeto llamado vista que se encarga de interactuar con el usuario
    private ConcesionarioVista vista;

    /**
     Constructor del controlador.
     Inicializa la vista y carga los datos iniciales del sistema.
     */
    public ConcesionarioControlador(ConcesionarioVista vista){
        this.vista = vista;
        cargarDatosIniciales(); // cargamos los datos iniciales
    }

    /**
     Carga una serie de coches y clientes de ejemplo al iniciar el programa.
     */
    public void cargarDatosIniciales(){
        coches.add(new CocheDTO("Toyota", "Yaris", "1234AAA", 3600, 2004, 230000));
        coches.add(new CocheDTO("Toyota", "Corolla", "1234BBB", 8200, 2016, 98000));
        coches.add(new CocheDTO("BMW", "E30", "1234CCC", 6700, 2008, 15000));
        coches.add(new CocheDTO("BMW", "M1", "1234DDD", 7000, 2003, 110000));
        coches.add(new CocheDTO("Corvette", "C2", "1234EEE", 120000, 1980, 180000));

        clientes.add(new ClienteDTO("98765432A", "Pablo Crespo", "651123457"));
        clientes.add(new ClienteDTO("68731367Y", "Ana Gómez", "907367235"));
    }

    /**
     Inicia el bucle principal del menú.
     Depende de la opción que escoja el usuario, llama a los métodos correspondientes.
     */
    public void iniciar(){

        while (true){

            int opcion = vista.menu();

            if (opcion == 1){
                anadirCoche();
            }

            if (opcion == 2){
                mostrarCoches();
            }

            if (opcion == 3){
                buscarCoches();
            }

            if (opcion == 4){
               registrarCliente();
            }

            if (opcion == 5){
                registrarVenta();
            }

            if (opcion == 6){
                listarVentas();
            }

            if (opcion == 0){
                vista.mensaje("Saliste");
                return;
            }
            if (opcion < 0 || opcion > 6){
                vista.mensaje("Opción no válida");
            }
        }
    }

    /**
      Añade un coche a la lista después de pedir los datos a la vista.
     */
    public void anadirCoche(){
        vista.mostrarCoches(coches);// muestro los coches para que no se equivoque al poner la matricula
        CocheDTO nuevo = vista.leerCoche(); // pedimos los datos del coche

        // Se comprueba si la matrícula ya existe en la lista de coches
        for (CocheDTO coche : coches){
            if (coche.getMatricula().equalsIgnoreCase(nuevo.getMatricula())){
                // Si se encuentra una coincidencia, mostramos mensaje de error y no añadimos
                vista.mensajeErr("Ya existe un coche con esa matrícula");
                return;
            }
        }

        // Si no hay duplicado, se añade el coche
        coches.add(nuevo);
        vista.mensaje("Coche añadido.");
    }

    /**
      Muestra la lista de coches disponibles.
      Si no hay coches, muestra un mensaje.
     */

    private void mostrarCoches(){
        if (coches.isEmpty()){ //si no hay coches

            vista.mensajeErr("No hay coches disponibles");
            return; //saldrá de la funcion
        }
        vista.mostrarCoches(coches);
    }

    /**
     Realiza búsquedas de coches según el criterio elegido:
     marca, precio o año y muestra los resultados encontrados.
     */
    public void buscarCoches(){

        int opcion = vista.pedirOpcionBusqueda(); //pide el criterio de la busqueda

        List<CocheDTO> resultado = new ArrayList<>(); //guardamos en la lista los resultados

        // Buscar por marca
        if (opcion == 1){
            String marca = vista.pedirMarca();

            for (CocheDTO CocheDTO : coches){
                if (CocheDTO.getMarca().equalsIgnoreCase(marca)){
                    resultado.add(CocheDTO);
                }
            }
        }

        // Buscar por rango de precio
        if (opcion == 2){
            int[] rango = vista.pedirRangoPrecio(); // pide precio minimo y el precio maximo y lo guarda.

            for (CocheDTO CocheDTO : coches){
                if (CocheDTO.getPrecio() >= rango[0] && CocheDTO.getPrecio() <= rango[1]){
                    resultado.add(CocheDTO); //agrega las coincidencias a la lista
                }
            }
        }

        // Buscar por año
        if (opcion == 3){
            int anio = vista.pedirAnio();

            for (CocheDTO CocheDTO : coches){
                if (CocheDTO.getAnio() == anio){
                    resultado.add(CocheDTO); //agrega las coincidencias a la lista
                }
            }
        }

        // Mostrar los resultados que se encontraron
        vista.mostrarCoches(resultado);
    }

    /**
      Registra un nuevo cliente después de pedir los datos y comprueba si el DNI ya existe para evitar duplicados.
     */
    public void registrarCliente(){
        ClienteDTO nuevo = vista.datosCliente();

        // Comprobamos si ya existe un cliente con el mismo DNI
        for (ClienteDTO ClienteDTO : clientes){
            if (ClienteDTO.getDni().equalsIgnoreCase(nuevo.getDni())){ // Comparamos el DNI del cliente actual con el DNI del nuevo cliente que queremos registrar.
                vista.mensajeErr("El DNI ya esta repetido en el sistema");
                return; //sale de la funcion si ya existe
            }

        }
        // Si no hay duplicado, se añade el cliente
        clientes.add(nuevo); // agrega el cliente a la lista
        vista.mensaje("Cliente añadido.");
    }

    /**
     Se encarga de comprobar los coches disponibles y a partir de ahi pedir los datos del cliente,
     los datos del coche y finalmente al realizar la venta elimina el coche.
     */
    public void registrarVenta(){

        // Comprobamos si hay coches disponibles
        if (coches.isEmpty()){
            vista.mensajeErr("No hay coches disponibles.");
            return;
        }

        // Pedimos el DNI del cliente
        String dni = vista.pedir("DNI del cliente:");
        ClienteDTO cliente = null;

        // Buscamos si el cliente existe
        for (ClienteDTO ClienteDTO : clientes){

            if (ClienteDTO.getDni().equalsIgnoreCase(dni)){
                cliente = ClienteDTO; //asigna el cliente encontrado
            }
        }

        if (cliente == null){ // Y si no lo encontró, muestra el mensaje
            vista.mensajeErr("Cliente no encontrado.");
            return;
        }

        // Mostramos los coches disponibles
        vista.mostrarCoches(coches);

        // Pedimos la matrícula del coche al comprar
        String matricula = vista.pedir("Matrícula del coche:");
        CocheDTO coche = null;

        // Buscamos el coche en la lista
        for (CocheDTO CocheDTO : coches){

            if (CocheDTO.getMatricula().equalsIgnoreCase(matricula)){
                coche = CocheDTO; // Asigna el coche encontrado
            }
        }

        if (coche == null){ // Y si no hay coincidencias y no lo encontro, muestra el mensaje
            vista.mensajeErr("Coche no encontrado.");
            return;
        }

        // Pedimos el precio de la venta
        double precio = vista.pedirDouble("Precio de venta: ");

        // Registramos la venta y eliminamos el coche vendido
        ventas.add(new VentaDTO(cliente, coche, precio));
        coches.remove(coche);

        vista.mensaje("Venta registrada.");
    }

    /**
      Muestra la lista de ventas que se han registrado.
     */
    public void listarVentas(){
        vista.mostrarVentas(ventas);
    }
}
