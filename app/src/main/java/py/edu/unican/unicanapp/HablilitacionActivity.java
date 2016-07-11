package py.edu.unican.unicanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import py.edu.unican.unicanapp.adapter.HabilitacionAdapter;
import py.edu.unican.unicanapp.interfaces.HabilitacionInterface;
import py.edu.unican.unicanapp.model.Alumno;
import py.edu.unican.unicanapp.response.HabilitacionResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HablilitacionActivity extends AppCompatActivity {
	
	private TextView userTextView;
	private Alumno alumno = null;
	private ListView listView;
	private HabilitacionAdapter adapter;
	private ProgressBar progressBar;
	private Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hablilitacion);
		userTextView = (TextView) findViewById(R.id.textUserName);
		listView = (ListView)findViewById(R.id.listViewHabilitaciones);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		toolbar = (Toolbar) findViewById(R.id.toolbarHabilitacion);
		toolbar.setTitle("Habitaciones");
		setSupportActionBar(toolbar);
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
		HabilitacionInterface service = restAdapter.create(HabilitacionInterface.class);
		Callback<HabilitacionResponse> callback = new Callback<HabilitacionResponse>() {

			@Override
			public void failure(RetrofitError error) {
				Log.e("RETROFIT", error.toString());
				Toast.makeText(HablilitacionActivity.this,
						"No se pudo conectar al Servidor", Toast.LENGTH_LONG)
						.show();
				listView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void success(HabilitacionResponse habilitacionResponse, Response response) {
				Log.d("RETROFIT", alumno.toString());
				adapter = new HabilitacionAdapter(getApplicationContext(), habilitacionResponse.value);
			    listView.setAdapter(adapter);
			    listView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}
		};
		service.getHabilitaciones(alumno.getCodigo(),callback);
	}
}
