package Funciones;

public class Tickets implements FTickets {

    public int IdTicket;
    private String pelicula;
    private String asientos;
    private double costoTotal;

    public Tickets(String pelicula, String asientos, double costoTotal) {
        this.pelicula = pelicula;
        this.asientos = asientos;
        this.costoTotal = costoTotal;
    }

    public Tickets() {

    }

    public int getIdTicket() {
        return IdTicket;
    }

    public void setIdTicket(int IdTicket) {
        this.IdTicket = IdTicket;
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

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public String generarTicket(String pelicula1, String asientos1, double costoTotal1) {
        return "Ticket:\n"
                + "Película: " + pelicula + "\n"
                + "Asientos: " + asientos + "\n"
                + "Costo Total: RD$" + costoTotal;
    }
}
