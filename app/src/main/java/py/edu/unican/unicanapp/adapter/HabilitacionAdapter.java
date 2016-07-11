package py.edu.unican.unicanapp.adapter;

import java.util.List;

import py.edu.unican.unicanapp.R;
import py.edu.unican.unicanapp.model.Habilitacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HabilitacionAdapter extends BaseAdapter {

	protected Context context;
	protected List<Habilitacion> items;
	
	public HabilitacionAdapter(Context context, List<Habilitacion> items){
		this.context = context;
		this.items = items;
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
		 View v = convertView;
	        if(convertView == null){
	            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = inf.inflate(R.layout.item_habilitacion, null);
	        }
		Habilitacion h = items.get(position);
		TextView asignatura = (TextView)v.findViewById(R.id.textViewAsignatura);
		TextView vencimiento = (TextView)v.findViewById(R.id.textViewVencimiento);
		TextView promedio = (TextView)v.findViewById(R.id.textViewPromedio);
		asignatura.setText(h.getAsignatura());
		vencimiento.setText(h.getVencimiento());
		promedio.setText(h.getPromedio()+"%");
		return v;
	}
	
}
