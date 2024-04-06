package model;

import Data_base.CRUD_RESERVACION;

import java.util.List;

public class Reservacion_Model implements CRUD_RESERVACION {
    @Override
    public Object insert(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public Object findName(String paciente) {
        return null;
    }
}
