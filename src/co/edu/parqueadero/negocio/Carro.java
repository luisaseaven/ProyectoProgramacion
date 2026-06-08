package co.edu.parqueadero.negocio;

public class Carro extends Vehiculo {
    private static final double VALOR_HORA = 5000; 
    private static final double VALOR_DIA_COMPLETO = 40000; // Tarifa si pasa de 12 horas

    public Carro(String placa) {
        super(placa, "Carro");
    }

    @Override
    public double calcularCostoTotal(int horas) {
        // Regla de negocio: Si supera las 12 horas se cobra tarifa completa del día
        if (horas > 12) {
            return VALOR_DIA_COMPLETO;
        } else {
            return horas * VALOR_HORA;
        }
    }
}
