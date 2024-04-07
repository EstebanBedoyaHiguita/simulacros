package model;

import Data_base.CRUD_RESERVACION;
import Data_base.ConfiDB;
import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reservacion_Model implements CRUD_RESERVACION {
    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        //Castear el objeto
        Reservacion objReservacion = (Reservacion) object;



        try{
            //Comando sql
            String sql = "INSERT INTO reservacion (id_pasajero,id_vuelo,fecha_reservacion,asiento) VALUE(?,?,?,?)";
            //Preparar el Statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valores
            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());



            //Ejecutar el query
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objReservacion.setId_reservacion(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Reservacion creada correctamente");



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar Reservacion"+ e.getMessage());
        }
        ConfiDB.closeConnection();

        return objReservacion;
    }

    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        Reservacion objReservacion  = (Reservacion)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE reservacion SET id_pasajero = ?,id_vuelo = ?,fecha_reservacion = ?,asiento=? where id_reservacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());
            objPrepare.setInt(5,objReservacion.getId_reservacion());



            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Reservacion editada correctamente");

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

        Reservacion objReservacion = (Reservacion) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM reservacion WHERE id_reservacion = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objReservacion.getId_reservacion());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Reservacion eliminada exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaReservaciones = new ArrayList<>();

        try{
            String sql = "SELECT *\n" +
                    "FROM reservacion\n" +
                    "INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero\n" +
                    "INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo\n" +
                    "INNER JOIN avion ON avion.id_avion = vuelo.id_avion;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Reservacion objReservacion = new Reservacion();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));



                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellidos"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                objReservacion.setObjPasajero(objPasajero);


                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));

                objReservacion.setObjVuelo(objVuelo);

                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                objVuelo.setObjAvion(objAvion);




                listaReservaciones.add(objReservacion);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaReservaciones;
    }

    @Override
    public Object findById(int id) {

        Connection objConection = ConfiDB.openConnection();

        Reservacion objReservacion = new Reservacion();

        try{
            String sql = "SELECT * FROM reservacion WHERE id_reservacion = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                Vuelo_Model objVuelo_Model = new Vuelo_Model();
                objReservacion.setObjVuelo((Vuelo) objVuelo_Model.findById(objReservacion.getId_vuelo()));

                Pasajero_Model objPasajeroModel = new Pasajero_Model();
                objReservacion.setObjPasajero((Pasajero) objPasajeroModel.findById(objReservacion.getId_pasajero()) );

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objReservacion;
    }

    @Override
    public List<Object> findDate(String fecha_reservacion) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listReservas = new ArrayList<>();

        try{
            String sql = "SELECT * FROM reservacion WHERE fecha_reservacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,fecha_reservacion);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Reservacion objReserva = new Reservacion();

                objReserva.setId_reservacion(objResult.getInt("id_reservacion"));
                objReserva.setId_pasajero(objResult.getInt("id_pasajero"));
                objReserva.setId_vuelo(objResult.getInt("id_vuelo"));
                objReserva.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReserva.setAsiento(objResult.getString("asiento"));

                listReservas.add(objReserva);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener Reservaciones");
        }
        ConfiDB.closeConnection();
        return listReservas;
    }


}
