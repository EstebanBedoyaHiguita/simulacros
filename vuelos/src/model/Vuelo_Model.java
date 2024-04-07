package model;

import Data_base.CRUD_VUELO;
import Data_base.ConfiDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vuelo_Model implements CRUD_VUELO {
    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        //Castear el objeto
        Vuelo objVuelo = (Vuelo) object;



        try{
            //Comando sql
            String sql = "INSERT INTO vuelo (id_avion,fecha_salida,hora_salida,destino) VALUE(?,?,?,?)";
            //Preparar el Statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valores

            objPrepare.setInt(1,objVuelo.getId_avion());
            objPrepare.setString(2,objVuelo.getFecha_salida());
            objPrepare.setString(3,objVuelo.getHora_salida());
            objPrepare.setString(4,objVuelo.getDestino());


            //Ejecutar el query
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Vuelo creada correctamente");



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar autor"+ e.getMessage());
        }
        ConfiDB.closeConnection();

        return objVuelo;
    }


    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        Vuelo objVuelo  = (Vuelo)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE vuelo SET id_avion = ?,fecha_salida = ?,hora_salida =?,destino=? where id_vuelo = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);


            objPrepare.setInt(1,objVuelo.getId_avion());
            objPrepare.setString(2,objVuelo.getFecha_salida());
            objPrepare.setString(3,objVuelo.getHora_salida());
            objPrepare.setString(4,objVuelo.getDestino());
            objPrepare.setInt(5,objVuelo.getId_vuelo());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Vuelo editada correctamente");

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

        Vuelo objVuelo = (Vuelo) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM vuelo WHERE id_vuelo = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objVuelo.getId_vuelo());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Vuelo eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaVuelos = new ArrayList<>();

        try{
            String sql = "SELECT *\n" +
                    "FROM vuelo\n" +
                    "INNER JOIN avion ON avion.id_avion = vuelo.id_avion;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setDestino(objResult.getString("destino"));


                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                objVuelo.setObjAvion(objAvion);



                listaVuelos.add(objVuelo);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaVuelos;
    }

    @Override
    public Object findById(int id) {

        Connection objConection = ConfiDB.openConnection();

        Vuelo objVuelo = new Vuelo();

        try{
            String sql = "SELECT * FROM vuelo WHERE id-vuelo = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hota_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objVuelo;
    }

    @Override
    public Object findDate(String fecha_salida) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listVuelos = new ArrayList<>();

        try{
            String sql = "SELECT * FROM vuelo WHERE fecha_salida = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));

                listVuelos.add(objVuelo);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener paciente");
        }
        ConfiDB.closeConnection();
        return listVuelos;
    }



}
