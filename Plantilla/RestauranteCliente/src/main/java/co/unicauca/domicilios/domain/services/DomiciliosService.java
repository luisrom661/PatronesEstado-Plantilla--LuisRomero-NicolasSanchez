package co.unicauca.domicilios.domain.services;

import co.unicauca.domicilios.domain.Cliente;
import co.unicauca.domicilios.access.ICliente;

/**
 * Es una fachada para comunicar la presentacion con el dominio
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class DomiciliosService {
	
	private final ICliente service;
	
	/**
	 * Constructor privado que evita que otros objetos instancien
	 *
	 * @param service implementacion de tipo ISaladService
	 */
	public DomiciliosService(ICliente service) {
		this.service = service;
	}
	
	/**
	 * Busca un cliente en el servidor remoto
	 *
	 * @param correo que se usa para identificador del cliente
	 * @return Objeto tipo Cliente, null si no lo encuentra
	 * @throws Exception
	 */
	public Cliente findCustomer(String correo) throws Exception {
		return service.findCliente(correo);
		
	}
	
	public String createCliente(Cliente cliente) throws Exception {
		return service.addCliente(cliente);
		
	}
}
