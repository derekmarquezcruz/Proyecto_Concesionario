import Vista.ConcesionarioVista;
import Controlador.ConcesionarioControlador;

public class Main{
    public static void main(String[] args){
        ConcesionarioVista vista = new ConcesionarioVista(); // crea la vista
        ConcesionarioControlador controlador = new ConcesionarioControlador(vista); // se crea el controlador pasando la vista
        controlador.iniciar(); // se inicia el controlador
    }
}