package Funciones;

import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Tickets implements FTickets {

    private String pelicula;
    private String asientos;
    private double costo;
    private int numeroTicket;

    public Tickets(String pelicula, String asientos, double costo) {
        this.pelicula = pelicula;
        this.asientos = asientos;
        this.costo = costo;
        this.numeroTicket = generarNumeroTicket(); // Genera un número único para el ticket
    }

    public Tickets() {

    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    @Override
    public int generarNumeroTicket() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    @Override
    public void guardarEnBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO tickets (numeroTicket, pelicula, asientos, costo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, numeroTicket);
            stmt.setString(2, pelicula);
            stmt.setString(3, asientos);
            stmt.setDouble(4, costo);

            stmt.executeUpdate();
            System.out.println("Ticket guardado correctamente en la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}