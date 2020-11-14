package co.unicauca.restaurant.server.access;

import co.unicauca.domicilios.domain.Cliente;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repositorio de Clientes en MySQL
 *
 * @author Luis Romero, Jose Nicolas Santander
 */
public class ClienteRepositoryImplMySql implements IClienteRepository {

    /**
     * Conexión con MySQL
     */
    private Connection con;
    private ResultSet rs;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "system";
    private static final String url = "jdbc:mysql://localhost:3306/clientes";

    public ClienteRepositoryImplMySql() {
    }

    public ResultSet getResultado() {
        return rs;
    }

    /**
     * Busca en la base de datos un cliente
     * @param correo 
     * @return 
     */
    @Override
    public Cliente findCliente(String correo) {
        Cliente cliente = null;

        this.connect();
        try {
            String sql = "SELECT * from clientes where correoCliente=? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, correo);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                cliente = new Cliente();
                cliente.setNombreCliente(res.getString("nombreCliente"));
                cliente.setApellidoCliente(res.getString("apellidoCliente"));
                cliente.setFechaNacCliente(res.getString("fechaNacCliente"));
                cliente.setCorreoCliente(res.getString("correoCliente"));
                cliente.setClaveCliente(res.getString("claveCliente"));

            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepositoryImplMySql.class.getName()).log(Level.SEVERE, "Error al consultar Cliente de la base de datos", ex);
        }
        return cliente;
    }

    @Override
    public String addCliente(Cliente cliente) {
        try {
            this.connect();
            String sql = "INSERT INTO clientes(nombreCliente, apellidoCliente, fechaNacCliente, correoCliente, claveCliente) VALUES (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cliente.getNombreCliente());
            statement.setString(2, cliente.getApellidoCliente());
            statement.setString(3, cliente.getFechaNacCliente());
            statement.setString(4, cliente.getCorreoCliente());
            statement.setString(5, cliente.getClaveCliente());
            statement.executeUpdate();
            statement.close();
            this.disconnect();

        } catch (SQLException exception) {
            Logger.getLogger(ClienteRepositoryImplMySql.class.getName()).log(Level.SEVERE, "Error al insertar el registro");
        }

        return cliente.getCorreoCliente();
    }

    /**
     * Conexión con la DB
     *
     * @return
     */
    public void connect() {
        // Reseteamos a null la conexion a la bd
        con = null;
        try {
            Class.forName(driver);

            // Nos conectamos a la bd
            con = (Connection) DriverManager.getConnection(url, user, pass);

            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con != null) {
                System.out.println("Conexion establecida");
            }
        } // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion" + e);
        }
    }

    /**
     * Cierra la conexion con la base de datos
     *
     */
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException exception) {
            Logger.getLogger(ClienteRepositoryImplMySql.class.getName()).log(Level.FINER, "Error al cerrar Connection", exception);
        }
    }
}
