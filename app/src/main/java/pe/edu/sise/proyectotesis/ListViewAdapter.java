package pe.edu.sise.proyectotesis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JEAN on 24/03/2017.
 */

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<Item> arraListItem;
    private Context context;
    private LayoutInflater layoutInflater;


    public ListViewAdapter(ArrayList<Item> arraListItem, Context context) {
        this.arraListItem = arraListItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraListItem.size();
    }

    @Override
    public Object getItem(int i) {
        return arraListItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vistaItem = layoutInflater.inflate(R.layout.layout_item,viewGroup,false);
        ImageView ivImagen =(ImageView) vistaItem.findViewById(R.id.ivImagen);
        TextView tvTitulo = (TextView)vistaItem.findViewById(R.id.tvTitulo);
        TextView tvContenido = (TextView)vistaItem.findViewById(R.id.tvContenido);

        ivImagen.setImageResource(arraListItem.get(i).getImagen());
        tvTitulo.setText(arraListItem.get(i).getTitulo());
        tvContenido.setText(arraListItem.get(i).getContenido());

        return vistaItem;
    }
}
