package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pelicula;
import utils.ConnectionFactory;
import utils.MotorSQL;

public class PeliculaDAO
        implements IDAO<Pelicula, Integer> {

    private final String SQL_FINDALL
            = "SELECT * FROM `pelicula` WHERE 1=1 ";

    private final String SQL_ADD
            = "INSERT INTO `pelicula` (`titulo`, `descripcion`, `numVotos`, `trailer`, `imagen`, `genero`) VALUES ";

    private final String SQL_DELETE = "DELETE FROM `pelicula` WHERE idPelicula=";

    private final String SQL_UPDATE = "UPDATE `pelicula` SET ";

    private MotorSQL motorSql;

    public PeliculaDAO() {
        motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public ArrayList<Pelicula> findAll(Pelicula bean) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdPelicula()!= 0) {
                    sql += "AND IDPELICULA='" + bean.getIdPelicula()+ "'";
                }
                if (bean.getTitulo() != null) {
                    sql += "AND TITULO='" + bean.getTitulo() + "'";
                }
                if (bean.getDescripcion()!= null) {
                    sql += "AND DESCRIPCION LIKE ('%" + bean.getDescripcion()+ "%')";
                }
                if (bean.getNumVotos()!= 0) {
                    sql += "AND NUMVOTOS='" + bean.getNumVotos()+ "'";
                }
                if (bean.getTrailer()!= null) {
                    sql += "AND TRAILER='" + bean.getTrailer()+ "'";
                }
                if (bean.getImagen()!= null) {
                    sql += "AND IMAGEN='" + bean.getImagen()+ "'";
                }
                if (bean.getGenero()!= null) {
                    sql += "AND GENERO='" + bean.getGenero()+ "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();

                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setDescripcion(rs.getString(3));
                pelicula.setNumVotos(rs.getInt(4));
                pelicula.setTrailer(rs.getString(5));
                pelicula.setImagen(rs.getString(6));
                pelicula.setGenero(rs.getString(7));

                peliculas.add(pelicula);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return peliculas;
    }

    @Override
    public int add(Pelicula bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getTitulo() + "', '"
                    + bean.getDescripcion()+ "', '"
                    + bean.getNumVotos()+ "', '"
                    + bean.getTrailer() + "', '"
                    + bean.getImagen()+ "', '"
                    + bean.getGenero() + "');";

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
    public int update(Pelicula bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getTitulo() != null) {
                    sql += "TITULO='" + bean.getTitulo() + "'";
                }

                if (bean.getDescripcion()!= null) {
                    sql += "DESCRIPCION='" + bean.getDescripcion()+ "'";
                }

                if (bean.getNumVotos()> 0) {
                    sql += "NUMVOTOS='" + bean.getNumVotos()+ "'";
                }

                if (bean.getTrailer() != null) {
                    sql += "TRAILER='" + bean.getTrailer() + "'";
                }

                if (bean.getImagen()!= null) {
                    sql += "IMAGEN='" + bean.getImagen() + "'";
                }

                if (bean.getGenero() != null) {
                    sql += "GENERO='" + bean.getGenero() + "'";
                }

                sql += " WHERE `idPelicula`=" + bean.getIdPelicula() + ";";
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
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        /*Pelicula pelicula = new Pelicula("G2", "La final se los llevo", 
                "trailer", "una url", "drama", 50, 1);
        peliculaDAO.add(pelicula);*/
        
        //Findall - filtra segun campos que no son null o 0
        ArrayList<Pelicula> lstPeliculas= new ArrayList();
        lstPeliculas = peliculaDAO.findAll(null);
        System.out.println(lstPeliculas.toString());
//
//        Pelicula peliprueba = new Pelicula("Joshua y los teleñecos", "www", "abc", "2015", 90, 5, 6, 9, 5.3, null);

//        //Add de registro
       // peliculaDAO.add(peliprueba);

//        //Update del registro con id pelicula 1
   //     peliculaDAO.update(new Pelicula("Titulo cambiado", null, null, null, 0, 0, 0, 1, null));

//        //Delete del registro 2
   //     peliculaDAO.delete(2);
    }
}
