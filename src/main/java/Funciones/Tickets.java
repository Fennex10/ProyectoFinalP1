package Funciones;

public class Tickets {
    
    public int IdTicket;
    public String usuario;
    public int fechaConpra;

    public Tickets(int IdTicket, String usuario, int fechaConpra) {
        this.IdTicket = IdTicket;
        this.usuario = usuario;
        this.fechaConpra = fechaConpra;
    }

    public int getIdTicket() {
        return IdTicket;
    }

    public void setIdTicket(int IdTicket) {
        this.IdTicket = IdTicket;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getFechaConpra() {
        return fechaConpra;
    }

    public void setFechaConpra(int fechaConpra) {
        this.fechaConpra = fechaConpra;
    }
    
    
}
