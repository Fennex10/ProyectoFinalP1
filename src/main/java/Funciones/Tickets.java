package Funciones;

import java.util.Date;

public class Tickets {
    
    public int IdTicket;
    public Usuario usuario;
    public Peliculas pelicula;
    public Date fechaCompra;

    public Tickets(int IdTicket, Usuario usuario, Peliculas pelicula, Date fechaCompra) {
        this.IdTicket = IdTicket;
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.fechaCompra = new Date();
    }

    public int getIdTicket() {
        return IdTicket;
    }

    public void setIdTicket(int IdTicket) {
        this.IdTicket = IdTicket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Peliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(Peliculas pelicula) {
        this.pelicula = pelicula;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public void generarTicket(Usuario usuario, Peliculas pelicula) {
        
    }
    
    
}
