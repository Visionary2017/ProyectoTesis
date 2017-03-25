package pe.edu.sise.proyectotesis;

/**
 * Created by JEAN on 24/03/2017.
 */

public class Item {

    private int imagen;
    private String titulo;
    private String contenido;

    public Item(int imagen, String titulo, String contenido) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getImagen() {
        return imagen;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
