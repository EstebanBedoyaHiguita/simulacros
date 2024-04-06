package DataBase;

import java.util.List;

public interface CRUD_Especialidad {

    public Object insert (Object object);

    public boolean update (Object object);

    public  boolean delete (Object object);

    public List<Object> finAll();

    public Object findById (int id);

    public Object finName (String especialidad);

}
