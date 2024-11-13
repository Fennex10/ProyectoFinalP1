package Funciones;

public class Administrador extends Persona {
  
    public String rol;

    public Administrador(String rol, int id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña);
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
    
    public void gestionarUsuarios() {
        
    }

    public void gestionarPeliculas() {
        
    }
      
}