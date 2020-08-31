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
import model.Cine;
import model.Pelicula;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author Joshua
 */
public class CineDAO implements IDAO<Cine, Integer>{
    
    private final String SQL_FINDALL
            = "SELECT * FROM `cine` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `cine` (`nombre`, `direccion`, `precioEntrada`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `cine` WHERE idCine=";

    private final String SQL_UPDATE = "UPDATE `cine` SET ";

    private MotorSQL motorSql;

    public CineDAO() {
        this.motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public int add(Cine bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre() + "', '"
                    + bean.getDireccion() + "', '"
                    + bean.getPrecioEntrada() + "');";

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
    public ArrayList<Cine> findAll(Cine bean) {
        ArrayList<Cine> cines = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdCine()!= 0) {
                    sql += "AND idCine='" + bean.getIdCine()+ "'";
                }
                if (bean.getNombre()!= null) {
                    sql += "AND nombre='" + bean.getNombre()+ "'";
                }
                if (bean.getDireccion()!= null) {
                    sql += "AND direccion=('%" + bean.getDireccion()+ "%')";
                }
                if (bean.getPrecioEntrada()!= null) {
                    sql += "AND GENERO='" + bean.getPrecioEntrada()+ "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Cine cine = new Cine();

                cine.setIdCine(rs.getInt(1));
                cine.setNombre(rs.getString(2));
                cine.setDireccion(rs.getString(3));
                cine.setPrecioEntrada(rs.getString(4));
                
                cines.add(cine);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return cines;
    }

    @Override
    public int update(Cine bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getNombre() != null) {
                    sql += "NOMBRE='" + bean.getNombre() + "'";
                }

                if (bean.getDireccion() != null) {
                    sql += "DIRECCION='" + bean.getDireccion() + "'";
                }

                if (bean.getPrecioEntrada()!= null) {
                    sql += "PRECIOENTRADA='" + bean.getPrecioEntrada() + "'";
                }

                sql += " WHERE `IDCINE`=" + bean.getIdCine()+ ";";
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
