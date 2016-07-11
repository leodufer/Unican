package py.edu.unican.unicanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import py.edu.unican.unicanapp.db.DBA;
import py.edu.unican.unicanapp.model.Alumno;

public class HomeActivity extends AppCompatActivity {
	private TextView userTextView;
	private Alumno alumno = null;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private List<String> list = new ArrayList<String>();

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		userTextView = (TextView) findViewById(R.id.textUserName);
		listView = (ListView)findViewById(R.id.listViewOpciones);

		toolbar = (Toolbar)findViewById(R.id.toolbarHome);
		toolbar.setSubtitle("Consultor Académico");

		setSupportActionBar(toolbar);


		list.add("Habilitaciones");
		list.add("Asistencia");
		list.add("Parciales");
	    list.add("Notas Finales");
	    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, list);
	    listView.setAdapter(adapter);

	    Bundle extras = getIntent().getExtras();
		if(extras!=null){
			alumno = new Alumno();
			alumno.setNombre(extras.getString("nombre"));
			alumno.setCedula(extras.getString("cedula"));
			alumno.setCodigo(extras.getString("codigo"));
			userTextView.setText(alumno.getNombre());
		}
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Intent intent = null;
				switch (position) {
				case 0:
					intent = new Intent(getApplicationContext(), HablilitacionActivity.class);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), AsistenciaActivity.class);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ParcialesActivity.class);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), NotasActivity.class);
					break;
				}
				if(intent!=null){
				intent.putExtra("nombre", alumno.getNombre());
				intent.putExtra("cedula", alumno.getCedula());
				intent.putExtra("codigo", alumno.getCodigo());
				startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "No disponible", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_logout) {
			DBA.clearTableAlumno();
			Intent intent = new Intent(HomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
