package co.edu.parqueadero.negocio;

public abstract class Vehiculo implements Calculable {
    protected String placa;
    protected String color;
    protected String marca;
    protected String modelo;
    protected String nombrePropietario;
    protected String identificacionPropietario;
    protected String tipo;

    public Vehiculo(String placa, String color, String marca, String modelo, String nombrePropietario, String identificacionPropietario, String tipo) {
        this.placa = placa;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.nombrePropietario = nombrePropietario;
        this.identificacionPropietario = identificacionPropietario;
        this.tipo = tipo;
    }

    public String getPlaca() { return placa; }
    public String getColor() { return color; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getNombrePropietario() { return nombrePropietario; }
    public String getIdentificacionPropietario() { return identificacionPropietario; }
    public String getTipo() { return tipo; }
}