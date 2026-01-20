import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(" Elecciones Presidenciales ");
            System.out.println("1. Incluir un partido");
            System.out.println("2. Incluir un candidato");
            System.out.println("3. Incluir un votante");
            System.out.println("4. Registrar un voto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("En construcción");
                    break;
                case 2:
                    System.out.println("En construcción");
                    break;
                case 3:
                    System.out.println("En construcción");
                    break;
                case 4:
                    System.out.println("En construcción");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

            System.out.println();

        } while (opcion != 5);

        scanner.close();
    }
}
