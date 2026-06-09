package co.edu.parqueadero.negocio;

public class Carro extends Vehiculo {
    public static final double VALOR_HORA = 5000; 
    public static final double VALOR_DIA_COMPLETO = 40000; 

    public Carro(String placa, String color, String marca, String modelo, String nombrePropietario, String identificacionPropietario) {
        super(placa, color, marca, modelo, nombrePropietario, identificacionPropietario, "Carro");
    }

    @Override
    public double calcularCostoTotal(int horas) {
        if (horas > 12) {
            return VALOR_DIA_COMPLETO;
        } else {
            return horas * VALOR_HORA;
        }
    }
}                                  