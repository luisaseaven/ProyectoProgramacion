package co.edu.parqueadero.presentacion;

import co.edu.parqueadero.negocio.ControlParqueadero;
import java.util.Scanner;

public class MenuParqueadero {
    public static void main(String[] args) {
        ControlParqueadero parqueadero = new ControlParqueadero();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- CONTROL DE PARQUEADERO ---");
            System.out.println("1. Registrar Ingreso de Vehículo");
            System.out.println("2. Registrar Salida y Facturar (IVA)");
            System.out.println("3. Ver Vehículos en el Parqueadero (Stock)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del carro: ");
                    String placaIngreso = teclado.nextLine();
                    if (parqueadero.registrarIngreso(placaIngreso)) {
                        System.out.println("¡Carro registrado con éxito!");
                    } else {
                        System.out.println("Error: El carro ya está dentro del parqueadero.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la placa del carro a salir: ");
                    String placaSalida = teclado.nextLine();
                    System.out.print("¿Cuántas horas estuvo?: ");
                    int horas = teclado.nextInt();
                    System.out.print("¿El cliente ya había visitado antes? (true/false): ");
                    boolean visita = teclado.nextBoolean();

                    String recibo = parqueadero.procesarSalida(placaSalida, horas, visita);
                    System.out.println("\n" + recibo);
                    break;

                case 3:
                    System.out.println("Vehículos actualmente en stock: " + parqueadero.getListaVehiculos().size());
                    break;

                case 4:
                    System.out.println("Cerrando el sistema del parqueadero...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        teclado.close();
    }
}