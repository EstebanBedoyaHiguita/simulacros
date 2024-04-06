package entity;

public class Reservacion {

    private int id_reservacion;
    private int id_pasajero;
    private int id_vuelo;
    private String fecha_reservacion;
    private String asiento;

    private Pasajero objPasajero;
    private Vuelo objVuelo;

    public Reservacion() {
    }

    public Reservacion(int id_reservacion, int id_pasajero, int id_vuelo, String fecha_reservacion, String asiento, Pasajero objPasajero, Vuelo objVuelo) {
        this.id_reservacion = id_reservacion;
        this.id_pasajero = id_pasajero;
        this.id_vuelo = id_vuelo;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
        this.objPasajero = objPasajero;
        this.objVuelo = objVuelo;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "id_reservacion=" + id_reservacion +
                ", id_pasajero=" + id_pasajero +
                ", id_vuelo=" + id_vuelo +
                ", fecha_reservacion='" + fecha_reservacion + '\'' +
                ", asiento='" + asiento + '\'' +
                ", NombrePasajero=" + this.objPasajero.getNombre() +
                ", Vuelo=" + this.objVuelo.toString() +
                '}';
    }
}
