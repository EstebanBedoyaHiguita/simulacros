package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiDB {
    static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://bgesatxfryvq9znzsgqv-mysql.services.clever-cloud.com:3306/bgesatxfryvq9znzsgqv";
            String user = "usjeizjz8yz6p0db";
            String password = "eqXdD4J53aSUIjmc3FY2";

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
