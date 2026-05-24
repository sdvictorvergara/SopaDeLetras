package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloSopa {

    private final String rutaBaseDatos = "jdbc:sqlite:sopa_letras.db";

    public ModeloSopa() {
        crearTablaPalabras();
    }

    public Connection conectarBaseDatos() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(rutaBaseDatos);
            System.out.println("Conexión con SQLite correcta");
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQLite: " + e.getMessage());
        }

        return conexion;
    }

    public void crearTablaPalabras() {
        String sql = """
            CREATE TABLE IF NOT EXISTS palabras (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                palabra TEXT NOT NULL UNIQUE
            )
        """;

        try (Connection conexion = conectarBaseDatos();
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sql);
            System.out.println("Tabla palabras preparada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla palabras: " + e.getMessage());
        }
    }

    public boolean insertarPalabra(String nuevaPalabra) {
        String sql = "INSERT INTO palabras (palabra) VALUES (?)";

        try (Connection conexion = conectarBaseDatos();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nuevaPalabra.toUpperCase());
            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar palabra: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPalabra(String palabraEliminar) {
        String sql = "DELETE FROM palabras WHERE palabra = ?";

        try (Connection conexion = conectarBaseDatos();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, palabraEliminar.toUpperCase());

            int filasEliminadas = sentencia.executeUpdate();

            return filasEliminadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar palabra: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<String> consultarListaPalabras() {
        ArrayList<String> listaPalabras = new ArrayList<>();
        String sql = "SELECT palabra FROM palabras ORDER BY palabra ASC";

        try (Connection conexion = conectarBaseDatos();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            while (resultado.next()) {
                listaPalabras.add(resultado.getString("palabra"));
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar palabras: " + e.getMessage());
        }

        return listaPalabras;
    }

    public String consultarPalabrasTexto() {
        ArrayList<String> listaPalabras = consultarListaPalabras();
        StringBuilder textoPalabras = new StringBuilder();

        for (String palabra : listaPalabras) {
            textoPalabras.append(palabra).append("\n");
        }

        return textoPalabras.toString();
    }
}