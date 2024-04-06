package Model;

import DataBase.CRUD_Medico;
import DataBase.ConfiDB;
import com.mysql.cj.protocol.Resultset;
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

public class Medico_Model implements CRUD_Medico {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();
        Medico objMedico = (Medico)object;

        try{
            String sql = "INSERT INTO medico (name,apellidos,id_especialidad) VALUE(?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedico.getName());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getId_especialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Medico agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar medico"+ e.getMessage());
        }
        return objMedico;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Medico objMedico =(Medico)object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE medico SET name = ?,apellidos = ?,id_especialidad = ? where id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedico.getName());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getId_especialidad());
            objPrepare.setInt(4,objMedico.getId());

            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Editado Exitosamente");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfiDB.closeConnection();
        return isEdit;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Medico objPaciente = (Medico) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM medico WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objPaciente.getId());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Medico eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfiDB.openConnection();
        List<Object> listMedicos = new ArrayList<>();

        try{
            String sql = "SELECT * FROM medico \n" +
                    "INNER JOIN especialidad on especialidad.id = medico.id_especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while(objResult.next()){
                Medico objMedico = new Medico();

                objMedico.setId(objResult.getInt("id"));
                objMedico.setName(objResult.getString("name"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));


                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("especialidad.id"));
                objEspecialidad.setName(objResult.getString("especialidad.name"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.description"));
                objMedico.setObjEspecialidad(objEspecialidad);

                listMedicos.add(objMedico);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga ");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();
        return listMedicos;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfiDB.openConnection();

        Medico objMedico = new Medico();

        try{
            String sql = "SELECT * FROM medico WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objMedico.setId(objResult.getInt("id"));
                objMedico.setName(objResult.getString("name"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));


            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objMedico;
    }

    @Override
    public List<Object> findName(String name) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listMedicos = new ArrayList<>();

        try{
            String sql = "SELECT * FROM medico WHERE name LIKE '%" + name + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();

                objMedico.setId(objResult.getInt("id"));
                objMedico.setName(objResult.getString("name"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));


                listMedicos.add(objMedico);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener paciente");
        }
        ConfiDB.closeConnection();
        return listMedicos;
    }
}
