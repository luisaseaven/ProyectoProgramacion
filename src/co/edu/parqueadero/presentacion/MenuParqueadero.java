package co.edu.parqueadero.presentacion;

import co.edu.parqueadero.negocio.ControlParqueadero;
import java.util.Scanner;

public class MenuParqueadero {
    public static void main(String[] args) {
        ControlParqueadero parqueadero = new ControlParqueadero();
        Scanner teclado = new Scanner(System.in);
        String entradaMenu = "";
        int opcion = 0;

        do {
            System.out.println("\n--- CONTROL DE PARQUEADERO ---");
            System.out.println("1. Registrar Ingreso de Vehículo");
            System.out.println("2. Registrar Salida y Facturar (IVA)");
            System.out.println("3. Ver Vehículos en el Parqueadero (Stock)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            entradaMenu = teclado.nextLine().trim();
            
            // EXCEPCIÓN: Si le dan Enter vacío en el menú
            if (entradaMenu.isEmpty()) {
                System.out.println("Error: No ingresó ninguna opción. Digite un número del 1 al 4.");
                continue;
            }
            
            // EXCEPCIÓN: Validar si la opción del menú no es un número
            try {
                opcion = Integer.parseInt(entradaMenu);
            } catch (NumberFormatException e) {
                System.out.println("Error: La opción debe ser solo numérica (números enteros).");
                opcion = 0; // Reiniciar para que repita el ciclo
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del carro: ");
                    String placaIngreso = teclado.nextLine().trim();
                    
                    if (placaIngreso.isEmpty()) {
                        System.out.println("Error: La placa no puede estar vacía.");
                        break;
                    }
                    
                    if (parqueadero.registrarIngreso(placaIngreso)) {
                        System.out.println("¡Carro registrado con éxito!");
                    } else {
                        System.out.println("Error: El carro con placa (" + placaIngreso + ") ya está dentro.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la placa del carro a salir: ");
                    String placaSalida = teclado.nextLine().trim();
                    
                    if (placaSalida.isEmpty()) {
                        System.out.println("Error: La placa no puede estar vacía.");
                        break;
                    }

                    // EXCEPCIÓN ULTRA BLINDADA PARA HORAS
                    int horas = 0;
                    while (true) {
                        System.out.print("¿Cuántas horas estuvo?: ");
                        String entradaHoras = teclado.nextLine().trim();
                        
                        if (entradaHoras.isEmpty()) {
                            System.out.println("Error: No puede dejar el campo de horas vacío.");
                            continue; // Pide las horas de nuevo
                        }
                        
                        try {
                            horas = Integer.parseInt(entradaHoras);
                            if (horas <= 0) {
                                System.out.println("Error: El número de horas debe ser mayor a cero.");
                                continue; // Pide las horas de nuevo
                            }
                            break; // Si el número es válido, rompe el bucle interno y continúa
                        } catch (NumberFormatException e) {
                            System.out.println("Error: El dato ingresado no es válido. Solo se permiten números enteros.");
                        }
                    }

                    // EXCEPCIÓN TOLERANTE A MAYÚSCULAS PARA EL SÍ / NO
                    boolean visita = false;
                    while (true) {
                        System.out.print("¿El cliente ya había visitado antes? (si/no): ");
                        String rtaVisita = teclado.nextLine().trim().toLowerCase(); // Convierte todo a minúsculas
                        
                        // Quitar la tilde visualmente si la ponen para que no falle (siguiente nivel de blindaje)
                        if (rtaVisita.equals("sí")) {
                            rtaVisita = "si";
                        }

                        if (rtaVisita.equals("si")) {
                            visita = true;
                            break;
                        } else if (rtaVisita.equals("no")) {
                            visita = false;
                            break;
                        } else {
                            System.out.println("Error: Respuesta inválida. Escriba claramente 'si' o 'no' (No importa si es mayúscula o minúscula).");
                        }
                    }

                    String recibo = parqueadero.procesarSalida(placaSalida, horas, visita);
                    System.out.println("\n" + recibo);
                    break;

                case 3:
                    System.out.println("Vehículos actualmente en stock: " + parqueadero.getListaVehiculos().size());
                    break;

                case 4:
                    System.out.println("Cerrando el sistema del parqueadero... ¡Ten un lindo día!");
                    break;

                default:
                    System.out.println("Error: Opción fuera de rango. Seleccione un número entre 1 y 4.");
            }
            
        } while (opcion != 4);

        teclado.close();
    }
}