public class NodoCandidato {

    private Candidato dato;
    private NodoCandidato izquierdo;
    private NodoCandidato derecho;

    public NodoCandidato(Candidato dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Candidato getDato() {
        return dato;
    }

    public void setDato(Candidato dato) {
        this.dato = dato;
    }

    public NodoCandidato getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoCandidato izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoCandidato getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoCandidato derecho) {
        this.derecho = derecho;
    }
}
