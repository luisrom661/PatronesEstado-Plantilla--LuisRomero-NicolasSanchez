package co.unicauca.domicilios.domain;

import co.unicauca.domicilios.access.ClienteAccessImplSockets;
import co.unicauca.domicilios.access.ICliente;

/**
 * @author Luis Romero, Jose Nicolas Santander
 */
public class CreateNewClient {
	
	private ICliente client;
	
	public CreateNewClient() {
		client = new ClienteAccessImplSockets();
	}
	
	public void clientRegistry(String name, String lastName, String birthdate, String email, String password) throws Exception {
		
		Cliente client = new Cliente(name, lastName, birthdate, email, password);
		
		this.client.addCliente(client);
		
	}
	
}
