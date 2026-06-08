package co.edu.parqueadero.negocio;

public abstract class Vehiculo implements Calculable {
    protected String placa;
    protected String tipo;

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }
}