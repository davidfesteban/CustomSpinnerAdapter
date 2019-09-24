
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * David Fdez Esteban
 * @param <T> Cualquier tipo de objeto.
 */
public abstract class CustomSpinnerAdapter<T> extends ArrayAdapter<T> {

    // Your sent context
    private final Context context;
    // Your custom values for the spinner (User)
    private final List<T> values;

    /**
     * @param context Contexto de la vista
     * @param resourceId  El id donde inflarlo
     * @param values  Los valores que deseas en el dropdown
     */
    public CustomSpinnerAdapter(Context context, int resourceId,
                                         List<T> values) {
        super(context, resourceId, values);
        //Se podría borrar el context del hijo y solo almacenar values.
        this.context = context;
        this.values = values;
    }

    /**
     * @param valores Listado con todos los valores que quieras añadir. Implementa un notifyDataSet
     * en caso de necesitar esperar. (Se podría hacer con un return de boolean).
     */
    public void addAll(List<T> valores) {
        boolean inserted = false;
        for (T valor : valores) {
            if (!values.contains(valor)) {
                inserted = true;
                values.add(valor);
            }
        }
        if (inserted) notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public T getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     *  Define el estado en que se vería el spinner en caso de estar modo pasivo (Sin hacerle click)
     *  
     *  No deberías necesitar invocar este método
     * @param position Para uso del SpinnerAdapter
     * @param convertView Para uso del SpinnerAdapter
     * @param parent Para uso del SpinnerAdapter
     * @return Devuelve la vista a dibujar al Spinner
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return setEstadoPasivoDelSpinner(inflater);
    }

    /**
     * Define el estado y el comportamiento del Dropdown
     */
    @Override
    public View getDropDownView(int position, View convertView,
                                @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return setEstadoDropdownDelSpinner(inflater);
    }

    /**
     * Permite implementar el comportamiento del getView sin necesidad de ser fijo.
     * Ejemplo del original:
     *         View row = inflater.inflate(R.layout.spinner_ps_item, parent, false);
     *         TextView textView = row.findViewById(R.id.texto);
     *         textView.setText(values.get(position).getTextoCalle());
     *
     * @param inflater  View result = inflater.inflate(R.layout.spinner_ps_item, parent, false);
     * @return  result
     */
    public abstract View setEstadoPasivoDelSpinner(LayoutInflater inflater);


    /**
     * Ejemplo del original:
     *         View row = inflater.inflate(R.layout.spinner_ps_dropdown_item, parent, false);
     *         TextView textView = row.findViewById(R.id.texto);
     *         textView.setText(values.get(position).getTextoCalle());
     *         textView.setMinWidth(Utils.dpToPx(getContext(), 150));
     *
     * @param inflater  View result = inflater.inflate(R.layout.spinner_ps_dropdown_item, parent, false);
     * @return  result
     */
    public abstract View setEstadoDropdownDelSpinner(LayoutInflater inflater);

}
