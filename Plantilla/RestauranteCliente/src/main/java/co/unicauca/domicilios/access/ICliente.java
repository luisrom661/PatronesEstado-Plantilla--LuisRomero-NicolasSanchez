
package co.unicauca.domicilios.access;

import co.unicauca.domicilios.domain.Cliente;

/**
 * Interfaz que define los servicios de clientes para el programa Deliverya
 *
 * @author Luis Romero, Jose Nicolas Santander
 */

public interface ICliente {
	/**
	 * Agrega un cliente utilizando un socket
	 *
	 * @param cliente Cliente del restaurante
	 * @return String cliente, en caso contrario, retorna null
	 * @throws Exception
	 */
	public String addCliente(Cliente cliente) throws Exception;
	
	/**
	 * Busca un cliente
	 *
	 * @param correo recibe correo para buscar el cliente
	 * @return Cliente encontrado
	 * @throws Exception Error al encontrar al ciente
	 */
	public Cliente findCliente(String correo) throws Exception;
}
