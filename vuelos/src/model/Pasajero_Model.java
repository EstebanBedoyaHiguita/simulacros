package model;

import Data_base.CRUD_PASAJERO;
import Data_base.ConfiDB;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pasajero_Model implements CRUD_PASAJERO {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Pasajero objPasajero = (Pasajero) object;

        try{
            String sql = "INSERT INTO pasajero (nombre,apellidos,documento_identidad) VALUE(?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement)  objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellidos());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());


            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId_pasajero(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Pasajero agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agegar pasajero"+ e.getMessage());
            ConfiDB.closeConnection();
        }
        return objPasajero;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Pasajero objPasajero  = (Pasajero)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE pasajero SET nombre = ?,apellidos = ?,documento_identidad =? where id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellidos());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Pasajero editado correctamente");

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

        Pasajero objPasajero = (Pasajero) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM pasajero WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objPasajero.getId_pasajero());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Pajero eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listPasajeros = new ArrayList<>();

        try{
            String sql = "SELECT * FROM pasajero ORDER BY paciente.id ASC;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellidos"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga");
        }
        ConfiDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public Object findById(String documento_identidad) {
        Connection objConection = ConfiDB.openConnection();

        Pasajero objPasajero = new Pasajero();

        try{
            String sql = "SELECT * FROM pasajero WHERE documento_identidad = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,documento_identidad);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Documento no encontrado");
                return null;
            }else {
                objPasajero.setId_pasajero(objResult.getInt("id"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellidos"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objPasajero;
    }

    @Override
    public List<Object> findName(String nombre) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listPasajeros = new ArrayList<>();

        try{
            String sql = "SELECT * FROM pasajero WHERE nombre LIKE '%" + nombre + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellidos"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener pasajero");
        }
        ConfiDB.closeConnection();
        return listPasajeros;
    }
}
