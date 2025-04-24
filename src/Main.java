import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> stockActual = new HashMap<>();
        HashMap<String, Integer> stockInicial = new HashMap<>();
        String nombreZapato;
        int cantidad, cantidadAgregar, cantidadVender;
        int opcion;

        do {
            System.out.println("-------------- OPCIONES --------------");
            System.out.println("1. Registrar nuevo zapato");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Procesar venta");
            System.out.println("4. Añadir más stock");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Salir del sistema");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            System.out.println("----------------------------------");

            switch (opcion) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Nombre del zapato: ");
                    nombreZapato = scanner.nextLine().toLowerCase();
                    System.out.print("Cantidad inicial en stock: ");
                    cantidad = scanner.nextInt();
                    stockActual.put(nombreZapato, cantidad);
                    stockInicial.put(nombreZapato, cantidad);
                    System.out.println("Zapato " + nombreZapato + " agregado con " + cantidad + " unidades disponibles.");
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.print("Ingrese el nombre del zapato para verificar stock: ");
                    nombreZapato = scanner.nextLine().toLowerCase();
                    if (stockActual.containsKey(nombreZapato)) {
                        System.out.println("Stock actual de " + nombreZapato + ": " + stockActual.get(nombreZapato));
                    } else {
                        System.out.println("Ese zapato no está registrado en el inventario.");
                    }
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.print("Indique el zapato a vender: ");
                    nombreZapato = scanner.nextLine().toLowerCase();
                    System.out.print("Cantidad a vender: ");
                    cantidadVender = scanner.nextInt();
                    if (stockActual.getOrDefault(nombreZapato, 0) >= cantidadVender) {
                        stockActual.put(nombreZapato, stockActual.get(nombreZapato) - cantidadVender);
                        System.out.println("Venta completada. Nuevo stock de " + nombreZapato + ": " + stockActual.get(nombreZapato));
                    } else {
                        System.out.println("Venta no posible. Stock insuficiente o zapato no encontrado.");
                    }
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.print("Zapato al que se añadirá stock: ");
                    nombreZapato = scanner.nextLine().toLowerCase();
                    System.out.print("Cantidad a sumar: ");
                    cantidadAgregar = scanner.nextInt();
                    if (stockActual.containsKey(nombreZapato)) {
                        stockActual.put(nombreZapato, stockActual.get(nombreZapato) + cantidadAgregar);
                        System.out.println("Stock actualizado. Nuevo total de " + nombreZapato + ": " + stockActual.get(nombreZapato));
                    } else {
                        System.out.println("No se puede añadir stock a un zapato no registrado.");
                    }
                    break;

                case 5:
                    System.out.println("-------------- Inventario General --------------");
                    if (stockActual.isEmpty()) {
                        System.out.println("No hay zapatos en el inventario.");
                    } else {
                        stockActual.forEach((zapato, cantidadDisponible) ->
                                System.out.println(zapato + " - En stock: " + cantidadDisponible + " (Stock inicial: " + stockInicial.get(zapato) + ")"));
                    }
                    break;

                case 6:
                    System.out.println("Cerrando sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo nuevamente.");
            }

            for (String zapato : stockActual.keySet()) {
                if (stockActual.get(zapato) <= 0) {
                    stockActual.put(zapato, stockInicial.get(zapato) * 2);
                    System.out.println("Stock de " + zapato + " agotado. Se ha repuesto a " + stockActual.get(zapato) + " unidades.");
                }
            }
        } while (opcion != 6);

        scanner.close();
    }
}