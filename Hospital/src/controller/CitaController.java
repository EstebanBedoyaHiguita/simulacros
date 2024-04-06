package controller;

import Model.Cita_Model;
import Model.Medico_Model;
import Model.Paciente_Model;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.util.List;

public class CitaController {
    Cita_Model objCitaModel = new Cita_Model() ;
    Paciente_Model objPacienteModel = new Paciente_Model();



    public void create (){
        Cita objCita = new Cita();

        List<Object> listPacientes = objPacienteModel.findAll();

        Paciente[] listPaciente = new Paciente[listPacientes.size()];
        int id_paciente=0;
        int index = 0;
        int id_medico=0;
        for(Object iterados : listPacientes){
            Paciente obj = (Paciente) iterados;
            listPaciente[index] = obj;
            index++;
        }

        Paciente PacienteSelect = (Paciente) JOptionPane.showInputDialog(null,
                "select a Paciente:\n","Choosing Paciente",
                JOptionPane.QUESTION_MESSAGE,null,listPaciente,listPaciente[0]);

        if (PacienteSelect != null) {

             id_paciente = PacienteSelect.getId();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Paciente.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Medico_Model objMedicoModel = new Medico_Model();
        List<Object> listMedicos = objMedicoModel.findAll();

        Medico[] listMedico = new Medico[listMedicos.size()];

        int i = 0;
        for(Object iterados : listMedicos){
            Medico obj = (Medico) iterados;
            listMedico[i] = obj;
            i++;
        }

        Medico MedicoSelect = (Medico) JOptionPane.showInputDialog(null,
                "select a doctor:\n","Choosing doctor",
                JOptionPane.QUESTION_MESSAGE,null,listMedico,listMedico[0]);

        if (MedicoSelect != null) {

            id_medico = MedicoSelect.getId();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un medico.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String fecha = JOptionPane.showInputDialog("Ingrese la fecha que desea agendar la consulta:"+"{aaaa/mm/dd}");
        String hora = JOptionPane.showInputDialog("Ingrese la hora en la que desea la consulta entre las 08:00 y 17:00"+"{hh:mm:ss}");
        String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la consulta");
        objCita.setId_paciente(id_paciente);
        objCita.setId_medico(id_medico);
        objCita.setFecha_cita(fecha);
        objCita.setHora_cita(hora);
        objCita.setMotivo(motivo);

        objCita.setObjMedico(MedicoSelect);
        objCita.setObjPaciente(PacienteSelect);

        objCita = (Cita) this.objCitaModel.insert(objCita);
        JOptionPane.showMessageDialog(null,objCita.toString());
    }

    public void  edit (){
        List<Object> listCitas = objCitaModel.findAll();


        Cita[] listCita = new Cita[listCitas.size()];
        int id_cita =0;
        int index = 0;
        for(Object iterados : listCitas){
            Cita obj = (Cita) iterados;
            listCita[index] = obj;
            index++;
        }

        Cita citaSelect = (Cita) JOptionPane.showInputDialog(null,
                "select a cita:\n","Choosing cita",
                JOptionPane.QUESTION_MESSAGE,null,listCita,listCita[0]);

        if(citaSelect != null){
            id_cita = citaSelect.getId_cita();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una cita.", "Error", JOptionPane.ERROR_MESSAGE);
        }

            Paciente_Model objPacienteModel = new Paciente_Model();
            List<Object> listPacientes = objPacienteModel.findAll();

            Paciente[] listPaciente = new Paciente[listPacientes.size()];
            int id_paciente = 0;
            int i = 0;
            for(Object iterados : listPacientes){
                Paciente obj = (Paciente) iterados;
                listPaciente[i] = obj;
                i++;
            }

            Paciente PacienteSelect = (Paciente) JOptionPane.showInputDialog(null,
                    "select a Paciente:\n","Choosing Paciente",
                    JOptionPane.QUESTION_MESSAGE,null,listPaciente,listPaciente[0]);

            if (PacienteSelect != null) {

                id_paciente = PacienteSelect.getId();

            } else {

                JOptionPane.showMessageDialog(null, "Debe seleccionar un Paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            Medico_Model objMedicoModel = new Medico_Model();
            List<Object> listMedicos = objMedicoModel.findAll();

            Medico[] listMedico = new Medico[listMedicos.size()];
        int id_medico = 0;
            int in = 0;
            for(Object iterados : listMedicos){
                Medico obj = (Medico) iterados;
                listMedico[in] = obj;
                in++;
            }

            Medico MedicoSelect = (Medico) JOptionPane.showInputDialog(null,
                    "select a doctor:\n","Choosing doctor",
                    JOptionPane.QUESTION_MESSAGE,null,listMedico,listMedico[0]);

            if (MedicoSelect != null) {

                id_medico = MedicoSelect.getId();

            } else {

                JOptionPane.showMessageDialog(null, "Debe seleccionar un medico.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            String fecha_cita = JOptionPane.showInputDialog("Ingrese la fecha que desea reprogramar la cita {aaaa,dd,mm}",citaSelect.getFecha_cita());
            String hora_cita = JOptionPane.showInputDialog("Ingrese la hora de la cita"+"{hh:mm:ss}",citaSelect.getHora_cita());
            String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la consulta",citaSelect.getMotivo());

            citaSelect.setId_paciente(id_paciente);
            citaSelect.setId_medico(id_medico);
            citaSelect.setFecha_cita(fecha_cita);
            citaSelect.setHora_cita(hora_cita);
            citaSelect.setMotivo(motivo);


            boolean editado = this.objCitaModel.update (citaSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Paciente editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el paciente");
            }



    }

    public void  getAll(){
        String list = "Lista de Citas \n";

        for  (Object obj : this.objCitaModel.findAll()){
            Cita objCita = (Cita) obj;
            list += objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void delete(){
        List<Object> listCitas = objCitaModel.findAll();

        Cita[] listCita = new Cita[listCitas.size()];

        int index = 0;
        for(Object iterados : listCitas){
            Cita obj = (Cita) iterados;
            listCita[index] = obj;
            index++;
        }

        Cita citaSelect = (Cita) JOptionPane.showInputDialog(null,
                "select a cita:\n","Choosing cita",
                JOptionPane.QUESTION_MESSAGE,null,listCita,listCita[0]);

        this.objCitaModel.delete(citaSelect);
    }

    public void buscar(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
        List<Object> listCitas = objCitaModel.finName(name);

        String srtlistacitas = "";

        if(listCitas.isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacia");
        }else {
            for (Object obj:listCitas){
                Cita objCita = (Cita) obj;
                srtlistacitas += objCita.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,srtlistacitas);
        }
    }

}
