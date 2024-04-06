package model;

import Data_base.CRUD_AVION;

import java.util.List;

public class Avion_Model implements CRUD_AVION {
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
    public Object findName(String name) {
        return null;
    }
}
