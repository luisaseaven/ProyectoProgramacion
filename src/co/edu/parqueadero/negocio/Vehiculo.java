package co.edu.parqueadero.negocio;

public abstract class Vehiculo implements Calculable {
    protected String placa; //pedimos el dato (placa)
    protected String tipo;  //pedimos el dato (tipo)

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }
   //se pide la respectiva placa
    public String getTipo() {
        return tipo;
    }
}