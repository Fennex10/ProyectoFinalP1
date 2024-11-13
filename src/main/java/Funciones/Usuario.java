package Funciones;

public class Usuario extends Persona {
    
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
    
    
    
    public void agregarSaldo(int monto) {
        
    
    }

    public void comprarTicket(Peliculas pelicula) {
        
   
    }

}
