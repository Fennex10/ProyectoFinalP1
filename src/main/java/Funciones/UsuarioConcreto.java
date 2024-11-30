package Funciones;

public abstract class UsuarioConcreto extends Usuario {
    public UsuarioConcreto(int saldo, int tickets, int id, String nombre, String email, String contraseña) {
        super(saldo, tickets, id, nombre, email, contraseña);
    }
}

