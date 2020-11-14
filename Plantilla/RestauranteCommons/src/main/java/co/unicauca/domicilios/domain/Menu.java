package co.unicauca.domicilios.domain;
/**
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class Menu {

	private int codigoPlato;
	private String nombrePlato;
	private String descripcionPlato;
	private int precioPlato;

	public Menu(int codigoPlato, String nombrePlato, String descripcionPlato, int precioPlato) {
		this.codigoPlato = codigoPlato;
		this.nombrePlato = nombrePlato;
		this.descripcionPlato = descripcionPlato;
		this.precioPlato = precioPlato;
	}
	
	public Menu() {
	}
	
	public int getCodigoPlato() {
		return codigoPlato;
	}

	public void setCodigoPlato(int codigoPlato) {
		this.codigoPlato = codigoPlato;
	}

	public String getNombrePlato() {
		return nombrePlato;
	}

	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}

	public String getDescripcionPlato() {
		return descripcionPlato;
	}

	public void setDescripcionPlato(String descripcionPlato) {
		this.descripcionPlato = descripcionPlato;
	}

	public int getPrecioPlato() {
		return precioPlato;
	}

	public void setPrecioPlato(int precioPlato) {
		this.precioPlato = precioPlato;
	}
}
