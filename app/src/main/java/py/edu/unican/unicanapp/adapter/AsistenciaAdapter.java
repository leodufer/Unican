package py.edu.unican.unicanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import py.edu.unican.unicanapp.R;
import py.edu.unican.unicanapp.model.Asistencia;

/**
 * Created by virux on 10/07/16.
 */
public class AsistenciaAdapter extends BaseAdapter {

    private List<Asistencia> asistencias;
    private Context context;

    public AsistenciaAdapter(List<Asistencia> asistencias, Context context) {
        this.asistencias = asistencias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return asistencias.size();
    }

    @Override
    public Object getItem(int position) {
        return asistencias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.item_asistencia, null);
        }

        Asistencia asistencia = asistencias.get(position);

        TextView asignaturaTextView = (TextView) view.findViewById(R.id.textViewAsignatura);
        TextView asistenciaPorcentajeTextView = (TextView)view.findViewById(R.id.textViewAsistenciaPorcentaje);

        asignaturaTextView.setText(asistencia.getAsignatura());
        asistenciaPorcentajeTextView.setText(asistencia.getTeorica()+"%");

        return view;
    }
}
