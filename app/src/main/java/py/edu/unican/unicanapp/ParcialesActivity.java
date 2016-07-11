package py.edu.unican.unicanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import py.edu.unican.unicanapp.adapter.ParcialAdapter;
import py.edu.unican.unicanapp.interfaces.ParcialInterface;
import py.edu.unican.unicanapp.model.Alumno;
import py.edu.unican.unicanapp.response.ParcialResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ParcialesActivity extends AppCompatActivity {
	
	private TextView userTextView;
	private Alumno alumno = null;
	private ListView listView;
	private ParcialAdapter adapter;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parciales);
		userTextView = (TextView) findViewById(R.id.textUserName);
		listView = (ListView)findViewById(R.id.listViewParciales);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		listView.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			alumno = new Alumno();
			alumno.setNombre(extras.getString("nombre"));
			alumno.setCedula(extras.getString("cedula"));
			alumno.setCodigo(extras.getString("codigo"));
			userTextView.setText(alumno.getNombre());
		}
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.endpoint)).build();
		ParcialInterface service = restAdapter.create(ParcialInterface.class);
		Callback<ParcialResponse> callback = new Callback<ParcialResponse>() {

			@Override
			public void failure(RetrofitError error) {
				Log.e("RETROFIT", error.toString());
				Toast.makeText(getApplicationContext(),
						"No se pudo conectar al Servidor", Toast.LENGTH_LONG)
						.show();
				listView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void success(ParcialResponse parcialResponse, Response response) {
				Log.d("RETROFIT", alumno.toString());
				adapter = new ParcialAdapter(getApplicationContext(), parcialResponse.value);
			    listView.setAdapter(adapter);
			    listView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}
		};
		service.getParciales(alumno.getCodigo(), callback);
	}
}
