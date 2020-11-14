package co.unicauca.domicilios.access;

import co.unicauca.domicilios.domain.Cliente;
import co.unicauca.domicilios.infra.DomiciliosSocket;
import co.unicauca.domicilios.infra.JsonError;
import co.unicauca.domicilios.infra.Protocol;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de Cliente, Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicacion server. Maneja los errores devueltos
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class ClienteAccessImplSockets implements ICliente {
	
	/**
	 * El servicio utiliza un socket para comunicarse con la aplicacion server
	 */
	private DomiciliosSocket mySocket;
	
	public ClienteAccessImplSockets() {
		mySocket = new DomiciliosSocket();
	}
	
	/**
	 * Agrega un cliente. Usando sockets para realizar la peticion
	 *
	 * @param cliente cliente del restaurante
	 * @return nombre del cliente
	 * @throws Exception error al agregar el cliente
	 */
	@Override
	public String addCliente(Cliente cliente) throws Exception {
		String jsonResponse = null;
		String requestJson = addClienteRequestJson(cliente);
		try {
			mySocket.connect();
			jsonResponse = mySocket.sendStream(requestJson);
			mySocket.closeStream();
			mySocket.disconnect();
			
		} catch (IOException exception) {
			Logger.getLogger(ClienteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexion con el servidor", exception);
		}
		
		if (jsonResponse == null) {
			throw new Exception("No se pudo conectar con el servidor");
		} else {
			if (jsonResponse.contains("error")) {
				//Devolvió algún error
				Logger.getLogger(ClienteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
				throw new Exception(extractMessages(jsonResponse));
			} else {
				return cliente.getNombreCliente();
			}
			
		}
	}
	
	/**
	 * Crea la solicitud json de creacion del customer para ser enviado por el socket
	 *
	 * @param cliente objeto customer
	 * @return
	 */
	private String addClienteRequestJson(Cliente cliente) {
		Protocol protocol = new Protocol();
		protocol.setResource("cliente");
		protocol.setAction("post");
		protocol.addParameter("nombreCliente", cliente.getNombreCliente());
		protocol.addParameter("apellidoCliente", cliente.getApellidoCliente());
		protocol.addParameter("fechaNacCliente", cliente.getFechaNacCliente());
		protocol.addParameter("correoCliente", cliente.getCorreoCliente());
		protocol.addParameter("claveCliente", cliente.getClaveCliente());
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(protocol);
		System.out.println("json" + requestJson);
		
		return requestJson;
	}
	
	/**
	 * Busca un Cliente, Utiliza socket para pedir el servicio al servidor
	 *
	 * @param correo correo del cliente
	 * @return Objeto Cliente
	 * @throws Exception cuando no se pueda conectar con el servidor
	 */
	@Override
	public Cliente findCliente(String correo) throws Exception {
		String jsonResponse = null;
		try {
			mySocket.connect();
			String requestJson = consultarClienteRequestJson(correo);
			jsonResponse = mySocket.sendStream(requestJson);
			mySocket.closeStream();
			mySocket.disconnect();
		} catch (IOException ex) {
			Logger.getLogger(ClienteAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (jsonResponse == null) {
			throw new Exception("No se pudo conectar con el servidor");
		} else {
			if (!jsonResponse.contains("error")) {
				Cliente cliente = new Cliente();
				parseToCliente(cliente, jsonResponse);
				return cliente;
			}
		}
		return null;
	}
	
	private void parseToCliente(Cliente cliente, String Json) {
		Gson gson = new Gson();
		Properties prop = gson.fromJson(Json, Properties.class);
		
		cliente.setNombreCliente(prop.getProperty("clienteNombre"));
		cliente.setApellidoCliente(prop.getProperty("clienteApellido"));
		cliente.setFechaNacCliente(prop.getProperty("clienteFecha"));
		cliente.setCorreoCliente(prop.getProperty("clienteCorreo"));
		cliente.setClaveCliente(prop.getProperty("clienteClave"));
	}
	
	/**
	 * Crea una solicitud json para ser enviada por el socket
	 *
	 * @param correo correo por medio del cual se identifica cada cliente
	 * @return
	 */
	private String consultarClienteRequestJson(String correo) {
		Protocol protocol = new Protocol();
		protocol.setResource("cliente");
		protocol.setAction("get");
		protocol.addParameter("correoCliente", correo);
		Gson gson = new Gson();
		String requestJson = gson.toJson(protocol);
		System.out.println("json" + requestJson);
		
		return requestJson;
	}
	
	/**
	 * Extra los mensajes de la lista de errores
	 *
	 * @param jsonResponse lista de mensajes json
	 * @return Mensajes de error
	 */
	private String extractMessages(String jsonResponse) {
		JsonError[] errors = jsonToErrors(jsonResponse);
		String msjs = "";
		for (JsonError error : errors) {
			msjs += error.getMessage();
		}
		return msjs;
	}
	
	/**
	 * Convierte el jsonError a un array de objetos jsonError
	 *
	 * @param jsonError
	 * @return objeto MyError
	 */
	private JsonError[] jsonToErrors(String jsonError) {
		Gson gson = new Gson();
		JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
		return error;
	}
}
