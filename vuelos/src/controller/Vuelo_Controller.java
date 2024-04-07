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

    public void  edit (){
        List<Object> listVuelos = objVuelo_Model.findAll();


        Vuelo[] listVuelo = new Vuelo[listVuelos.size()];
        int id_vuelo =0;
        int index = 0;
        for(Object iterados : listVuelos){
            Vuelo obj = (Vuelo) iterados;
            listVuelo[index] = obj;
            index++;
        }

        Vuelo vueloSelect = (Vuelo) JOptionPane.showInputDialog(null,
                "select a vuelo:\n","Choosing vuelo",
                JOptionPane.QUESTION_MESSAGE,null,listVuelo,listVuelo[0]);

        if(vueloSelect != null){
            id_vuelo = vueloSelect.getId_vuelo();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Avion_Model objAvion_Model = new Avion_Model();
        List<Object> listAviones = objAvion_Model.findAll();

        Avion[] listAvion = new Avion[listAviones.size()];
        int id_avion = 0;
        int i = 0;
        for(Object iterados : listAviones){
            Avion obj = (Avion) iterados;
            listAvion[i] = obj;
            i++;
        }

        Avion AvionSelect = (Avion) JOptionPane.showInputDialog(null,
                "select a Avion:\n","Choosing Avion",
                JOptionPane.QUESTION_MESSAGE,null,listAvion,listAvion[0]);

        if (AvionSelect != null) {

            id_avion = AvionSelect.getId_avion();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un avion.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String destiono = JOptionPane.showInputDialog("Ingrese el destino",vueloSelect.getDestino());
        String fecha_salida = JOptionPane.showInputDialog("Ingrese la fecha que desea reprogramar el vuelo {aaaa,dd,mm}",vueloSelect.getFecha_salida());
        String hora_cita = JOptionPane.showInputDialog("Ingrese la hora de la cita"+"{hh:mm:ss}",vueloSelect.getHora_salida());

        vueloSelect.setId_avion(id_avion);

        vueloSelect.setFecha_salida(fecha_salida);
        vueloSelect.setHora_salida(hora_cita);
        vueloSelect.setDestino(destiono);


        boolean editado = this.objVuelo_Model.update (vueloSelect);

        if(editado){
            JOptionPane.showMessageDialog(null,"vuelo editado correctamente");
        }else {
            JOptionPane.showMessageDialog(null, "Error al editar el vuelo");
        }



    }

    public void  getAll(){
        String list = "Lista de Vuelos \n";

        for  (Object obj : this.objVuelo_Model.findAll()){
            Vuelo objVuelo = (Vuelo) obj;
            list += objVuelo.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void delete(){
        List<Object> listVuelos = objVuelo_Model.findAll();

        Vuelo[] listVuelo = new Vuelo[listVuelos.size()];

        int index = 0;
        for(Object iterados : listVuelos){
            Vuelo obj = (Vuelo) iterados;
            listVuelo[index] = obj;
            index++;
        }

        Vuelo vueloSelect = (Vuelo) JOptionPane.showInputDialog(null,
                "select a Vuelo:\n","Choosing Vuelo",
                JOptionPane.QUESTION_MESSAGE,null,listVuelo,listVuelo[0]);

        this.objVuelo_Model.delete(vueloSelect);
    }

    public void buscarDate(){
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha que desea buscar "+"{aaaa-mm-dd}");
        List<Object> listVuelos = objVuelo_Model.findDate(fecha);

        String srtlistVuelos = "";

        if(listVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacia");
        }else {
            for (Object obj:listVuelos){
                Vuelo objVuelo = (Vuelo) obj;
                srtlistVuelos += objVuelo.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,srtlistVuelos);
        }
    }

    public void buscarId(){
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Vuelo objVuelo = (Vuelo) this.objVuelo_Model.findById(id_vuelo);

        if(objVuelo == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objVuelo);
        }

    }
}
