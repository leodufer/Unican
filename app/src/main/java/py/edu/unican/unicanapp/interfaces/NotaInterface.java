package py.edu.unican.unicanapp.interfaces;

import py.edu.unican.unicanapp.response.NotaResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NotaInterface {
	@GET("/service/notas?codigo=1111")
	void getNotas(@Query("codigo") String codigo, Callback<NotaResponse> callback);
}
