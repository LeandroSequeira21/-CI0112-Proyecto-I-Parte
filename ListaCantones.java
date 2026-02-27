public class ListaCantones {

    private NodoCanton cabeza;
    private NodoCanton cola;

    public ListaCantones() {
        this.cabeza = null;
        this.cola = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    private NodoCanton buscarNodo(String nombreCanton) {
        if (nombreCanton == null) {
            return null;
        }
        NodoCanton actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getNombre().equalsIgnoreCase(nombreCanton.trim())) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Canton obtenerOCrearCanton(String nombreCanton) {
        if (nombreCanton == null) {
            return null;
        }
        String canon = nombreCanton.trim();
        if (canon.isEmpty()) {
            return null;
        }

        NodoCanton encontrado = buscarNodo(canon);
        if (encontrado != null) {
            return encontrado.getDato();
        }

        Canton nuevoCanton = new Canton(canon);
        insertarOrdenado(nuevoCanton);
        return nuevoCanton;
    }

    private void insertarOrdenado(Canton canton) {
        NodoCanton nuevo = new NodoCanton(canton);

        if (cabeza == null) {
            cabeza = cola = nuevo;
            return;
        }

        // Insertar al inicio
        if (canton.getNombre().compareToIgnoreCase(cabeza.getDato().getNombre()) < 0) {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
            return;
        }

        // Insertar al final
        if (canton.getNombre().compareToIgnoreCase(cola.getDato().getNombre()) > 0) {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
            return;
        }

        // Insertar en medio
        NodoCanton actual = cabeza;
        while (actual != null && canton.getNombre().compareToIgnoreCase(actual.getDato().getNombre()) > 0) {
            actual = actual.getSiguiente();
        }

        // Insertar antes de "actual"
        NodoCanton anterior = actual.getAnterior();
        anterior.setSiguiente(nuevo);
        nuevo.setAnterior(anterior);
        nuevo.setSiguiente(actual);
        actual.setAnterior(nuevo);
    }

    public boolean insertarVotanteEnCanton(Votante votante) {
        if (votante == null) {
            return false;
        }

        // Evita cedulas repetidas en cualquier canton
        if (buscarVotantePorCedula(votante.getCedula()) != null) {
            return false;
        }

        Canton canton = obtenerOCrearCanton(votante.getDomicilioElectoral());
        if (canton == null) {
            return false;
        }
        return canton.getVotantes().insertarOrdenado(votante);
    }

    public Votante buscarVotantePorCedula(String cedula) {
        if (cedula == null) {
            return null;
        }
        NodoCanton actual = cabeza;
        while (actual != null) {
            Votante v = actual.getDato().getVotantes().buscarPorCedula(cedula.trim());
            if (v != null) {
                return v;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void imprimirPadron() {
        if (cabeza == null) {
            System.out.println("(Padron vacio)");
            return;
        }
        NodoCanton actual = cabeza;
        while (actual != null) {
            System.out.println("\n=== Canton: " + actual.getDato().getNombre() + " ===");
            actual.getDato().getVotantes().imprimir();
            actual = actual.getSiguiente();
        }
    }
}
