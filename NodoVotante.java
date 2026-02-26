public class NodoVotante {

    private Votante dato;
    private NodoVotante siguiente;

    public NodoVotante(Votante dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Votante getDato() {
        return dato;
    }

    public void setDato(Votante dato) {
        this.dato = dato;
    }

    public NodoVotante getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoVotante siguiente) {
        this.siguiente = siguiente;
    }
}
