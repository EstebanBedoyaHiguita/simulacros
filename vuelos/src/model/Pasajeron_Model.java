package model;

import Data_base.CRUD_PASAJERO;

import java.util.List;

public class Pasajeron_Model implements CRUD_PASAJERO {
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
    public Object findById(String documento_identidad) {
        return null;
    }

    @Override
    public Object findName(String paciente) {
        return null;
    }
}
