public class Candidato {

    private String cedula;
    private String nombre;
    private PartidoPolitico partido;
    private int edad;
    private int cantidadVotos;
    private boolean costarricense;

    public Candidato(String cedula, String nombre, PartidoPolitico partido,
                     int edad, boolean costarricense) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.partido = partido;
        this.edad = edad;
        this.costarricense = costarricense;
        this.cantidadVotos = 0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PartidoPolitico getPartido() {
        return partido;
    }

    public void setPartido(PartidoPolitico partido) {
        this.partido = partido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }

    public boolean isCostarricense() {
        return costarricense;
    }

    public void setCostarricense(boolean costarricense) {
        this.costarricense = costarricense;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", partido=" + partido.getNombre() +
                ", edad=" + edad +
                ", cantidadVotos=" + cantidadVotos +
                ", costarricense=" + costarricense +
                '}';
    }
}
