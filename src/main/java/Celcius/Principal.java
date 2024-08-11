package Celcius;

import java.util.Scanner;



public class Principal {

    private static Operaciones operaciones;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        operaciones = new Operaciones();

        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione la operación a realizar:");
            System.out.println("1. Añadir nueva venta");
            System.out.println("2. Ver registro de un cliente");
            System.out.println("3. Modificar información de un cliente");
            System.out.println("4. Eliminar registro de venta");
            System.out.println("5. Salir");

            int opcion = readOption(scanner);

            switch (opcion) {
                case 1: // AÑADIR
                    System.out.println("Ingrese los detalles de la venta:");

                    // Solicitar y leer los detalles de la venta
                    System.out.println("ID del álbum:");
                    int idAlbum = readInt(scanner);

                    System.out.println("ID del cliente:");
                    int idCliente = readInt(scanner);

                    // Utilizar los IDs para obtener el álbum y el cliente de la base de datos
                    Album album = operaciones.obtenerAlbumPorId(idAlbum);
                    Cliente cliente = operaciones.obtenerClientePorId(idCliente);

                    // Verificar si el álbum y el cliente existen en la base de datos
                    if (album != null && cliente != null) {
                        operaciones.insertar(album, cliente);
                    } else {
                        System.out.println("El álbum o el cliente no existen en la base de datos.");
                    }
                    break;

                case 2: // RECUPERAR / VER
                    System.out.println("Introduzca el ID del cliente:");
                    int idClienteVer = readInt(scanner);
                    operaciones.verDatos(idClienteVer);
                    break;

                case 3: // ACTUALIZAR
                    System.out.println("Ingrese el ID del cliente a actualizar:");
                    int idClienteActualizar = readInt(scanner);
                    scanner.nextLine(); // Limpiar el búfer de entrada

                    System.out.println("Ingrese el nuevo nombre completo:");
                    String nombreCompleto = scanner.nextLine();

                    System.out.println("Ingrese la nueva dirección:");
                    String direccion = scanner.nextLine();

                    System.out.println("Ingrese el nuevo correo:");
                    String correo = scanner.nextLine();

                    System.out.println("Ingrese el nuevo teléfono:");
                    String telefono = readPhone(scanner);

                    System.out.println("Ingrese la nueva foto:");
                    String foto = scanner.nextLine();

                    operaciones.modificarDato(idClienteActualizar, nombreCompleto, direccion, correo, telefono, foto);
                    break;

                case 4: // BORRAR
                    System.out.println("Ingrese el ID del cliente cuyo registro de venta desea eliminar:");
                    int idClienteBorrar = readInt(scanner);

                    System.out.println("Ingrese el ID de la venta que desea eliminar:");
                    int idVenta = readInt(scanner);

                    operaciones.borrarDato(idClienteBorrar, idVenta);
                    break;

                case 5:
                    exit = true; // Salir del ciclo y finalizar la aplicación
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        // Cerrar el scanner al finalizar
        scanner.close();
    }

    // Verifica si es un número entero
    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, introduzca un número entero válido:");
            scanner.next(); // Consumir el token no válido
        }
        return scanner.nextInt();
    }

    // Verifica si el teléfono es un número válido
    private static String readPhone(Scanner scanner) {
        String phone;
        while (true) {
            phone = scanner.nextLine();
            if (phone.matches("\\d+")) {
                break;
            } else {
                System.out.println("Por favor, introduzca un número de teléfono válido (solo dígitos):");
            }
        }
        return phone;
    }

    private static int readOption(Scanner scanner) {
        int option = 0;
        boolean validOption = false;

        while (!validOption) {
            try {
                System.out.print("Opción: ");
                option = scanner.nextInt();
                validOption = true;
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }

        return option;
    }
}






//