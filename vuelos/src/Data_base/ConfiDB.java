package Data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiDB {
    static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://b044m0ga7nlstszg9ybz-mysql.services.clever-cloud.com:3306/b044m0ga7nlstszg9ybz";
            String user = "uphnuyp0icnxne2g";
            String password = "ppRlnd0PdVmOnNkvWZlQ";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa");

        }catch (ClassNotFoundException e){
            System.out.println("ERROR >> Driver no instalado");
        }catch(SQLException e){
            System.out.println("Error >> No se pudo establecer una conexion");
        }
        return objConnection;
    }

    public static  void closeConnection(){
        try{
            if(objConnection != null) objConnection.close();
        }catch(SQLException e){
            System.out.println("Error :" + e.getMessage());
        }
    }
}
