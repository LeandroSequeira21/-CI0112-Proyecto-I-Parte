public class PartidoPolitico {

    private String id;              // En tu archivo: 3-110-100001
    private String nombre;
    private String colores;
    private String presidente;
    private String correo;

    // Para resultados
    private int votosAcumulados;

    public PartidoPolitico(String nombre, String id, String colores, String presidente, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.colores = colores;
        this.presidente = presidente;
        this.correo = correo;
        this.votosAcumulados = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getVotosAcumulados() {
        return votosAcumulados;
    }

    public void setVotosAcumulados(int votosAcumulados) {
        this.votosAcumulados = votosAcumulados;
    }

    public void sumarVotos(int cantidad) {
        if (cantidad > 0) {
            this.votosAcumulados += cantidad;
        }
    }

    @Override
    public String toString() {
        return "PartidoPolitico{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", colores='" + colores + '\'' +
                ", presidente='" + presidente + '\'' +
                ", correo='" + correo + '\'' +
                ", votosAcumulados=" + votosAcumulados +
                '}';
    }
}
