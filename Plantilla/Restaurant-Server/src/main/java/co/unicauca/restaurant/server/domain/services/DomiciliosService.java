package co.unicauca.restaurant.server.domain.services;

import co.unicauca.domicilios.domain.Cliente;
import co.unicauca.domicilios.infra.JsonError;
import co.unicauca.restaurant.server.access.IClienteRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Romero, Jose Nicolas Santander
 */
public class DomiciliosService {
	
	IClienteRepository domiciliosRepository;
	
	public DomiciliosService(IClienteRepository domiciliosRepository) {
		this.domiciliosRepository = domiciliosRepository;
	}
	
	public Cliente findCliente(String correo) {
		return domiciliosRepository.findCliente(correo);
	}
	
	public String addCliente(Cliente cliente) {
		List<JsonError> errors = new ArrayList<>();
		
		// Validaciones y reglas de negocio
		if (cliente.getCorreoCliente().isEmpty() || cliente.getNombreCliente().isEmpty()) {
			errors.add(new JsonError("400", "BAD_REQUEST", "id, nombres, precio son obligatorios. "));
		}
		
		if (!errors.isEmpty()) {
			Gson gson = new Gson();
			String errorsJson = gson.toJson(errors);
			return errorsJson;
		}
		return domiciliosRepository.addCliente(cliente);
	}
	
}
