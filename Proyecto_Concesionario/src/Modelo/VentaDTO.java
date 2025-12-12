package Modelo;
import java.util.Date;

public class VentaDTO{
    private static int contador = 1;
    private int idVenta;
    private ClienteDTO cliente;
    private CocheDTO coche;
    private Date fecha;
    private double precioVenta;

    //constructor
    public VentaDTO(ClienteDTO cliente, CocheDTO coche, double precioVenta){
        this.idVenta = contador++;
        this.cliente = cliente;
        this.coche = coche;
        this.fecha = new Date();
        this.precioVenta = precioVenta;
    }

    public String toString(){
        return "Venta" + " " + idVenta + " -- Matrícula: " + coche.getMatricula() + " -- Cliente: " + cliente.toString() + " -- Precio: " +
                precioVenta + "€" + " -- Fecha: " + fecha;
    }
}


