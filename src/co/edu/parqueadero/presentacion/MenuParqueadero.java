package co.edu.parqueadero.presentacion;

import co.edu.parqueadero.negocio.ControlParqueadero;
import co.edu.parqueadero.negocio.Carro;
import java.util.Scanner;

public class MenuParqueadero {
    public static void main(String[] args) {
        ControlParqueadero parqueadero = new ControlParqueadero();
        Scanner teclado = new Scanner(System.in);
        String entradaMenu = "";
        int opcion = 0;

        do {
            System.out.println("\n=============================================");
            System.out.println("          SISTEMA DE PARQUEADERO             ");
            System.out.println("  Tarifa Normal: $" + Carro.VALOR_HORA + " por hora");
            System.out.println("  Estadía Larga (>12h): Tarifa plana $" + Carro.VALOR_DIA_COMPLETO);
            System.out.println("=============================================");
            System.out.println("1. Registrar Ingreso de Vehículo (Datos Completos)");
            System.out.println("2. Registrar Salida, Tiempo de Uso y Facturar");
            System.out.println("3. Ver Vehículos en el Parqueadero (Stock)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            entradaMenu = teclado.nextLine().trim();
            
            if (entradaMenu.isEmpty()) {
                System.out.println("Error: No ingresó ninguna opción. Digite un número del 1 al 4.");
                continue;
            }
            
            try {
                opcion = Integer.parseInt(entradaMenu);
            } catch (NumberFormatException e) {
                System.out.println("Error: La opción debe ser solo numérica.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE INGRESO ---");
                    String placa = pedirDato(teclado, "Ingrese la matrícula/placa del carro: ");
                    String color = pedirDato(teclado, "Ingrese el color del carro: ");
                    String marca = pedirDato(teclado, "Ingrese la marca del carro: ");
                    String modelo = pedirDato(teclado, "Ingrese el modelo del carro: ");
                    String propietario = pedirDato(teclado, "Ingrese el nombre del propietario: ");
                    String cedula = pedirDato(teclado, "Ingrese el número de identificación: ");
                    
                    if (parqueadero.registrarIngreso(placa, color, marca, modelo, propietario, cedula)) {
                        System.out.println("\n¡Vehículo registrado con éxito en el sistema!");
                    } else {
                        System.out.println("\nError: El vehículo con placa (" + placa + ") ya está registrado.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRO DE SALIDA Y FACTURACIÓN ---");
                    String placaSalida = pedirDato(teclado, "Ingrese la matrícula del carro a salir: ");

                    int horas = 0;
                    while (true) {
                        System.out.print("Especifique el tiempo de uso (¿Cuántas horas estuvo?): ");
                        String entradaHoras = teclado.nextLine().trim();
                        if (entradaHoras.isEmpty()) {
                            System.out.println("Error: El campo de horas no puede estar vacío.");
                            continue;
                        }
                        try {
                            horas = Integer.parseInt(entradaHoras);
                            if (horas <= 0) {
                                System.out.println("Error: Las horas deben ser un número mayor a cero.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Formato inválido. Solo se admiten números enteros.");
                        }
                    }

                    String horaRecogida = null;
                    if (horas > 12) {
                        System.out.println("[Alerta: Estadía Larga Detectada]");
                        horaRecogida = pedirDato(teclado, "Defina la hora exacta en la que será recogido (Ej: 05:30 PM): ");
                    }

                    boolean visita = false;
                    while (true) {
                        System.out.print("¿Este vehículo ya estuvo antes en el parqueadero? (si/no): ");
                        String rtaVisita = teclado.nextLine().trim().toLowerCase();
                        
                        if (rtaVisita.equals("sí")) rtaVisita = "si";

                        if (rtaVisita.equals("si")) {
                            visita = true;
                            break;
                        } else if (rtaVisita.equals("no")) {
                            visita = false;
                            break;
                        } else {
                            System.out.println("Error: Ingrese una respuesta válida ('si' o 'no').");
                        }
                    }

                    String recibo = parqueadero.procesarSalida(placaSalida, horas, visita, horaRecogida);
                    System.out.println("\n" + recibo);
                    break;

                case 3:
                    System.out.println("\n--- ESTADO DEL INVENTARIO ---");
                    System.out.println("Vehículos actualmente en stock: " + parqueadero.getListaVehiculos().size());
                    break;

                case 4:
                    System.out.println("Cerrando el sistema del parqueadero... ¡Que tengan un excelente día!");
                    break;

                default:
                    System.out.println("Error: Opción fuera de rango (1 al 4).");
            }
            
        } while (opcion != 4);

        teclado.close();
    }

    private static String pedirDato(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String dato = teclado.nextLine().trim();
            if (dato.isEmpty()) {
                System.out.println("Error: Este campo es obligatorio y no puede quedarse vacío.");
                continue;
            }
            return dato;
        }
    }
} 