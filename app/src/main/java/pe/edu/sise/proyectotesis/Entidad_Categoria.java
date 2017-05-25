package pe.edu.sise.proyectotesis;

/**
 * Created by Angel Mamani on 6/05/2017.
 */

public class Entidad_Categoria {

    public int id_categoria;
    public  String descripcion;
    public  String descripcionGeneral;
    public String icon;

    public Entidad_Categoria() {
    }

    public Entidad_Categoria(int id_categoria, String descripcion, String descripcionGeneral, String icon) {
        this.id_categoria = id_categoria;
        this.descripcion = descripcion;
        this.descripcionGeneral = descripcionGeneral;
        this.icon = icon;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
