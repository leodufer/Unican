package py.edu.unican.unicanapp.interfaces;

import py.edu.unican.unicanapp.response.ParcialResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ParcialInterface {
	@GET("/service/parciales?codigo=1111")
	void getParciales(@Query("codigo") String codigo, Callback<ParcialResponse> callback);
}
