package controller;

import entity.Avion;
import model.Avion_Model;

import javax.swing.*;
import java.util.List;

public class Avion_Controller {

    Avion_Model objAvion_Model;

    public Avion_Controller() {
        this.objAvion_Model = new Avion_Model();
    }

    public  void create(){
        Avion objAvion = new Avion();

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del paciente");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad "));


        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);


        objAvion = (Avion) this.objAvion_Model.insert(objAvion);
        JOptionPane.showMessageDialog(null,objAvion.toString());

    }

    public void  edit (){
        List<Object> listAviones = objAvion_Model.findAll();

        Avion[] listAvion = new Avion[listAviones.size()];

        int index = 0;
        for(Object iterados : listAviones){
            Avion obj = (Avion) iterados;
            listAvion[index] = obj;
            index++;
        }

        Avion avionSelect = (Avion) JOptionPane.showInputDialog(null,
                "select a Avion:\n","Choosing Avion",
                JOptionPane.QUESTION_MESSAGE,null,listAvion,listAvion[0]);

        if(avionSelect != null){

            String modelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo", avionSelect.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad de pasajeros", avionSelect.getCapacidad()));


            avionSelect.setModelo(modelo);
            avionSelect.setCapacidad(capacidad);



            boolean editado = this.objAvion_Model.update (avionSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Avion editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el avion");
            }

        }

    }

    public void delete(){
        List<Object> listAviones = objAvion_Model.findAll();

        Avion[] listAvion = new Avion[listAviones.size()];

        int index = 0;
        for(Object iterados : listAviones){
            Avion obj = (Avion) iterados;
            listAvion[index] = obj;
            index++;
        }

        Avion AvionSelect = (Avion) JOptionPane.showInputDialog(null,
                "select a Avion:\n","Choosing Avion",
                JOptionPane.QUESTION_MESSAGE,null,listAvion,listAvion[0]);

        this.objAvion_Model.delete(AvionSelect);
    }

    public void  getAll(){
        String list = "Lista de Aviones \n";

        for  (Object obj : this.objAvion_Model.findAll()){
            Avion objAvion = (Avion) obj;
            list += objAvion.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void buscarModelo (){
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo");
        List<Object> listAviones = objAvion_Model.findModelo(modelo);
        String srtlistAviones = "";

        if(listAviones.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listAviones){
                Avion objAvion = (Avion) obj;
                srtlistAviones += objAvion.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Pacientes encontrados:"+srtlistAviones);
        }
    }

    public void buscarId(){
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Avion objAvion = (Avion) this.objAvion_Model.findById(id_avion);

        if(objAvion == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objAvion);
        }

    }



}
