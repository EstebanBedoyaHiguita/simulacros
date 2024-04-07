package model;

import Data_base.CRUD_AVION;
import Data_base.ConfiDB;
import entity.Avion;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Avion_Model implements CRUD_AVION {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Avion objAvion = (Avion) object;

        try{
            String sql = "INSERT INTO avion (modelo,capacidad) VALUE(?,?)";

            PreparedStatement objPrepare = (PreparedStatement)  objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());


            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAvion.setId_avion(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Avion agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agegar Avion"+ e.getMessage());
            ConfiDB.closeConnection();
        }
        return objAvion;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Avion objAvion  = (Avion)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE avion SET modelo = ?,capacidad = ? where id_avion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());
            objPrepare.setInt(3,objAvion.getId_avion());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Avion editado correctamente");

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

        Avion objAvion = (Avion) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM avion WHERE id_avion = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objAvion.getId_avion());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Avion eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listAviones = new ArrayList<>();

        try{
            String sql = "SELECT * FROM avion ORDER BY avion.id_avion ASC;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));


                listAviones.add(objAvion);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga");
        }
        ConfiDB.closeConnection();
        return listAviones;
    }

    @Override
    public Object findById(int id) {
        Connection objConection = ConfiDB.openConnection();

        Avion objAvion = new Avion();

        try{
            String sql = "SELECT * FROM avion WHERE id_avion = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));


            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objAvion;
    }

    @Override
    public List<Object> findModelo(String modelo) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listAviones = new ArrayList<>();

        try{
            String sql = "SELECT * FROM avion WHERE modelo LIKE '%" + modelo + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));


                listAviones.add(objAvion);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener paciente");
        }
        ConfiDB.closeConnection();
        return listAviones;
    }

}
