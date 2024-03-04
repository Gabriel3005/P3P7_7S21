package DTO;

public class DatosDTO {
    String nombre, correo;

    int id, edad;

    public DatosDTO() {
    }

    public DatosDTO(int id, String nombre, int edad, String correo ) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
