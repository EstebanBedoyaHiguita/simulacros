package controller;

import DataBase.CRUD_Especialidad;
import Model.Especialidad_Model;
import Model.Medico_Model;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    Medico_Model objMedicoModel;


    EspecialidadController objEspecialidad = new EspecialidadController();

    public MedicoController(){
        this.objMedicoModel = new Medico_Model();
    }

    public void create() {
        Medico objMedico = new Medico();


        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese los apellidos");


        Especialidad_Model objEspecialidadModel = new Especialidad_Model();
        List<Object> listaEspecialidades = objEspecialidadModel.finAll();


        Especialidad[] listaEspecialidad = new Especialidad[listaEspecialidades.size()];
        int index = 0;
        for (Object iterados : listaEspecialidades) {
            Especialidad obj = (Especialidad) iterados;
            listaEspecialidad[index] = obj;
            index++;
        }


        Especialidad especialidadSeleccionada = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Seleccione la especialidad:",
                "Seleccionando especialidad",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listaEspecialidad,
                listaEspecialidad[0]
        );

        if (especialidadSeleccionada != null) {





            objMedico.setName(name);
            objMedico.setApellidos(apellido);
            objMedico.setId_especialidad(especialidadSeleccionada.getId());
            objMedico.setObjEspecialidad(especialidadSeleccionada);


            objMedico = (Medico) this.objMedicoModel.insert(objMedico);
            JOptionPane.showMessageDialog(null,objMedico.toString());
        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una especialidad.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void edit() {
        List<Object> listMedicos = objMedicoModel.findAll();

        if (listMedicos != null) {
            Medico[] listMedico = new Medico[listMedicos.size()];

            int index = 0;
            for (Object iterados : listMedicos) {
                Medico obj = (Medico) iterados;
                listMedico[index] = obj;
                index++;
            }

            Medico medicoSelect = (Medico) JOptionPane.showInputDialog(null,
                    "select a paciente:\n", "Choosing paciente",
                    JOptionPane.QUESTION_MESSAGE, null, listMedico, listMedico[0]);

            if (medicoSelect != null) {

                String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre", medicoSelect.getName());
                String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos", medicoSelect.getApellidos());


                Especialidad_Model objEspecialidadModel = new Especialidad_Model();
                List<Object> listaEspecialidades = objEspecialidadModel.finAll();


                Especialidad[] listaEspecialidad = new Especialidad[listaEspecialidades.size()];
                int i = 0;
                for (Object iterados : listaEspecialidades) {
                    Especialidad obj = (Especialidad) iterados;
                    listaEspecialidad[i] = obj;
                    i++;
                }


                Especialidad especialidadSeleccionada = (Especialidad) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la especialidad:",
                        "Seleccionando especialidad",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listaEspecialidad,
                        listaEspecialidad[0]
                );

                if (especialidadSeleccionada != null) {
                    int id_especialista = especialidadSeleccionada.getId();


                    medicoSelect.setName(name);
                    medicoSelect.setApellidos(apellidos);
                    medicoSelect.setId_especialidad(id_especialista);


                    boolean editado = this.objMedicoModel.update(medicoSelect);

                    if (editado) {
                        JOptionPane.showMessageDialog(null, "Paciente editado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al editar el paciente");
                    }
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "No se encontraron médicos en la base de datos");
        }
    }

    public void getAll(){
        String list = "Lista de medicos \n";

        for(Object obj:this.objMedicoModel.findAll()){
            Medico objMedico = (Medico) obj;
            list += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void delete(){
        List<Object> listMedicos = objMedicoModel.findAll();

        Medico[] listMedico = new Medico[listMedicos.size()];

        int index = 0;
        for(Object iterados : listMedicos){
            Medico obj = (Medico) iterados;
            listMedico[index] = obj;
            index++;
        }

        Medico MedicoSelect = (Medico) JOptionPane.showInputDialog(null,
                "select a role:\n","Choosing role",
                JOptionPane.QUESTION_MESSAGE,null,listMedico,listMedico[0]);

        this.objMedicoModel.delete(MedicoSelect);
    }

    public void buscarId(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Medico objMedico = (Medico) this.objMedicoModel.findById(id);

        if(objMedico == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objMedico);
        }

    }

    public void buscar (){
        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        List<Object> listMedicos = objMedicoModel.findName(name);
        String srtlistMedicos = "";

        if(listMedicos.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listMedicos){
                Medico objMedico = (Medico) obj;
                srtlistMedicos += objMedico.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Pacientes encontrados:"+srtlistMedicos);
        }
    }

}
