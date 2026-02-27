public class Canton {

    private String nombre;
    private ListaVotantes votantes;

    public Canton(String nombre) {
        this.nombre = nombre;
        this.votantes = new ListaVotantes();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaVotantes getVotantes() {
        return votantes;
    }

    public void setVotantes(ListaVotantes votantes) {
        this.votantes = votantes;
    }
}
