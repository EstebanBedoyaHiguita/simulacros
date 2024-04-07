package Data_base;

import java.util.List;

public interface CRUD_PASAJERO {
    public Object insert(Object object);

    public  boolean update (Object object);

    public boolean delete(Object object);

    public List<Object> findAll();

    public Object findById(int id);
    public Object findBydocument(String documento_identidad);

    public Object findName(String paciente);
}
