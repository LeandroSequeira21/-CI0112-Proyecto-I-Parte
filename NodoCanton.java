public class NodoCanton {

    private Canton dato;
    private NodoCanton anterior;
    private NodoCanton siguiente;

    public NodoCanton(Canton dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public Canton getDato() {
        return dato;
    }

    public void setDato(Canton dato) {
        this.dato = dato;
    }

    public NodoCanton getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoCanton anterior) {
        this.anterior = anterior;
    }

    public NodoCanton getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCanton siguiente) {
        this.siguiente = siguiente;
    }
}
