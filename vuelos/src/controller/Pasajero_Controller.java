package controller;

import entity.Pasajero;
import model.Pasajero_Model;

import javax.swing.*;
import java.util.List;

public class Pasajero_Controller {
    Pasajero_Model objPasajero_Model;
    public Pasajero_Controller(){
        this.objPasajero_Model = new Pasajero_Model();
    }

    public  void create (){
        Pasajero objPasajero = new Pasajero();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el numero de documento");

        objPasajero.setNombre(nombre);
        objPasajero.setApellidos(apellidos);
        objPasajero.setDocumento_identidad(documento_identidad);

        objPasajero = (Pasajero) this.objPasajero_Model.insert(objPasajero);
        JOptionPane.showMessageDialog(null,objPasajero.toString());
    }

    public void  edit (){
        List<Object> listPasajeros = objPasajero_Model.findAll();

        Pasajero[] listPasajero = new Pasajero[listPasajeros.size()];

        int index = 0;
        for(Object iterados : listPasajeros){
            Pasajero obj = (Pasajero) iterados;
            listPasajero[index] = obj;
            index++;
        }

        Pasajero pasajeroSelect = (Pasajero) JOptionPane.showInputDialog(null,
                "select a pasajero:\n","Choosing pasajero",
                JOptionPane.QUESTION_MESSAGE,null,listPasajero,listPasajero[0]);

        if(pasajeroSelect != null){

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre", pasajeroSelect.getNombre());
            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos", pasajeroSelect.getApellidos());
            String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento de identidad",pasajeroSelect.getDocumento_identidad());

            pasajeroSelect.setNombre(nombre);
            pasajeroSelect.setApellidos(apellidos);
            pasajeroSelect.setDocumento_identidad(documento_identidad);


            boolean editado = this.objPasajero_Model.update (pasajeroSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Pasajero editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el pasajero");
            }

        }

    }

    public void delete(){
        List<Object> listPasajeros = objPasajero_Model.findAll();

        Pasajero[] listPasajero = new Pasajero[listPasajeros.size()];

        int index = 0;
        for(Object iterados : listPasajeros){
            Pasajero obj = (Pasajero) iterados;
            listPasajero[index] = obj;
            index++;
        }

        Pasajero pasajeroSelect = (Pasajero) JOptionPane.showInputDialog(null,
                "select a pasajero:\n","Choosing pasajero",
                JOptionPane.QUESTION_MESSAGE,null,listPasajero,listPasajero[0]);

        this.objPasajero_Model.delete(pasajeroSelect);
    }

    public void  getAll(){
        String list = "Lista de pasajeros \n";

        for  (Object obj : this.objPasajero_Model.findAll()){
            Pasajero objPasajero = (Pasajero) obj;
            list += objPasajero.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void buscarname (){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
        List<Object> listPasajeros = objPasajero_Model.findName(nombre);
        String srtlistPasajeros = "";

        if(listPasajeros.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listPasajeros){
                Pasajero objPasajero = (Pasajero) obj;
                srtlistPasajeros += objPasajero.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Pasajeros encontrados:"+srtlistPasajeros);
        }
    }

    public void buscarDocumento(){
        String documento_Pasajero = JOptionPane.showInputDialog("Ingrese el documento que desea buscar");
        Pasajero objPasajero = (Pasajero) this.objPasajero_Model.findBydocument(documento_Pasajero);

        if(objPasajero == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objPasajero);
        }

    }
}
