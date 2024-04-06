package controller;

import Model.Especialidad_Model;
import entity.Especialidad;
import entity.Paciente;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {

    Especialidad_Model objEspecialidadModel;

    public  EspecialidadController(){

        this.objEspecialidadModel = new Especialidad_Model();
    }

    public void create(){
        Especialidad objEspecialidad = new Especialidad();

        String name = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad");
        String description = JOptionPane.showInputDialog("Agregue una descripcion de la especialidad");

        objEspecialidad.setName(name);
        objEspecialidad.setDescripcion(description);
        JOptionPane.showMessageDialog(null,objEspecialidad.getDescripcion());

        objEspecialidad = (Especialidad) this.objEspecialidadModel.insert(objEspecialidad);
        JOptionPane.showMessageDialog(null,objEspecialidad.toString());
    }

    public void  edit (){
        List<Object> listEspecialidades = objEspecialidadModel.finAll();

        Especialidad[] listEspecialidad = new Especialidad[listEspecialidades.size()];

        int index = 0;
        for(Object iterados : listEspecialidades){
            Especialidad obj = (Especialidad) iterados;
            listEspecialidad[index] = obj;
            index++;
        }

        Especialidad EspecialidadSelect = (Especialidad) JOptionPane.showInputDialog(null,
                "select Especialidad:\n","Choosing especialidad",
                JOptionPane.QUESTION_MESSAGE,null,listEspecialidad,listEspecialidad[0]);

        if(EspecialidadSelect != null){

            String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre", EspecialidadSelect.getName());
            String description = JOptionPane.showInputDialog("Ingrese la description", EspecialidadSelect.getDescripcion());


            EspecialidadSelect.setName(name);
            EspecialidadSelect.setDescripcion(description);



            boolean editado = this.objEspecialidadModel.update (EspecialidadSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Especialidad editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el Especialidad");
            }

        }

    }

    public void delete(){
        List<Object> listaEspecialidades = objEspecialidadModel.finAll();

        Especialidad[] listaEspecialidad = new Especialidad[listaEspecialidades.size()];

        int index = 0;
        for(Object iterados : listaEspecialidades){
            Especialidad obj = (Especialidad) iterados;
            listaEspecialidad[index] = obj;
            index++;
        }

        Especialidad EspecialidadSelect = (Especialidad) JOptionPane.showInputDialog(null,
                "selecciones la especialidad:\n","Especialidades",
                JOptionPane.QUESTION_MESSAGE,null,listaEspecialidad,listaEspecialidad[0]);

        this.objEspecialidadModel.delete(EspecialidadSelect);
    }

    public void  getAll(){
        String list = "Lista de Especialidades \n";

        for  (Object obj : this.objEspecialidadModel.finAll()){
            Especialidad objEspecialidad = (Especialidad) obj;
            list += objEspecialidad.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void buscar (){
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad");
        List<Object> listaEspecialidades = objEspecialidadModel.finName(name);

//        List<Object> listaEspecialidades = objEspecialidadModel.finName(name);
//        List<Object> listaEspecialidades = objEspecialidadModel.finName(name);
        String srtlistaEspecialidades = "";

        if(listaEspecialidades.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listaEspecialidades){
                Especialidad objEspecialidad = (Especialidad) obj;
                srtlistaEspecialidades += objEspecialidad.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Especialidades encontrados:"+srtlistaEspecialidades);
        }
    }

}
