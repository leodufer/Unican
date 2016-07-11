package py.edu.unican.unicanapp.adapter;

import java.util.List;

import py.edu.unican.unicanapp.model.Nota;
import py.edu.unican.unicanapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotaAdapter extends BaseAdapter {

	protected Context context;
    protected List<Nota> items;
    
    
    public NotaAdapter(Context  context, List<Nota> items){
    	this.context = context;
    	this.items=items;
    }
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Generamos una convertView por motivos de eficiencia
        View v = convertView;
 
        //Asociamos el layout de la lista que hemos creado
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_nota, null);
        }
        // Creamos un objeto
        Nota n = items.get(position);
        //Rellenamos los datos
        TextView materia = (TextView) v.findViewById(R.id.textViewMateria);
        materia.setText(n.getMateria());
        //Rellenamos el cargo
        TextView nota = (TextView) v.findViewById(R.id.textViewNota);
        nota.setText(n.getNota()+"("+getNotaString(n)+")");
        TextView evaluacion = (TextView) v.findViewById(R.id.textViewFinal);
        evaluacion.setText(n.getEvaluacion());
		return v;
	}
	
	private String getNotaString(Nota nota){
		String[] nota_valor = {"cero","uno","dos","tres","cuatro","cinco"};
		return nota_valor[Integer.parseInt(nota.getNota())];
	
	}

}
