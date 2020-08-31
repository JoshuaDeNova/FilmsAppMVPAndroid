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
import model.Proyecciones;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author Joshua
 */
public class ProyeccionesDAO implements IDAO<Proyecciones, Integer>{
    
    private final String SQL_FINDALL
            = "SELECT * FROM `proyecciones` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `proyecciones` (`pelicula`, `sesion`, `fecha`, `horario`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `proyecciones` WHERE idCine=";

    private final String SQL_UPDATE = "UPDATE `proyecciones` SET ";

    private MotorSQL motorSql;
    
    public ProyeccionesDAO(){
        this.motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public int add(Proyecciones bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getIdProyeccion()+ "', '"
                    + bean.getPelicula()+ "', '"
                    + bean.getSesion() + "', '"
                    + bean.getFecha() + "', '"
                    + bean.getHorario() + "');";

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
    public ArrayList<Proyecciones> findAll(Proyecciones bean) {
        ArrayList<Proyecciones> proyectando = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdProyeccion()!= 0) {
                    sql += "AND IDPROYECCION='" + bean.getIdProyeccion()+ "'";
                }
                if (bean.getPelicula() != 0) {
                    sql += "AND PELICULA='" + bean.getPelicula() + "'";
                }
                if (bean.getSesion()!= 0) {
                    sql += "AND SESION='" + bean.getSesion()+ "'";
                }
                if (bean.getFecha()!= null) {
                    sql += "AND FECHA='" + bean.getFecha()+ "'";
                }
                if (bean.getHorario()!= null) {
                    sql += "AND HORARIO='" + bean.getHorario()+ "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Proyecciones proyeccion = new Proyecciones();

                proyeccion.setIdProyeccion(rs.getInt(1));
                proyeccion.setPelicula(rs.getInt(2));
                proyeccion.setSesion(rs.getInt(3));
                proyeccion.setFecha(rs.getString(4));
                proyeccion.setHorario(rs.getString(5));

                proyectando.add(proyeccion);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return proyectando;
    }

    @Override
    public int update(Proyecciones bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getPelicula() != 0) {
                    sql += "PELICULA='" + bean.getPelicula() + "'";
                }

                if (bean.getSesion()!= 0) {
                    sql += "SESION='" + bean.getSesion()+ "'";
                }

                if (bean.getFecha()!= null) {
                    sql += "FECHA='" + bean.getFecha()+ "'";
                }

                if (bean.getHorario() != null) {
                    sql += "HORARIO='" + bean.getHorario() + "'";
                }

                sql += " WHERE `idProyeccion`=" + bean.getIdProyeccion()+ ";";
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
