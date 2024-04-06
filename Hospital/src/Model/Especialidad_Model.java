package Model;

import DataBase.CRUD_Especialidad;
import DataBase.ConfiDB;
import entity.Especialidad;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Especialidad_Model implements CRUD_Especialidad {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();
        Especialidad objEspecialidad =(Especialidad) object;

        try{
            String sql ="INSERT INTO especialidad (name,description) VALUE(?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getName());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.executeLargeUpdate();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEspecialidad.setId(objResult.getInt(1));
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar especialidad"+e.getMessage());
        }
        ConfiDB.closeConnection();
        return objEspecialidad;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) object;

        boolean isEdit = false;

        try{
            String sql ="UPDATE especialidad SET name = ?,description = ? where id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getName());
            objPrepare.setString(2,objEspecialidad.getDescripcion());
            objPrepare.setInt(3,objEspecialidad.getId());

            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Especialidad editada correctamente");
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

        Especialidad objEspecialidad = (Especialidad) object;

        boolean isdelete = false;

        try{
            String sql = "DELETE FROM especialidad WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objEspecialidad.getId());

            int totalAfect = objPrepare.executeUpdate();

            if (totalAfect > 0){
                isdelete = true;
                JOptionPane.showMessageDialog(null,"Especialidad eliminada correctamente");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfiDB.closeConnection();
        return isdelete;
    }

    @Override
    public List<Object> finAll() {
    Connection objConnection = ConfiDB.openConnection();

    List<Object> listaEspecialidades =  new ArrayList<>();

    try{
        String sql = "SELECT * FROM especialidad ORDER BY especialidad.id ASC;";
        PreparedStatement objPrepare = objConnection.prepareStatement(sql);

        ResultSet objResult = (ResultSet)  objPrepare.executeQuery();

        while (objResult.next()){
        Especialidad objEspecialidad = new Especialidad();

        objEspecialidad.setId(objResult.getInt("id"));
        objEspecialidad.setName(objResult.getString("name"));
        objEspecialidad.setDescripcion(objResult.getString("description"));
        listaEspecialidades.add(objEspecialidad);
        }
    }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"Error en la carga");
    }
        ConfiDB.closeConnection();
        return listaEspecialidades;
    }

    @Override
    public Object findById(int id) {

        Connection objConnection = ConfiDB.openConnection();
        Especialidad objEspecialidad = new Especialidad();

        try{

            String sql = "SELECT * FROM Especialidad WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {

                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setName(objResult.getString("name"));
                objEspecialidad.setDescripcion(objResult.getString("description"));

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> finName(String name) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listEspecialidades = new ArrayList<>();

        try{
            String sql = "SELECT * FROM especialidad WHERE name LIKE '%" + name + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setName(objResult.getString("name"));
                objEspecialidad.setDescripcion(objResult.getString("description"));


                listEspecialidades.add(objEspecialidad);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener paciente");
        }
        ConfiDB.closeConnection();
        return listEspecialidades;

    }
}
