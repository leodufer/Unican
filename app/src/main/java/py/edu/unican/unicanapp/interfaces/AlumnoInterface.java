package py.edu.unican.unicanapp.interfaces;

import py.edu.unican.unicanapp.model.Alumno;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface AlumnoInterface {
	
	@GET("/service?cedula=1111&password=111")
	void loginAlumno(@Query("cedula") String cedula,
					 @Query("password") String password,
					 Callback<Alumno> callback);
}
