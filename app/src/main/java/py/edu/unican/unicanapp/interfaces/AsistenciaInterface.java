package py.edu.unican.unicanapp.interfaces;

import py.edu.unican.unicanapp.response.AsistenciaResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by virux on 09/07/16.
 */
public interface AsistenciaInterface {
    @GET("/service/asistencia?codigo=00000")
    void getAsistencia(@Query("codigo") String codigo, Callback<AsistenciaResponse> callback);
}
