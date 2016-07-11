package py.edu.unican.unicanapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import py.edu.unican.unicanapp.adapter.AsistenciaAdapter;
import py.edu.unican.unicanapp.interfaces.AsistenciaInterface;
import py.edu.unican.unicanapp.model.Alumno;
import py.edu.unican.unicanapp.model.Asistencia;
import py.edu.unican.unicanapp.response.AsistenciaResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AsistenciaActivity extends AppCompatActivity {

    Alumno alumno;
    TextView userTextView;
    ListView asistenciaListView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        userTextView = (TextView)findViewById(R.id.textUserName);
        asistenciaListView = (ListView)findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            alumno = new Alumno();
            alumno.setNombre(extras.getString("nombre"));
            alumno.setCedula(extras.getString("cedula"));
            alumno.setCodigo(extras.getString("codigo"));
            userTextView.setText(alumno.getNombre());
        }

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.endpoint)).build();
        AsistenciaInterface service = restAdapter.create(AsistenciaInterface.class);

        service.getAsistencia(alumno.getCodigo(), new Callback<AsistenciaResponse>() {
            @Override
            public void success(AsistenciaResponse asistenciaResponse, Response response) {
                AsistenciaAdapter adapter = new AsistenciaAdapter(asistenciaResponse.value,AsistenciaActivity.this);
                progressBar.setVisibility(View.GONE);
                asistenciaListView.setAdapter(adapter);
                asistenciaListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(),"No se pudo conectar con el servidor",Toast.LENGTH_LONG).show();
            }
        });
    }

}
