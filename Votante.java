public class Votante {

    private String cedula;
    private String nombre;
    private String domicilioElectoral;
    private boolean votoEmitido;

    public Votante(String cedula, String nombre, String domicilioElectoral) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.domicilioElectoral = domicilioElectoral;
        this.votoEmitido = false;
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

    public String getDomicilioElectoral() {
        return domicilioElectoral;
    }

    public void setDomicilioElectoral(String domicilioElectoral) {
        this.domicilioElectoral = domicilioElectoral;
    }

    public boolean isVotoEmitido() {
        return votoEmitido;
    }

    public void setVotoEmitido(boolean votoEmitido) {
        this.votoEmitido = votoEmitido;
    }

    public String toString() {
        return "Votante{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilioElectoral='" + domicilioElectoral + '\'' +
                ", votoEmitido=" + votoEmitido +
                '}';
    }
}
