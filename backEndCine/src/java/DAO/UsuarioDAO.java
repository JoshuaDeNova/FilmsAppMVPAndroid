package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;
import utils.ConnectionFactory;
import utils.MotorSQL;

public class UsuarioDAO implements IDAO<Usuario, Integer> {

    private final String SQL_FINDALL
            = "SELECT * FROM `usuario` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `contraseña`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `usuario` WHERE idUsuario=";

    private final String SQL_UPDATE = "UPDATE `usuario` SET ";

    private MotorSQL motorSql;

    public UsuarioDAO() {
        motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public int add(Usuario bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre() + "', '"
                    + bean.getApellidos() + "', '"
                    + bean.getEmail() + "', '"
                    + bean.getContraseña() + "');";

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
    public ArrayList<Usuario> findAll(Usuario bean) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdUsuario() != 0) {
                    sql += " AND idUsuario='" + bean.getIdUsuario() + "'";
                }
                if (bean.getNombre() != null) {
                    sql += " AND Nombre='" + bean.getNombre() + "'";
                }
                if (bean.getApellidos() != null) {
                    sql += " AND Apellido ='" + bean.getApellidos() + "'";
                }
                if (bean.getEmail() != null) {
                    sql += " AND Email='" + bean.getEmail() + "'";
                }
                if (bean.getContraseña() != null) {
                    sql += " AND contraseña ='" + bean.getContraseña() + "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setEmail(rs.getString(4));
                usuario.setContraseña(rs.getString(5));

                usuarios.add(usuario);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return usuarios;
    }

    @Override
    public int update(Usuario bean) {
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

                if (bean.getApellidos() != null) {
                    sql += "APELLIDOS='" + bean.getApellidos() + "'";
                }

                if (bean.getEmail() != null) {
                    sql += "EMAIL='" + bean.getEmail() + "'";
                }

                if (bean.getContraseña() != null) {
                    sql += "CONTRASEÑA='" + bean.getContraseña() + "'";
                }

                sql += " WHERE `idUsuario`=" + bean.getIdUsuario() + ";";
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

    public static void main(String[] args) {
        /*PRUEBAS UNITARIAS - TEST*/
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario(0, null, null, "joshua@gmail.com", "123");

        //Findall - filtra segun campos que no son null o 0
        /*Usuario usuario = new Usuario();
            usuario.setEmail("prueba@gmail.com");
            usuario.setContraseña("12345");*/
        ArrayList<Usuario> lstUsuarios
                = usuarioDAO.findAll(usuario);
        System.out.println(lstUsuarios.toString());
    }
}
