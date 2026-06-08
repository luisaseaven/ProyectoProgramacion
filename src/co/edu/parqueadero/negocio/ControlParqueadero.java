package co.edu.parqueadero.negocio;

import java.util.ArrayList;

public class ControlParqueadero {
    private ArrayList<Carro> listaVehiculos;
    private static final double PORCENTAJE_IVA = 0.19; // IVA del 19%

    public ControlParqueadero() {
        this.listaVehiculos = new ArrayList<>();
    }

    public boolean registrarIngreso(String placa) {
        for (Carro c : listaVehiculos) {
            if (c.getPlaca().equalsIgnoreCase(placa)) {
                return false; // El vehículo ya está adentro
            }
        }
        listaVehiculos.add(new Carro(placa));
        return true;
    }

    public String procesarSalida(String placa, int horas, boolean yaHabiaVisitado) {
        Carro carroEncontrado = null;
        for (Carro c : listaVehiculos) {
            if (c.getPlaca().equalsIgnoreCase(placa)) {
                carroEncontrado = c;
                break;
            }
        }

        if (carroEncontrado == null) {
            return "Vehículo no registrado en el parqueadero.";
        }

        double subtotal = carroEncontrado.calcularCostoTotal(horas);

        // Descuento del 10% por visita previa
        if (yaHabiaVisitado) {
            subtotal = subtotal * 0.90; 
        }

        double valorIva = subtotal * PORCENTAJE_IVA;
        double totalPagar = subtotal + valorIva;

        listaVehiculos.remove(carroEncontrado);

        return "=== RECIBO DE PAGO ===\n" +
               "Placa: " + placa + "\n" +
               "Horas en parqueadero: " + horas + "\n" +
               "Descuento aplicado (10%): " + (yaHabiaVisitado ? "SÍ" : "NO") + "\n" +
               "Subtotal: $" + subtotal + "\n" +
               "IVA (19%): $" + valorIva + "\n" +
               "TOTAL A PAGAR: $" + totalPagar + "\n" +
               "=====================";
    }

    public ArrayList<Carro> getListaVehiculos() {
        return listaVehiculos;
    }
}