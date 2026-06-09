package co.edu.parqueadero.negocio;

import java.util.ArrayList;

public class ControlParqueadero {
    private ArrayList<Carro> listaVehiculos;
    private static final double PORCENTAJE_IVA = 0.19; 

    public ControlParqueadero() {
        this.listaVehiculos = new ArrayList<>();
    }

    public boolean registrarIngreso(String placa, String color, String marca, String modelo, String propietario, String cedula) {
        for (Carro c : listaVehiculos) {
            if (c.getPlaca().equalsIgnoreCase(placa)) {
                return false; 
            }
        }
        listaVehiculos.add(new Carro(placa, color, marca, modelo, propietario, cedula));
        return true;
    }

    public String procesarSalida(String placa, int horas, boolean yaHabiaVisitado, String horaRecogida) {
        Carro carroEncontrado = null;
        for (Carro c : listaVehiculos) {
            if (c.getPlaca().equalsIgnoreCase(placa)) {
                carroEncontrado = c;
                break;
            }
        }

        if (carroEncontrado == null) {
            return "Error: Vehículo no registrado en el parqueadero.";
        }

        double subtotal = carroEncontrado.calcularCostoTotal(horas);

        if (yaHabiaVisitado) {
            subtotal = subtotal * 0.90; 
        }

        double valorIva = subtotal * PORCENTAJE_IVA;
        double totalPagar = subtotal + valorIva;

        listaVehiculos.remove(carroEncontrado);

        String reporte = "=== RECIBO DE PAGO ===\n" +
               "Placa/Matrícula: " + carroEncontrado.getPlaca() + "\n" +
               "Marca/Modelo: " + carroEncontrado.getMarca() + " (" + carroEncontrado.getModelo() + ")\n" +
               "Color: " + carroEncontrado.getColor() + "\n" +
               "Propietario: " + carroEncontrado.getNombrePropietario() + " (CC: " + carroEncontrado.getIdentificacionPropietario() + ")\n" +
               "Tiempo de uso: " + horas + " horas.\n";
               
        if (horas > 12 && horaRecogida != null) {
            reporte += "Estado: Estadía Larga (>12h) - Recogida pactada a las: " + horaRecogida + "\n";
        }

        reporte += "Descuento aplicado (10%): " + (yaHabiaVisitado ? "SÍ" : "NO") + "\n" +
               "Subtotal: $" + subtotal + "\n" +
               "IVA (19%): $" + valorIva + "\n" +
               "TOTAL A PAGAR: $" + totalPagar + "\n" +
               "=====================";
               
        return reporte;
    }

    public ArrayList<Carro> getListaVehiculos() {
        return listaVehiculos;
    }
}