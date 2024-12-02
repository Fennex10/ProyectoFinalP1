package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario extends Persona implements FUsuario {

    public int saldo;
    public int tickets;

    public Usuario(int saldo, int tickets, int id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña);
        this.saldo = saldo;
        this.tickets = tickets;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void comprarTicket() {

    }

    public void actualizarUsuario() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE usuarios SET saldo = ?, tickets = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, saldo);
            stmt.setInt(2, tickets);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Usuario actualizado: ID " + id);
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public boolean existeUsuarioEnBD() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            String query = "SELECT COUNT(*) FROM cine";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia de usuarios en la base de datos: " + e.getMessage());
        }
        return false;
    }

}