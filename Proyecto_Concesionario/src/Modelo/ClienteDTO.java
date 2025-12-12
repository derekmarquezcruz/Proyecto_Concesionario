package Modelo;

public class ClienteDTO{
    private String dni;
    private String nombre;
    private String telefono;


    //getters
    public String getDni(){
        return dni;
    }


    //constructor
    public ClienteDTO(String dni, String nombre, String telefono){
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String toString(){
        return nombre + " (DNI: " + dni + ", Tel: " + telefono + ")";
    }
}
