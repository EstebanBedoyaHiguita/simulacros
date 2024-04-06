package Model;

import DataBase.CRUD_Cita;
import DataBase.ConfiDB;
import entity.Cita;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cita_Model implements CRUD_Cita {
    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        //Castear el objeto
        Cita objCita = (Cita) object;

        System.out.println(objCita);

        try{
            //Comando sql
            String sql = "INSERT INTO cita (id_paciente,id_medico,fecha_cita,hora_cita,motivo) VALUE(?,?,?,?,?)";
            //Preparar el Statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valores
            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());


            //Ejecutar el query
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCita.setId_cita(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Cita creada correctamente");



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar autor"+ e.getMessage());
        }
        ConfiDB.closeConnection();

        return objCita;
    }

    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        Cita objCita  = (Cita)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE cita SET id_paciente = ?,id_medico = ?,fecha_cita = ?,hora_cita =?,motivo=? where id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId_cita());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Cita editada correctamente");

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            ConfiDB.closeConnection();
        }
        return isEdit;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Cita objCita = (Cita) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM cita WHERE id_cita = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objCita.getId_cita());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Cita eliminada exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaCitas = new ArrayList<>();

        try{
            String sql = "select * from cita \n" +
                    "INNER join paciente on paciente.id = cita.id_paciente\n" +
                    "INNER JOIN medico on medico.id = cita.id_medico\n" +
                    "INNER JOIN especialidad on especialidad.id = medico.id_especialidad;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();

                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente"));
                objCita.setId_medico(objResult.getInt("id_medico"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));


                Paciente objPaciente = new Paciente();

                objPaciente.setId(objResult.getInt("paciente.id"));
                objPaciente.setName(objResult.getString("paciente.name"));
                objPaciente.setApellidos(objResult.getString("paciente.apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("paciente.documento_identidad"));
                objCita.setObjPaciente(objPaciente);

                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("especialidad.id"));
                objEspecialidad.setName(objResult.getString("especialidad.name"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.description"));


                Medico objMedico = new Medico();

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setName(objResult.getString("medico.name"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setId_especialidad(objResult.getInt("medico.id_especialidad"));
                objMedico.setObjEspecialidad(objEspecialidad);

                objCita.setObjMedico(objMedico);

                listaCitas.add(objCita);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaCitas;
    }

    @Override
    public Object findById(int id) {

        return null;
    }

    @Override
    public Object findDate(String date) {
        return null;
    }

    @Override
    public List<Object> finName(String name) {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaCitas = new ArrayList<>();

        try{
            String sql = "select * from cita \n" +
                    "INNER join paciente on paciente.id = cita.id_paciente\n" +
                    "INNER JOIN medico on medico.id = cita.id_medico\n" +
                    "where paciente.name like '%"+ name +"%' ;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();
                System.out.println("vamos bien");
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente"));
                objCita.setId_medico(objResult.getInt("id_medico"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));


                listaCitas.add(objCita);

            }


            return listaCitas;

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaCitas;
    }
}
