package Funciones;

public class Usuario {
    
    public int IdUsuario;
    public String Email;
    public String contraseña;
    public int saldo;
    public int tickets;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public Usuario(int IdUsuario, String Email, String contraseña, int saldo, int tickets) {
        this.IdUsuario = IdUsuario;
        this.Email = Email;
        this.contraseña = contraseña;
        this.saldo = saldo;
        this.tickets = tickets;
    }
    
    
}
