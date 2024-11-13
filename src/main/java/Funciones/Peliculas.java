package Funciones;

public class Peliculas {
    
    public int IdPelicula;
    public String nombre;
    public String categoria;
    public int CostoDeTicket;

    public int getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(int IdPelicula) {
        this.IdPelicula = IdPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCostoDeTicket() {
        return CostoDeTicket;
    }

    public void setCostoDeTicket(int CostoDeTicket) {
        this.CostoDeTicket = CostoDeTicket;
    }

    public Peliculas(int IdPelicula, String nombre, String categoria, int CostoDeTicket) {
        this.IdPelicula = IdPelicula;
        this.nombre = nombre;
        this.categoria = categoria;
        this.CostoDeTicket = CostoDeTicket;
    }
    
    
    public void ObtenerCosto(){
    
    }
    
    public void actualizarCategoria() {
       
    }
    
    
}
