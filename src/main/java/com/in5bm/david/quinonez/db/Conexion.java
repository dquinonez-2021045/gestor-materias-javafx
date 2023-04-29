package com.in5bm.david.quinonez.db;

/**
 *
 * @author informatica
 */
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.*;

public class Conexion {

    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DB = "db_control_materia_in5bm";
    private static final String USER = "root";
    private static final String PASS = "admin";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private Connection conexion;
    private static Conexion instance;

    //PATRON SINGLETON
    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement psmt) {
        try {
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void close(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    public Connection getConexion() {
        return conexion;
    }
    
    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("La conexion fue todo un exito");

            DatabaseMetaData dma = conexion.getMetaData();
            System.out.println("\nConected to: " + dma.getURL());
            System.out.println("Driver utilizad: " + dma.getDriverName());
            System.out.println("Version utilizada: " + dma.getDriverVersion() + "\n");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("No encuentra ninguna definicion para esta clase");

           
        } catch (CommunicationsException e) {           
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Se produjo un error de tipo SQLException");
            System.out.println("SQL SATE: " + e.getSQLState());
            System.out.println("ERROR CODE: " + e.getErrorCode());
            System.out.println("MESSAGE: " + e.getMessage());
            System.out.println("\n");
        } catch (Exception e) {
            System.err.println("Se produjo un error al intetar establecer una conexion con la base de datos");
            e.printStackTrace();
        }

    }
    
    
}
