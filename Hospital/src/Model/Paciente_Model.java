package Model;

import DataBase.CRUD_Paciente;
import DataBase.ConfiDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paciente_Model implements CRUD_Paciente {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Paciente objPaciente = (Paciente) object;

        try{
          String sql = "INSERT INTO paciente (name,apellidos,fecha_nacimiento,documento_identidad) VALUE(?,?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement)  objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPaciente.getName());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPaciente.setId(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Paciente agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agegar paciente"+ e.getMessage());
            ConfiDB.closeConnection();
        }
        return objPaciente;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Paciente objPaciente  = (Paciente)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE paciente SET name = ?,apellidos = ?,fecha_nacimiento = ?,documento_identidad =? where id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setString(1,objPaciente.getName());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId());

            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Paciente editado correctamente");

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

        Paciente objPaciente = (Paciente) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM paciente WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objPaciente.getId());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Paciente eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listPacientes = new ArrayList<>();

        try{
            String sql = "SELECT * FROM paciente ORDER BY paciente.id ASC;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Paciente objPaciente = new Paciente();

                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga");
        }
        ConfiDB.closeConnection();
        return listPacientes;
    }

    @Override
    public Object findById(int id) {

        Connection objConection = ConfiDB.openConnection();

        Paciente objPaciente = new Paciente();

        try{
            String sql = "SELECT * FROM paciente WHERE id = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objPaciente;
    }

    @Override
    public List<Object> findName(String name) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listPacientes = new ArrayList<>();

        try{
            String sql = "SELECT * FROM paciente WHERE name LIKE '%" + name + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Paciente objPaciente = new Paciente();

                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener paciente");
        }
        ConfiDB.closeConnection();
        return listPacientes;
    }
}
