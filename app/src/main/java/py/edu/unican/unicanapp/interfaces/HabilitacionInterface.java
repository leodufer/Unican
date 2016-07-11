package py.edu.unican.unicanapp.interfaces;

import py.edu.unican.unicanapp.response.HabilitacionResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface HabilitacionInterface {

	@GET("/service/habilitaciones?codigo=00000")
	void getHabilitaciones(@Query("codigo") String codigo, Callback<HabilitacionResponse> callback);
}
