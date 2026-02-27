public class ListaVotantes {

    private NodoVotante cabeza;

    public ListaVotantes() {
        this.cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public Votante buscarPorCedula(String cedula) {
        if (cedula == null) {
            return null;
        }
        NodoVotante actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getCedula().equalsIgnoreCase(cedula.trim())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean insertarOrdenado(Votante votante) {
        if (votante == null) {
            return false;
        }
        // Cedula unica dentro del canton 
        if (buscarPorCedula(votante.getCedula()) != null) {
            return false;
        }

        NodoVotante nuevo = new NodoVotante(votante);

        if (cabeza == null ||
                votante.getNombre().compareToIgnoreCase(cabeza.getDato().getNombre()) < 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            return true;
        }

        NodoVotante actual = cabeza;
        while (actual.getSiguiente() != null &&
                votante.getNombre().compareToIgnoreCase(actual.getSiguiente().getDato().getNombre()) > 0) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        return true;
    }

    public void imprimir() {
        NodoVotante actual = cabeza;
        while (actual != null) {
            Votante v = actual.getDato();
            System.out.println(v.getCedula() + " - " + v.getNombre() + (v.isVotoEmitido() ? " (Ya voto)" : ""));
            actual = actual.getSiguiente();
        }
    }
}
