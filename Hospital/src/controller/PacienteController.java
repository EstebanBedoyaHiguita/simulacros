package controller;

import Model.Paciente_Model;
import entity.Paciente;

import javax.swing.*;
import java.util.List;

public class PacienteController {
    Paciente_Model objPacienteModel;

    public PacienteController(){
        this.objPacienteModel = new Paciente_Model();
    }

    public  void create (){
        Paciente objPaciente = new Paciente();

        String name = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        String fecha_nacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento {aaaa/dd/mm}");
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el numero de documento");

        objPaciente.setName(name);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(fecha_nacimiento);
        objPaciente.setDocumento_identidad(documento_identidad);

        objPaciente = (Paciente) this.objPacienteModel.insert(objPaciente);
        JOptionPane.showMessageDialog(null,objPaciente.toString());
    }

    public void  edit (){
        List<Object> listPacientes = objPacienteModel.findAll();

        Paciente[] listPaciente = new Paciente[listPacientes.size()];

        int index = 0;
        for(Object iterados : listPacientes){
            Paciente obj = (Paciente) iterados;
            listPaciente[index] = obj;
            index++;
        }

        Paciente pacienteSelect = (Paciente) JOptionPane.showInputDialog(null,
                "select a paciente:\n","Choosing paciente",
                JOptionPane.QUESTION_MESSAGE,null,listPaciente,listPaciente[0]);

        if(pacienteSelect != null){

            String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre", pacienteSelect.getName());
            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos", pacienteSelect.getApellidos());
            String fecha_nacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento {aaaa,dd,mm}",pacienteSelect.getFecha_nacimiento());
            String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento de identidad",pacienteSelect.getDocumento_identidad());

            pacienteSelect.setName(name);
            pacienteSelect.setApellidos(apellidos);
            pacienteSelect.setFecha_nacimiento(fecha_nacimiento);
            pacienteSelect.setDocumento_identidad(documento_identidad);


            boolean editado = this.objPacienteModel.update (pacienteSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Paciente editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el paciente");
            }

        }

    }

    public void delete(){
        List<Object> listPacientes = objPacienteModel.findAll();

        Paciente[] listPaciente = new Paciente[listPacientes.size()];

        int index = 0;
        for(Object iterados : listPacientes){
            Paciente obj = (Paciente) iterados;
            listPaciente[index] = obj;
            index++;
        }

        Paciente pacienteSelect = (Paciente) JOptionPane.showInputDialog(null,
                "select a role:\n","Choosing role",
                JOptionPane.QUESTION_MESSAGE,null,listPaciente,listPaciente[0]);

        this.objPacienteModel.delete(pacienteSelect);
    }

    public void  getAll(){
        String list = "Lista de pacientes \n";

        for  (Object obj : this.objPacienteModel.findAll()){
            Paciente objPaciente = (Paciente) obj;
            list += objPaciente.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void buscar (){
        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        List<Object> listPacientes = objPacienteModel.findName(name);
        String srtlistPacientes = "";

        if(listPacientes.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listPacientes){
                Paciente objPaciente = (Paciente) obj;
                srtlistPacientes += objPaciente.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Pacientes encontrados:"+srtlistPacientes);
        }
    }

    public void buscarId(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Paciente objPaciente = (Paciente) this.objPacienteModel.findById(id);

        if(objPaciente == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objPaciente);
        }

    }






}
