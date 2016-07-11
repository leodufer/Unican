package py.edu.unican.unicanapp.model;

/**
 * Created by virux on 09/07/16.
 */
public class Asistencia {
    String alumno;
    String asignatura;
    String teorica;
    String practica;

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getTeorica() {
        return teorica;
    }

    public void setTeorica(String teorica) {
        this.teorica = teorica;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }
}
