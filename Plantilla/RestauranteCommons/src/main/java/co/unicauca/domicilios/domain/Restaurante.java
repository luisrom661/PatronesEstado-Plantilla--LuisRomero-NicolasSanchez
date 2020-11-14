package co.unicauca.domicilios.domain;
import java.io.File;
import java.io.FileInputStream;
/**
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class Restaurante {
	
	private String nit;
	private String nombreRestaurante;
	private String direccionRestaurante;
	private String imagen;
        private String correoRestaurante;
	private String clave;
	
	public Restaurante(String nit, String nombreRestaurante, String direccionRestaurante,String imagenRestaurante, String correoRestaurante, String clave) {
		this.nit = nit;
		this.nombreRestaurante = nombreRestaurante;
		this.direccionRestaurante = direccionRestaurante;
                this.imagen = imagenRestaurante;
		this.correoRestaurante = correoRestaurante;
		this.clave = clave;
	}
	
	public Restaurante() {
	
	}
	
	public String getNit() {
		return nit;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public String getNombreRestaurante() {
		return nombreRestaurante;
	}
	
	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	
	public String getDireccionRestaurante() {
		return direccionRestaurante;
	}
	
	public void setDireccionRestaurante(String direccionRestaurante) {
		this.direccionRestaurante = direccionRestaurante;
	}
        
	public String getImagenRestaurante() {
		return imagen;
	}
	
	public void setImagenRestaurante(String imagenRestaurante) {
		this.imagen = imagenRestaurante;
	}
        
	public String getCorreoRestaurante() {
		return correoRestaurante;
	}
	
	public void setCorreoRestaurante(String correoRestaurante) {
		this.correoRestaurante = correoRestaurante;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
}
