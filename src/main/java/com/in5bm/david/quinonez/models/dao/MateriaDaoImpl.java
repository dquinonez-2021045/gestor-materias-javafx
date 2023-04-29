package com.in5bm.david.quinonez.models.dao;

import com.in5bm.david.quinonez.db.Conexion;
import com.in5bm.david.quinonez.models.domain.Materia;
import com.in5bm.david.quinonez.models.idao.IMateriaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author informatica
 */
public class MateriaDaoImpl implements IMateriaDAO {

    private static final String SQL_SELECT = "SELECT id_materia,nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota FROM materias";

    private static final String SQL_DELETE = "DELETE FROM materias WHERE id_materia = ?";

    private static final String SQL_INSERT = "INSERT INTO materias (nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota) VALUES(?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE materias SET nombre_materia=?,ciclo_escolar=?,horario_inicio=?,horario_final=?,catedratico=?,salon=?,cupo_maximo=?,cupo_minimo=?,nota=? WHERE id_materia= ?";

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    private Materia materia = null;
    public ObservableList listaMaterias;
    private Connection con = null;

    @Override
    public ObservableList getAll() {
        ArrayList<Materia> listaMateria = new ArrayList<>();
        try {
            pstmt = Conexion.getInstance().getConexion().prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            System.out.println("Antes del while");
            while (rs.next()) {
                materia = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre_materia"),
                        rs.getInt("ciclo_escolar"),
                        rs.getTime("horario_inicio").toLocalTime(),
                        rs.getTime("horario_final").toLocalTime(),
                        rs.getString("catedratico"),
                        rs.getString("salon"),
                        rs.getInt("cupo_maximo"),
                        rs.getInt("cupo_minimo"),
                        rs.getInt("nota"));

                System.out.println("MATERIAS:" + materia);
                listaMateria.add(materia);
                System.out.println(listaMaterias);
            }
            listaMaterias = FXCollections.observableArrayList(listaMateria);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(pstmt);
        }
        return listaMaterias;
    }

    @Override
    public int add(Materia materia) {
        int rows = 0;
        ArrayList<Materia> listaMateria = new ArrayList<>();
        try {

            pstmt = Conexion.getInstance().getConexion().prepareStatement(SQL_INSERT);
            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCicloEscolar());
            pstmt.setString(3, materia.getHorarioInicio().toString());
            pstmt.setString(4, materia.getHorarioFinal().toString());
            pstmt.setString(5, materia.getCatedratico());
            pstmt.setString(6, materia.getSalon());
            pstmt.setInt(7, materia.getCupoMaximo());
            pstmt.setInt(8, materia.getCupoMinimo());
            pstmt.setInt(9, materia.getNota());

            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            listaMateria.add(materia);
            rows = 1;

        } catch (SQLException e) {
            System.err.println("NO SE PUDO AGREGAR EL REGISTRO: " + materia);
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(pstmt);
            
        }
        listaMaterias = FXCollections.observableArrayList(listaMateria);
        return rows;
    }

    @Override
    public int update(Materia materia) {
        int rows = 0;
        try {
            
            pstmt = Conexion.getInstance().getConexion().prepareStatement(SQL_UPDATE);
            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCicloEscolar());
            pstmt.setString(3, materia.getHorarioInicio().toString());
            pstmt.setString(4, materia.getHorarioFinal().toString());
            pstmt.setString(5, materia.getCatedratico());
            pstmt.setString(6, materia.getSalon());
            pstmt.setInt(7, materia.getCupoMaximo());
            pstmt.setInt(8, materia.getCupoMinimo());
            pstmt.setInt(9, materia.getNota());
            pstmt.setInt(10, materia.getIdMateria());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("NO SE PUDO ACTUALIZAR EL REGISTRO: " + materia);
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(pstmt);
         
        }
        return rows;
    }

    @Override
    public int delete(Materia materia) {
        int rows = 0;
        try {
            pstmt = Conexion.getInstance().getConexion().prepareStatement(SQL_DELETE);
            pstmt.setInt(1, materia.getIdMateria());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("NO SE PUDO ELIMINAR EL REGISTRO CON EL ID: " + materia.getIdMateria());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return rows;
    }

    
 
    
}
