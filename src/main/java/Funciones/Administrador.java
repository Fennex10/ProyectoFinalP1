package Funciones;

public class Administrador {
  
    public int IdAdministrador;
    public String nombre;
    public String apellido;
    public String contraseña;
    public String rol;

    public int getIdAdministrador() {
        return IdAdministrador;
    }

    public void setIdAdministrador(int IdAdministrador) {
        this.IdAdministrador = IdAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Administrador(int IdAdministrador, String nombre, String apellido, String contraseña, String rol) {
        this.IdAdministrador = IdAdministrador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    
}
