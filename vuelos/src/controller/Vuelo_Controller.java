package controller;

import entity.Avion;
import entity.Vuelo;
import model.Avion_Model;
import model.Vuelo_Model;

import javax.swing.*;
import java.util.List;

public class Vuelo_Controller {

    Vuelo_Model objVuelo_Model = new Vuelo_Model();

    Avion_Model objAvion_Model = new Avion_Model();

    public void create (){
        Vuelo objVuelo = new Vuelo();

        List<Object> listAviones = objAvion_Model.findAll();

        Avion[] listAvion = new Avion[listAviones.size()];
        int id_avion =0;
        int index = 0;

        for(Object iterados : listAviones){
            Avion obj = (Avion) iterados;
            listAvion[index] = obj;
            index++;
        }

        Avion AvionSelect = (Avion) JOptionPane.showInputDialog(null,
                "select a Avion:\n","Choosing Avion",
                JOptionPane.QUESTION_MESSAGE,null,listAvion,listAvion[0]);

        if (AvionSelect != null) {

            id_avion = AvionSelect.getId_avion();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Avion.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo");
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha que desea agendar el vuelo:"+"{aaaa/mm/dd}");
        String hora = JOptionPane.showInputDialog("Ingrese la hora en la que desea agendar el vuelo 24HH"+"{hh:mm:ss}");

        objVuelo.setId_avion(id_avion);
        objVuelo.setFecha_salida(fecha);
        objVuelo.setHora_salida(hora);
        objVuelo.setDestino(destino);




        objVuelo = (Vuelo) this.objVuelo_Model.insert(objVuelo);
        JOptionPane.showMessageDialog(null,objVuelo.toString());
    }
}
