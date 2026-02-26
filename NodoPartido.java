public class NodoPartido {

    private PartidoPolitico dato;
    private NodoPartido siguiente;

    public NodoPartido(PartidoPolitico dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public PartidoPolitico getDato() {
        return dato;
    }

    public void setDato(PartidoPolitico dato) {
        this.dato = dato;
    }

    public NodoPartido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPartido siguiente) {
        this.siguiente = siguiente;
    }
}
