public class ArbolCandidatos {

    private NodoCandidato raiz;

    public interface AccionCandidato {
        void ejecutar(Candidato candidato);
    }

    public ArbolCandidatos() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public boolean insertar(Candidato candidato) {
        if (candidato == null) {
            return false;
        }
        if (buscarPorNombre(candidato.getNombre()) != null) {
            return false;
        }
        raiz = insertarRec(raiz, candidato);
        return true;
    }

    private NodoCandidato insertarRec(NodoCandidato nodo, Candidato candidato) {
        if (nodo == null) {
            return new NodoCandidato(candidato);
        }

        int cmp = candidato.getNombre().compareToIgnoreCase(nodo.getDato().getNombre());
        if (cmp < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), candidato));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), candidato));
        }
        return nodo;
    }

    public Candidato buscarPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        return buscarRec(raiz, nombre.trim());
    }

    private Candidato buscarRec(NodoCandidato nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        int cmp = nombre.compareToIgnoreCase(nodo.getDato().getNombre());
        if (cmp == 0) {
            return nodo.getDato();
        }
        if (cmp < 0) {
            return buscarRec(nodo.getIzquierdo(), nombre);
        }
        return buscarRec(nodo.getDerecho(), nombre);
    }

    public void inOrden(AccionCandidato accion) {
        inOrdenRec(raiz, accion);
    }

    private void inOrdenRec(NodoCandidato nodo, AccionCandidato accion) {
        if (nodo == null) {
            return;
        }
        inOrdenRec(nodo.getIzquierdo(), accion);
        accion.ejecutar(nodo.getDato());
        inOrdenRec(nodo.getDerecho(), accion);
    }

    public Candidato obtenerGanadorPorVotos() {
        if (raiz == null) {
            return null;
        }

        final Candidato[] ganador = new Candidato[1];
        ganador[0] = null;

        inOrden(new AccionCandidato() {
            @Override
            public void ejecutar(Candidato candidato) {
                if (ganador[0] == null) {
                    ganador[0] = candidato;
                    return;
                }
                if (candidato.getCantidadVotos() > ganador[0].getCantidadVotos()) {
                    ganador[0] = candidato;
                } else if (candidato.getCantidadVotos() == ganador[0].getCantidadVotos()) {
                    // Desempate por nombre (alfabetico)
                    if (candidato.getNombre().compareToIgnoreCase(ganador[0].getNombre()) < 0) {
                        ganador[0] = candidato;
                    }
                }
            }
        });

        return ganador[0];
    }

    public int contarVotosTotales() {
        final int[] total = new int[]{0};
        inOrden(new AccionCandidato() {
            @Override
            public void ejecutar(Candidato candidato) {
                total[0] += candidato.getCantidadVotos();
            }
        });
        return total[0];
    }
}
