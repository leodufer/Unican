package py.edu.unican.unicanapp.db;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.unican.unicanapp.model.Alumno;

/**
 * Created by virux on 14/04/16.
 */
public class AlumnoDAO {

    public AlumnoDAO(Context context){
        DBA.init(context);
    }

    public void guardar(Alumno u){
        try {
            DBA.getUsuarioDAO().create(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Alumno> obtenerTodos(){
        try {
           return DBA.getUsuarioDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void eliminar(int id){
        try {
            DBA.getUsuarioDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
