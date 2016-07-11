package py.edu.unican.unicanapp;

import py.edu.unican.unicanapp.db.AlumnoDAO;
import py.edu.unican.unicanapp.interfaces.AlumnoInterface;
import py.edu.unican.unicanapp.model.Alumno;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	
	private EditText usuarioEditText;
	private EditText passwordEditText;
	private Button okButton;
	private ProgressBar progressBar;
	private Alumno a = null;
	AlumnoDAO alumnoDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		alumnoDAO = new AlumnoDAO(this);

		if(alumnoDAO.obtenerTodos().size()>0){
			a = alumnoDAO.obtenerTodos().get(0);
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			intent.putExtra("nombre", a.getNombre());
			intent.putExtra("cedula", a.getCedula());
			intent.putExtra("codigo", a.getCodigo());
			startActivity(intent);
			finish();
		}else{
			setContentView(R.layout.activity_main);
			usuarioEditText = (EditText)findViewById(R.id.editText1);
			passwordEditText = (EditText)findViewById(R.id.editText2);
			progressBar = (ProgressBar)findViewById(R.id.progressBar1);
			okButton = (Button)findViewById(R.id.button1);
			okButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					loginUsuario();
				}
			});
		}
	}
	
	private void loginUsuario(){
		String userCedu = usuarioEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		
		usuarioEditText.setVisibility(View.GONE);
		passwordEditText.setVisibility(View.GONE);
		okButton.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.endpoint)).build();
		AlumnoInterface service = restAdapter.create(AlumnoInterface.class);
		Callback<Alumno> callback = new Callback<Alumno>() {

			@Override
			public void failure(RetrofitError error) {
				Log.e("RETROFIT", error.toString());
				Toast.makeText(getApplicationContext(),
						"No se pudo conectar al Servidor", Toast.LENGTH_LONG)
						.show();  
				usuarioEditText.setVisibility(View.VISIBLE);
				passwordEditText.setVisibility(View.VISIBLE);
				okButton.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void success(Alumno alumno, Response response) {
				Log.d("RETROFIT", alumno.toString());
				a = alumno;
				if(a!=null&&!a.getCodigo().trim().equals("")){
					Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
					intent.putExtra("nombre", a.getNombre());
					intent.putExtra("cedula", a.getCedula());
					intent.putExtra("codigo", a.getCodigo());
					startActivity(intent);
					finish();
					alumnoDAO.guardar(a);
				}else{
					Toast.makeText(getApplicationContext(),
							"Error de Autenticacion",
							Toast.LENGTH_SHORT)
							.show();
					usuarioEditText.setVisibility(View.VISIBLE);
					passwordEditText.setVisibility(View.VISIBLE);
					okButton.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
				}
			}
		};
		service.loginAlumno(userCedu, password, callback);	
	}

}