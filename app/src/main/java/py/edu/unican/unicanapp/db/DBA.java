package py.edu.unican.unicanapp.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oss.datahelper.DataBaseManager;

import java.sql.SQLException;

import py.edu.unican.unicanapp.model.Alumno;

/**
 * Created by virux on 14/04/16.
 */
public class DBA {

    private static final String DB_NAME = "unican.sqlite";
    private static final int DB_VERSION = 1;

    public static void init(Context context){
        DataBaseManager.init(context,DB_NAME,DB_VERSION);

        ConnectionSource source = DataBaseManager.getInstance().getHelper().getConnectionSource();


            try {
                TableUtils.createTableIfNotExists(source,Alumno.class);

                //Demas tables
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    public static Dao<Alumno, Integer> getUsuarioDAO(){
        try {
            return DataBaseManager.getInstance().getHelper().getDao(Alumno.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearTableAlumno(){
        ConnectionSource source = DataBaseManager.getInstance().getHelper().getConnectionSource();
        try {
            TableUtils.clearTable(source,Alumno.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}