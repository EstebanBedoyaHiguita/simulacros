package entity;

public class Medico {
    private int id;

    private String name;
    private  String apellidos;

    private  int id_especialidad;

    private  Especialidad objEspecialidad;

    public Medico() {
    }

    public Medico(int id, String name, String apellidos, int id_especialidad, Especialidad objEspecialidad) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.id_especialidad = id_especialidad;
        this.objEspecialidad = objEspecialidad;
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

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return String.format("Medico:\n" +
                        "  ID: %d\n" +
                        "  Nombre: %s\n" +
                        "  Apellidos: %s\n" +
                        "  ID Especialidad: %d\n"+
                        "Especialidad :"+ this.objEspecialidad.getName(),
                id, name, apellidos, id_especialidad);
    }
}
