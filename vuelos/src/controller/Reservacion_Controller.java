package controller;

import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.Pasajero_Model;
import model.Reservacion_Model;
import model.Vuelo_Model;

import javax.swing.*;
import java.util.List;

public class Reservacion_Controller {
    Reservacion_Model objReservacionModel = new Reservacion_Model();

    Pasajero_Model objPasajeroModel = new Pasajero_Model();
    Vuelo_Model objVueloModel = new Vuelo_Model();

    public void create (){
        Reservacion objreserva = new Reservacion();

        List<Object> listPasajeros = objPasajeroModel.findAll();

        Pasajero[] listPasajero = new Pasajero[listPasajeros.size()];
        int id_pasajero=0;
        int index = 0;
        int id_vuelo =0;
        for(Object iterados : listPasajeros){
            Pasajero obj = (Pasajero) iterados;
            listPasajero[index] = obj;
            index++;
        }

        Pasajero pasajeroSelect = (Pasajero) JOptionPane.showInputDialog(null,
                "select a Pasajero:\n","Choosing Pasajero",
                JOptionPane.QUESTION_MESSAGE,null,listPasajero,listPasajero[0]);

        if (pasajeroSelect != null) {

            id_pasajero = pasajeroSelect.getId_pasajero();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Vuelo_Model objVuelo_Model = new Vuelo_Model();
        List<Object> listVuelos = objVuelo_Model.findAll();

        Vuelo[] listVuelo = new Vuelo[listVuelos.size()];

        int i = 0;
        for(Object iterados : listVuelos){
            Vuelo obj = (Vuelo) iterados;
            listVuelo[i] = obj;
            i++;
        }

        Vuelo vueloSelect = (Vuelo) JOptionPane.showInputDialog(null,
                "select a vuelo:\n","Choosing vuelo",
                JOptionPane.QUESTION_MESSAGE,null,listVuelo,listVuelo[0]);

        if (vueloSelect != null) {

            id_vuelo = vueloSelect.getId_vuelo();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String fecha = JOptionPane.showInputDialog("Ingrese la fecha que desea realizar la reserva:"+"{aaaa/mm/dd}");
        String asiento = JOptionPane.showInputDialog("Ingrese el asiento");

        objreserva.setId_pasajero(id_pasajero);
        objreserva.setId_vuelo(id_vuelo);
        objreserva.setFecha_reservacion(fecha);
        objreserva.setAsiento(asiento);

        objreserva.setObjPasajero(pasajeroSelect);
        objreserva.setObjVuelo(vueloSelect);

        objreserva = (Reservacion) this.objReservacionModel.insert(objreserva);
        JOptionPane.showMessageDialog(null,objreserva.toString());
    }

    public void  edit (){
        List<Object> listReservas = objReservacionModel.findAll();


        Reservacion[] listreserva = new Reservacion[listReservas.size()];
        int id_reservacion =0;
        int index = 0;
        for(Object iterados : listReservas){
            Reservacion obj = (Reservacion) iterados;
            listreserva[index] = obj;
            index++;
        }

        Reservacion reservaSelect = (Reservacion) JOptionPane.showInputDialog(null,
                "select a reservacion:\n","Choosing reservacion",
                JOptionPane.QUESTION_MESSAGE,null,listreserva,listreserva[0]);

        if(reservaSelect != null){
            id_reservacion= reservaSelect.getId_reservacion();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una reservacion.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Pasajero_Model objPasajero = new Pasajero_Model();
        List<Object> listPasajeros = objPasajeroModel.findAll();

        Pasajero[] listPasajero = new Pasajero[listPasajeros.size()];
        int id_pasajero = 0;
        int i = 0;
        for(Object iterados : listPasajeros){
            Pasajero obj = (Pasajero) iterados;
            listPasajero[i] = obj;
            i++;
        }

        Pasajero PasajeroSelect = (Pasajero) JOptionPane.showInputDialog(null,
                "select a Pasajero:\n","Choosing Pasajero",
                JOptionPane.QUESTION_MESSAGE,null,listPasajero,listPasajero[0]);

        if (PasajeroSelect != null) {

            id_pasajero = PasajeroSelect.getId_pasajero();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Vuelo_Model objVuelo_Model = new Vuelo_Model();
        List<Object> listVuelos = objVuelo_Model.findAll();

        Vuelo[] listVuelo = new Vuelo[listVuelos.size()];
        int id_vuelo = 0;
        int in = 0;
        for(Object iterados : listVuelos){
            Vuelo obj = (Vuelo) iterados;
            listVuelo[in] = obj;
            in++;
        }

        Vuelo VueloSelect = (Vuelo) JOptionPane.showInputDialog(null,
                "select a Vuelo:\n","Choosing Vuelo",
                JOptionPane.QUESTION_MESSAGE,null,listVuelo,listVuelo[0]);

        if (VueloSelect != null) {

            id_vuelo = VueloSelect.getId_vuelo();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String fecha_reserva = JOptionPane.showInputDialog("Ingrese la fecha que desea reprogramar la reserva{aaaa,dd,mm}",reservaSelect.getFecha_reservacion());
        String asiento = JOptionPane.showInputDialog("Ingrese el asiento",reservaSelect.getAsiento());

        reservaSelect.setId_pasajero(id_pasajero);
        reservaSelect.setId_vuelo(id_vuelo);
        reservaSelect.setFecha_reservacion(fecha_reserva);
        reservaSelect.setAsiento(asiento);


        boolean editado = this.objReservacionModel.update (reservaSelect);

        if(editado){
            JOptionPane.showMessageDialog(null,"Reservacion editada correctamente");
        }else {
            JOptionPane.showMessageDialog(null, "Error al editar el Reservacion");
        }



    }

    public void  getAll(){
        String list = "Lista de Reservaciones \n";

        for  (Object obj : this.objReservacionModel.findAll()){
            Reservacion objReserva = (Reservacion) obj;
            list += objReserva.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }
    public void delete(){
        List<Object> listReservas = objReservacionModel.findAll();

        Reservacion[] listReserva = new Reservacion[listReservas.size()];

        int index = 0;
        for(Object iterados : listReservas){
            Reservacion obj = (Reservacion) iterados;
            listReserva[index] = obj;
            index++;
        }

        Reservacion ReservaSelect = (Reservacion) JOptionPane.showInputDialog(null,
                "select a cita:\n","Choosing cita",
                JOptionPane.QUESTION_MESSAGE,null,listReserva,listReserva[0]);

        this.objReservacionModel.delete(ReservaSelect);
    }

    public void buscarDate(){
        String fecha_reservacion = JOptionPane.showInputDialog("Ingrese la fecha de la reservacion");
        List<Object> listReservas = objReservacionModel.findDate(fecha_reservacion);

        String srtlistaReservaciones = "";

        if(listReservas.isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacia");
        }else {
            for (Object obj:listReservas){
                Reservacion objCita = (Reservacion) obj;
                srtlistaReservaciones += objCita.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,srtlistaReservaciones);
        }
    }

    public void buscarId(){
        int id_reservacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Reservacion objReserva = (Reservacion) this.objReservacionModel.findById(id_reservacion);

        if(objReserva == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objReserva);
        }

    }
}
