package co.unicauca.restaurant.server.access;

import co.unicauca.domicilios.domain.Cliente;

/**
 * Interface del repositorio de Clientes
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public interface IClienteRepository {
	public String addCliente(Cliente cliente);
	
	/**
	 * Buscar cliente con el correo
	 *
	 * @param correo correo del cliente
	 * @return cliente encontrado
	 */
	public Cliente findCliente(String correo);
	
}
