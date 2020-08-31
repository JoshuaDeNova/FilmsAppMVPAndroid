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
import model.Historico;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author Joshua
 */
public class HistoricoDAO implements IDAO<Historico, Integer>{
    private final String SQL_FINDALL
            = "SELECT * FROM `historico` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `historico` (`pelicula`, `usuario`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `historico` WHERE idHistorico=";

    private final String SQL_UPDATE = "UPDATE `historico` SET ";

    private MotorSQL motorSql;

    public HistoricoDAO() {
        this.motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public int add(Historico bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getPelicula()+ "', '"
                    + bean.getUsuario() + "');";

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
    public ArrayList<Historico> findAll(Historico bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Historico bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
