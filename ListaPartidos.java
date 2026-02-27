public class ListaPartidos {

    private NodoPartido cabeza;

    public ListaPartidos() {
        this.cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public PartidoPolitico buscarPorId(String id) {
        if (id == null) {
            return null;
        }
        NodoPartido actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getId().equalsIgnoreCase(id.trim())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean insertarOrdenado(PartidoPolitico partido) {
        if (partido == null) {
            return false;
        }

        // No permitir IDs repetidos
        if (buscarPorId(partido.getId()) != null) {
            return false;
        }

        NodoPartido nuevo = new NodoPartido(partido);

        // Insertar al inicio
        if (cabeza == null ||
                partido.getNombre().compareToIgnoreCase(cabeza.getDato().getNombre()) < 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            return true;
        }

        // Insertar en medio/final
        NodoPartido actual = cabeza;
        while (actual.getSiguiente() != null &&
                partido.getNombre().compareToIgnoreCase(actual.getSiguiente().getDato().getNombre()) > 0) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        return true;
    }

    public void resetearVotos() {
        NodoPartido actual = cabeza;
        while (actual != null) {
            actual.getDato().setVotosAcumulados(0);
            actual = actual.getSiguiente();
        }
    }

    public void sumarVotosAId(String idPartido, int votos) {
        PartidoPolitico p = buscarPorId(idPartido);
        if (p != null) {
            p.sumarVotos(votos);
        }
    }

    public void imprimirResumen() {
        if (cabeza == null) {
            System.out.println("(No hay partidos cargados)");
            return;
        }
        System.out.println("\n Partidos (ordenados por nombre)");
        NodoPartido actual = cabeza;
        while (actual != null) {
            PartidoPolitico p = actual.getDato();
            System.out.println("- " + p.getNombre() + " | ID: " + p.getId());
            actual = actual.getSiguiente();
        }
    }

    public void imprimirResultadosConPorcentaje(int totalVotos) {
        if (cabeza == null) {
            System.out.println("(No hay partidos cargados)");
            return;
        }

        System.out.println("\n Porcentaje de votos por partido ");
        NodoPartido actual = cabeza;
        while (actual != null) {
            PartidoPolitico p = actual.getDato();
            double porcentaje = 0.0;
            if (totalVotos > 0) {
                porcentaje = (p.getVotosAcumulados() * 100.0) / totalVotos;
            }
            System.out.printf("%s (ID: %s): %d votos (%.2f%%)%n",
                    p.getNombre(), p.getId(), p.getVotosAcumulados(), porcentaje);
            actual = actual.getSiguiente();
        }
    }
}
