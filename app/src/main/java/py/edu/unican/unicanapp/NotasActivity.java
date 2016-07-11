package py.edu.unican.unicanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import py.edu.unican.unicanapp.adapter.NotaAdapter;
import py.edu.unican.unicanapp.interfaces.NotaInterface;
import py.edu.unican.unicanapp.model.Alumno;
import py.edu.unican.unicanapp.model.Nota;
import py.edu.unican.unicanapp.response.NotaResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NotasActivity extends AppCompatActivity {
	private TextView userTextView;
	private Alumno alumno = null;
	private ListView listView;
	private NotaAdapter adapter;
	private ProgressBar progressBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota);
		userTextView = (TextView) findViewById(R.id.textUserName);
		listView = (ListView)findViewById(R.id.listViewNotas2);
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
		NotaInterface service = restAdapter.create(NotaInterface.class);
		Callback<NotaResponse> callback = new Callback<NotaResponse>() {

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
			public void success(NotaResponse notaResponse, Response response) {
				Log.d("RETROFIT", alumno.toString());
				adapter = new NotaAdapter(getApplicationContext(), notaResponse.value);
			    listView.setAdapter(adapter);
			    listView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}
		};
		service.getNotas(alumno.getCodigo(), callback);
	}
	
	
	@SuppressWarnings("unused")
	private String calcularPromedio(List<Nota> notas){
		double promedio = 0;
		for(Nota n:notas){
			promedio = promedio + Integer.parseInt(n.getNota());
		}
		promedio = promedio/notas.size();
		return new DecimalFormat("#0.00").format(promedio);
	}
}
