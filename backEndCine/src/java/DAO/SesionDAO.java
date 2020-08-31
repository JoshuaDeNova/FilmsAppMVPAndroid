/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sesion;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author Joshua
 */
public class SesionDAO implements IDAO<Sesion, Integer>{

    private final String SQL_FINDALL
            = "SELECT * FROM `sesion` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `sesion` (`butaca`, `sala`, `aforo`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `sesion` WHERE idCine=";

    private final String SQL_UPDATE = "UPDATE `sesion` SET ";

    private MotorSQL motorSql;
    
    public SesionDAO(){
        this.motorSql = ConnectionFactory.selectDb();
    }
    
    @Override
    public int add(Sesion bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getIdSesion()+ "', '"
                    + bean.getButaca() + "', '"
                    + bean.getSala() + "', '"
                    + bean.getAforo() + "');";

//                    + bean.getsPuntuacion() + "',"
//                    + "CURRENT_DATE)";
            resp = motorSql.execute(sql);
            /*RECUPERAR EL ID DEL ULTIMO OBJETO INSERTADO*/

 /*FIN*/
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Película insertada con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        motorSql.connect();
        try {
            String sql = SQL_DELETE + id;
            //desactivo la restriccion de claves foráneas para borrado
            motorSql.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSql.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            motorSql.execute("SET FOREIGN_KEY_CHECKS=1;");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    @Override
    public ArrayList<Sesion> findAll(Sesion bean) {
        ArrayList<Sesion> sesiones = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdSesion()!= 0) {
                    sql += "AND IDSESION='" + bean.getIdSesion()+ "'";
                }
                if (bean.getButaca()!= 0) {
                    sql += "AND BUTACA='" + bean.getButaca()+ "'";
                }
                if (bean.getSala()!= null) {
                    sql += "AND SALA=('%" + bean.getSala()+ "%')";
                }
                if (bean.getAforo()!= null) {
                    sql += "AND AFORO='" + bean.getAforo()+ "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Sesion sesion = new Sesion();

                sesion.setIdSesion(rs.getInt(1));
                sesion.setButaca(rs.getInt(2));
                sesion.setSala(rs.getString(3));
                sesion.setAforo(rs.getString(4));
                
                sesiones.add(sesion);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return sesiones;
    }

    @Override
    public int update(Sesion bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getButaca() != 0) {
                    sql += "BUTACA='" + bean.getButaca()+ "'";
                }

                if (bean.getSala()!= null) {
                    sql += "SALA='" + bean.getSala()+ "'";
                }

                if (bean.getAforo()!= null) {
                    sql += "AFORO='" + bean.getAforo()+ "'";
                }

                sql += " WHERE `idSesion`=" + bean.getIdSesion()+ ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Pelicula actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
}
