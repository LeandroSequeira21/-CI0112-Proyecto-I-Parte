import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SistemaElecciones {

    private ListaPartidos partidos;
    private ArbolCandidatos candidatos;
    private ListaCantones cantones;

    public SistemaElecciones() {
        this.partidos = new ListaPartidos();
        this.candidatos = new ArbolCandidatos();
        this.cantones = new ListaCantones();
    }


    public void cargarPartidosDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[] p = linea.split(";");
                if (p.length < 2) {
                    System.out.println("[Aviso] Linea invalida en Partidos: " + linea);
                    continue;
                }

                String nombre = limpiar(p[0]);
                String id = limpiar(p[1]);
                String colores = (p.length >= 3) ? limpiar(p[2]) : "";
                String presidente = (p.length >= 4) ? limpiar(p[3]) : "";
                String correo = (p.length >= 5) ? limpiar(p[4]) : "";

                if (nombre.isEmpty() || id.isEmpty()) {
                    System.out.println("[Aviso] Partido con datos vacios: " + linea);
                    continue;
                }

                boolean ok = partidos.insertarOrdenado(new PartidoPolitico(nombre, id, colores, presidente, correo));
                if (!ok) {
                    System.out.println("[Aviso] Partido duplicado (ID repetido): " + id);
                }
            }
        } catch (IOException e) {
            System.out.println("[Aviso] No se pudo leer el archivo de partidos: " + ruta);
        }
    }

    public void cargarVotantesDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[] p = linea.split(";");
                if (p.length < 3) {
                    System.out.println("[Aviso] Linea invalida en Padron: " + linea);
                    continue;
                }

                String cedula = limpiar(p[0]);
                String nombre = limpiar(p[1]);
                String canton = limpiar(p[2]);

                if (!validarCedula(cedula) || nombre.isEmpty() || canton.isEmpty()) {
                    System.out.println("[Aviso] Datos invalidos en Padron: " + linea);
                    continue;
                }

                boolean ok = cantones.insertarVotanteEnCanton(new Votante(cedula, nombre, canton));
                if (!ok) {
                    System.out.println("[Aviso] Votante duplicado: " + cedula);
                }
            }
        } catch (IOException e) {
            System.out.println("[Aviso] No se pudo leer el archivo de padron: " + ruta);
        }
    }

    private String limpiar(String s) {
        if (s == null) {
            return "";
        }
        return s.trim();
    }

    private boolean validarCedula(String cedula) {
        if (cedula == null) {
            return false;
        }
        String c = cedula.trim();
        if (c.isEmpty()) {
            return false;
        }
        return c.matches("\\d{1}-\\d{4}-\\d{4}") || c.matches("[0-9-]{6,20}");
    }


    public void incluirPartido(Scanner sc) {
        System.out.println("\n Incluir Partido ");
        sc.nextLine(); 

        System.out.print("Nombre del partido: ");
        String nombre = sc.nextLine().trim();

        System.out.print("ID del partido (ej: 3-110-100016): ");
        String id = sc.nextLine().trim();

        System.out.print("Colores (opcional): ");
        String colores = sc.nextLine().trim();

        System.out.print("Presidente (opcional): ");
        String presidente = sc.nextLine().trim();

        System.out.print("Correo (opcional): ");
        String correo = sc.nextLine().trim();

        if (nombre.isEmpty() || id.isEmpty()) {
            System.out.println("[Error] Nombre e ID no pueden ir vacios.");
            return;
        }

        boolean ok = partidos.insertarOrdenado(new PartidoPolitico(nombre, id, colores, presidente, correo));
        if (ok) {
            System.out.println("Partido agregado correctamente.");
        } else {
            System.out.println("[Error] No se pudo agregar. Revisa que el ID no este repetido.");
        }
    }

    public void incluirCandidato(Scanner sc) {
        System.out.println("\n Incluir Candidato ");
        if (partidos.estaVacia()) {
            System.out.println("[Error] No hay partidos cargados. Primero agrega/carga partidos.");
            return;
        }

        sc.nextLine();
        System.out.print("Cedula del candidato: ");
        String cedula = sc.nextLine().trim();

        System.out.print("Nombre del candidato: ");
        String nombre = sc.nextLine().trim();

        partidos.imprimirResumen();
        System.out.print("ID del partido del candidato (segun lista): ");
        String idPartido = sc.nextLine().trim();

        if (partidos.buscarPorId(idPartido) == null) {
            System.out.println("[Error] Ese ID de partido no existe.");
            return;
        }

        int edad = leerEntero(sc, "Edad del candidato: ");

        boolean costarricense = leerBoolean(sc, "Es costarricense? (S/N): ");

        // Validaciones 
        if (!validarCedula(cedula)) {
            System.out.println("[Error] Cedula invalida.");
            return;
        }
        if (nombre.isEmpty()) {
            System.out.println("[Error] Nombre no puede ir vacio.");
            return;
        }
        if (edad <= 0) {
            System.out.println("[Error] Edad invalida.");
            return;
        }
        if (!costarricense) {
            System.out.println("[Error] El candidato debe ser costarricense.");
            return;
        }

        boolean ok = candidatos.insertar(new Candidato(cedula, nombre, idPartido, edad, true));
        if (ok) {
            System.out.println("Candidato agregado correctamente.");
        } else {
            System.out.println("[Error] No se pudo agregar. Puede que el nombre ya exista en el arbol.");
        }
    }

    public void incluirVotante(Scanner sc) {
        System.out.println("\n Incluir Votante ");
        sc.nextLine();

        System.out.print("Cedula del votante: ");
        String cedula = sc.nextLine().trim();

        System.out.print("Nombre del votante: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Canton (domicilio electoral): ");
        String canton = sc.nextLine().trim();

        if (!validarCedula(cedula) || nombre.isEmpty() || canton.isEmpty()) {
            System.out.println("[Error] Datos invalidos.");
            return;
        }

        boolean ok = cantones.insertarVotanteEnCanton(new Votante(cedula, nombre, canton));
        if (ok) {
            System.out.println("Votante agregado correctamente.");
        } else {
            System.out.println("[Error] No se pudo agregar. Puede que la cedula ya exista.");
        }
    }

    public void registrarVoto(Scanner sc) {
        System.out.println("\n Registrar Voto ");
        if (candidatos.estaVacio()) {
            System.out.println("[Error] No hay candidatos registrados.");
            return;
        }

        sc.nextLine();
        System.out.print("Cedula del votante: ");
        String cedulaV = sc.nextLine().trim();

        Votante votante = cantones.buscarVotantePorCedula(cedulaV);
        if (votante == null) {
            System.out.println("[Error] Votante no existe en el padron.");
            return;
        }

        if (votante.isVotoEmitido()) {
            System.out.println("[Error] Este votante ya emitio su voto.");
            return;
        }

        System.out.println("\nCandidatos disponibles (ordenados alfabeticamente):");
        candidatos.inOrden(new ArbolCandidatos.AccionCandidato() {
            @Override
            public void ejecutar(Candidato c) {
                PartidoPolitico p = partidos.buscarPorId(c.getIdPartido());
                String nombrePartido = (p != null) ? p.getNombre() : "(Partido no encontrado)";
                System.out.println("- " + c.getNombre() + " | Cedula: " + c.getCedula() + " | Partido: " + nombrePartido);
            }
        });

        System.out.print("\nEscriba EXACTAMENTE el nombre del candidato por el que vota: ");
        String nombreC = sc.nextLine().trim();

        Candidato candidato = candidatos.buscarPorNombre(nombreC);
        if (candidato == null) {
            System.out.println("[Error] No existe un candidato con ese nombre.");
            return;
        }

        // Registrar voto
        candidato.sumarVoto();
        votante.setVotoEmitido(true);

        System.out.println("Voto registrado correctamente.");
    }

    public void mostrarResultadosFinales() {
        System.out.println("\n Resultados Finales");

        if (candidatos.estaVacio()) {
            System.out.println("No hay candidatos. No se pueden mostrar resultados.");
            return;
        }

        // Resetear votos acumulados por partido
        partidos.resetearVotos();

        // Sumar votos de cada candidato al partido
        candidatos.inOrden(new ArbolCandidatos.AccionCandidato() {
            public void ejecutar(Candidato c) {
                partidos.sumarVotosAId(c.getIdPartido(), c.getCantidadVotos());
            }
        });

        // Total de votos
        int total = candidatos.contarVotosTotales();

        // Ganador
        Candidato ganador = candidatos.obtenerGanadorPorVotos();
        if (ganador == null) {
            System.out.println("No hay ganador.");
            return;
        }

        PartidoPolitico partidoGanador = partidos.buscarPorId(ganador.getIdPartido());
        String nombrePartido = (partidoGanador != null) ? partidoGanador.getNombre() : "(Partido no encontrado)";

        System.out.println("Ganador/a: " + ganador.getNombre());
        System.out.println("Partido: " + nombrePartido + " | ID: " + ganador.getIdPartido());
        System.out.println("Votos del ganador: " + ganador.getCantidadVotos());
        System.out.println("Total de votos emitidos: " + total);

        // Porcentajes por partido
        partidos.imprimirResultadosConPorcentaje(total);
    }

    private int leerEntero(Scanner sc, String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                break;
            } else {
                sc.next();
            System.out.println("[Error] Debe ingresar un numero.");
            }
        }
        return valor;
    }

    private boolean leerBoolean(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String r = sc.next().trim();
            if (r.equalsIgnoreCase("S") || r.equalsIgnoreCase("SI") || r.equalsIgnoreCase("Y")) {
                return true;
            }
            if (r.equalsIgnoreCase("N") || r.equalsIgnoreCase("NO")) {
                return false;
            }
            System.out.println("[Error] Responda con S o N.");
        }
    }
}
