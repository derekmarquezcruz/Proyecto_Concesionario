package Modelo;

public class CocheDTO{
    private String marca;
    private String modelo;
    private String matricula;
    private double precio;
    private int anio;
    private int kilometros;

    //getters

    public String getMarca(){
        return marca;
    }

    public String getMatricula(){
        return matricula;
    }

    public int getKilometros() {
        return kilometros;
    }

    public double getPrecio(){
        return precio;
    }

    public int getAnio(){
        return anio;
    }

    public String getModelo() {
        return modelo;
    }

    //constructor
    public CocheDTO(String marca, String modelo, String matricula, double precio, int anio, int kilometros){
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precio = precio;
        this.anio = anio;
        this.kilometros = kilometros;
    }

    public String toString(){
        return  marca + " " + modelo + " -- (" + matricula + ") -- " + "Precio: " + precio + "€" + " -- " + "Año:" + anio + " -- " + "Kilometros:" + kilometros + "km";
    }



}