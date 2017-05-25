package pe.edu.sise.proyectotesis;

/**
 * Created by Angel Mamani on 6/05/2017.
 */

public class Entidad_Empleado {

    public String id_persona;
    public String apellidos;
    public String nombres;
    public String direccion;
    public String dni;
    public String telefono;
    public String sexo;
    public String estado;

    public Entidad_Empleado() {
    }

    public Entidad_Empleado(String id_persona, String apellidos, String nombres, String direccion, String dni, String telefono, String sexo, String estado) {
        this.id_persona = id_persona;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
        this.sexo = sexo;
        this.estado = estado;
    }

    public String getid_persona() {
        return id_persona;
    }

    public void setid_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
