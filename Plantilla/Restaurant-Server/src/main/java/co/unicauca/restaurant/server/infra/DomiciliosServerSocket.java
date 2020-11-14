package co.unicauca.restaurant.server.infra;

import co.unicauca.domicilios.domain.Cliente;
import co.unicauca.domicilios.infra.JsonError;
import co.unicauca.domicilios.infra.Protocol;
import co.unicauca.domicilios.infra.Utilities;
import co.unicauca.restaurant.server.access.Factory;
import co.unicauca.restaurant.server.access.IClienteRepository;
import co.unicauca.restaurant.server.domain.services.DomiciliosService;
import com.google.gson.*;
import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;


import java.util.ArrayList;
import java.util.List;

/**
 * Servidor Socket que esta escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecucion
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class DomiciliosServerSocket extends ServerSocketTemplate {
	
	/**
	 * Servicio de clientes
	 */
	private DomiciliosService service;
	
	/**
	 * Inicialización
	 *
	 * @return este mismo objeto
	 */
	@Override
	protected ServerSocketTemplate init() {
		PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
		// Se hace la inyección de dependencia
		IClienteRepository repository = Factory.getInstance().getRepository();
		this.setService(new DomiciliosService(repository));
		return this;
	}
	
	/**
	 * Procesar la solicitud que proviene de la aplicación cliente
	 *
	 * @param requestJson petición que proviene del cliente socket en formato
	 *                    json que viene de esta manera:
	 *                    "{"resource":"cliente","action":"get","parameters":[{"name":"id","value":"1"}]}"
	 */
	@Override
	protected void processRequest(String requestJson) {
		// Convertir la solicitud a objeto Protocol para poderlo procesar
		Gson gson = new Gson();
		Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);
		
		switch (protocolRequest.getResource()) {
			case "cliente":
				if (protocolRequest.getAction().equals("get")) {
					// Consultar un cliente
					processGetCliente(protocolRequest);
				}
				
				if (protocolRequest.getAction().equals("post")) {
					// Agregar un cliente
					processPostCliente(protocolRequest);
					
				}
				break;
		}
		
	}
	
	/**
	 * Procesa la solicitud de consultar un cliente
	 *
	 * @param protocolRequest Protocolo de la solicitud
	 */
	private void processGetCliente(Protocol protocolRequest) {
		// Extraer la cedula del primer parámetro
		String id = protocolRequest.getParameters().get(0).getValue();
		Cliente cliente = getService().findCliente(id);
		if (cliente == null) {
			String errorJson = generateNotFoundErrorJson();
			respond(errorJson);
		} else {
			respond(objectToJSON(cliente));
		}
	}
	
	/**
	 * Procesa la solicitud de agregar un cliente
	 *
	 * @param protocolRequest Protocolo de la solicitud
	 */
	private void processPostCliente(Protocol protocolRequest) {
		Cliente cliente = new Cliente();
		// Reconstruir el cliente a partid de lo que viene en los parámetros
		cliente.setNombreCliente(protocolRequest.getParameters().get(0).getValue());
		cliente.setApellidoCliente(protocolRequest.getParameters().get(1).getValue());
		cliente.setFechaNacCliente(protocolRequest.getParameters().get(2).getValue());
		cliente.setCorreoCliente(protocolRequest.getParameters().get(3).getValue());
		cliente.setClaveCliente(protocolRequest.getParameters().get(4).getValue());
		
		String response = getService().addCliente(cliente);
		respond(response);
	}
	
	
	/**
	 * Genera un ErrorJson no encontrado
	 *
	 * @return error en formato json
	 */
	private String generateNotFoundErrorJson() {
		List<JsonError> errors = new ArrayList<>();
		JsonError error = new JsonError();
		error.setCode("404");
		error.setError("NOT_FOUND");
		error.setMessage("ERROR: Id no existe");
		errors.add(error);
		
		Gson gson = new Gson();
		String errorsJson = gson.toJson(errors);
		
		return errorsJson;
	}
	
	/**
	 * @return the service
	 */
	public DomiciliosService getService() {
		return service;
	}
	
	/**
	 * @param service the service to set
	 */
	public void setService(DomiciliosService service) {
		this.service = service;
	}
	
	
}
