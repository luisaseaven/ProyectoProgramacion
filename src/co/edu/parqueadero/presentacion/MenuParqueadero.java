package co.edu.parqueadero.presentacion;

import co.edu.parqueadero.negocio.ControlParqueadero;
import java.util.Scanner;
import java.util.InputMismatchException;

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
            
            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la placa del carro: ");
                        String placaIngreso = teclado.nextLine().trim();
                        
                        // Validación de dato vacío o nulo
                        if (placaIngreso.isEmpty()) {
                            System.out.println("Error: La placa no puede estar vacía.");
                            break;
                        }
                        
                        if (parqueadero.registrarIngreso(placaIngreso)) {
                            System.out.println("¡Carro registrado con éxito!");
                        } else {
                            System.out.println("Error: El carro con placa (" + placaIngreso + ") ya está dentro del parqueadero.");
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese la placa del carro a salir: ");
                        String placaSalida = teclado.nextLine().trim();
                        
                        if (placaSalida.isEmpty()) {
                            System.out.println("Error: La placa no puede estar vacía.");
                            break;
                        }

                        // Validación para el ingreso de horas
                        int horas = 0;
                        System.out.print("¿Cuántas horas estuvo?: ");
                        try {
                            horas = teclado.nextInt();
                            teclado.nextLine(); // Limpiar buffer
                            
                            if (horas <= 0) {
                                System.out.println("Error: El número de horas debe ser mayor a cero.");
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero válido para las horas.");
                            teclado.nextLine(); // Limpiar buffer por el error
                            break;
                        }

                        // Validación para la respuesta de visita previa
                        boolean visita = false;
                        System.out.print("¿El cliente ya había visitado antes? (si/no): ");
                        String rtaVisita = teclado.nextLine().trim().toLowerCase();
                        
                        if (rtaVisita.equals("si") || rtaVisita.equals("sí")) {
                            visita = true;
                        } else if (rtaVisita.equals("no")) {
                            visita = false;
                        } else {
                            System.out.println("Error: Solo se acepta como respuesta 'si' o 'no'.");
                            break;
                        }

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
                        System.out.println("Error: Opción no válida. Elija un número entre 1 y 4.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Error crítico: Por favor, ingrese un número entero válido para el menú.");
                teclado.nextLine(); // Limpiar el buffer para evitar un bucle infinito
                opcion = 0; // Reiniciar opción para que continúe el ciclo
            }
            
        } while (opcion != 4);

        teclado.close();
    }
}