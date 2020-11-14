package co.unicauca.domicilios.domain;

/**
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class Cliente {

    private String nombreCliente;
    private String apellidoCliente;
    private String fechaNacCliente;
    private String correoCliente;
    private String claveCliente;

    
    public Cliente(String nombreCliente, String apellidoCliente, String fechaNacCliente, String correoCliente, String claveCliente) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.fechaNacCliente = fechaNacCliente;
        this.correoCliente = correoCliente;
        this.claveCliente = claveCliente;
    }
    /**
     * Constructor por defecto
     */
    public Cliente() {
    }

    /**
     * Getters and Setters
     * @return 
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getFechaNacCliente() {
        return fechaNacCliente;
    }

    public void setFechaNacCliente(String fechaNacCliente) {
        this.fechaNacCliente = fechaNacCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getClaveCliente() {
        return this.claveCliente;
    }

    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }
}
