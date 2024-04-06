package entity;

public class Paciente {
    private int id;
    private String name;
    private String apellidos;
    private String fecha_nacimiento;
    private String documento_identidad;

    public Paciente() {
    }

    public Paciente(int id, String name, String apellidos, String fecha_nacimiento, String documento_identidad) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento_identidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return String.format("Paciente:\n" +
                        "  ID: %d\n" +
                        "  Nombre completo: %s %s\n" +
                        "  Fecha de nacimiento: %s\n" +
                        "  Documento de identidad: %s",
                id, name, apellidos, fecha_nacimiento, documento_identidad);
    }

}
