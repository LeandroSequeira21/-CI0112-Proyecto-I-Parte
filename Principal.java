import java.util.Scanner;

public class Principal {

    
    private static final String ARCHIVO_PARTIDOS = "Partidos Politicos.txt";
    
    private static final String ARCHIVO_PADRON = "Padron electoral.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaElecciones sistema = new SistemaElecciones();

        // Carga inicial 
        sistema.cargarPartidosDesdeArchivo(ARCHIVO_PARTIDOS);
        sistema.cargarVotantesDesdeArchivo(ARCHIVO_PADRON);

        int opcion;
        do {
            System.out.println("\n Elecciones Presidenciales ");
            System.out.println("1. Incluir un partido");
            System.out.println("2. Incluir un candidato");
            System.out.println("3. Incluir un votante");
            System.out.println("4. Registrar un voto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("Seleccione una opcion valida (1-5): ");
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    sistema.incluirPartido(scanner);
                    break;
                case 2:
                    sistema.incluirCandidato(scanner);
                    break;
                case 3:
                    sistema.incluirVotante(scanner);
                    break;
                case 4:
                    sistema.registrarVoto(scanner);
                    break;
                case 5:
                    sistema.mostrarResultadosFinales();
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 5);

        scanner.close();
    }
}
