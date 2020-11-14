package co.unicauca.restaurant.server.access;

import co.unicauca.domicilios.domain.Cliente;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementacion de IClienteRepository. Utiliza arreglos en memoria
 * 
 * @author Luis Romero, Jose Nicolas Santander
 */
public final class ClienteRepositoryImplArrays implements IClienteRepository {

    /**
     * Array List de clientes
     */
    private static List<Cliente> clientes;

    public ClienteRepositoryImplArrays() {
        if (clientes == null){
            clientes = new ArrayList();
        }
        
        if (clientes.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {
        clientes.add(new Cliente("Luis", "Romero", "15/03/2000", "luisrom@hotmail.com", "12345"));
        clientes.add(new Cliente("Santiago", "Vivas", "19/06/1995",  "Sago@gmail.com", "12345"));
        clientes.add(new Cliente( "Libardo", "Pantoja", "24/09/1998",  "libardo@gmail.com", "12345"));
    }

    @Override
    public String addCliente(Cliente cliente) {
        clientes.add(cliente);
        return cliente.getCorreoCliente();
    }
    
    /**
     * Busca el cliente correspondiente al correo
     * @param correo correo del cliente
     * @return cliente encontrado
     */
    @Override
    public Cliente findCliente(String correo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCorreoCliente().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }

}
