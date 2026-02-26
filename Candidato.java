public class Candidato {

    private String cedula;
    private String nombre;

    // Requisito: referencia al partido por ID 
    private String idPartido;

    private int edad;
    private int cantidadVotos;
    private boolean costarricense;

    public Candidato(String cedula, String nombre, String idPartido, int edad, boolean costarricense) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.idPartido = idPartido;
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

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
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

    public void sumarVoto() {
        this.cantidadVotos++;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idPartido='" + idPartido + '\'' +
                ", edad=" + edad +
                ", cantidadVotos=" + cantidadVotos +
                ", costarricense=" + costarricense +
                '}';
    }
}
