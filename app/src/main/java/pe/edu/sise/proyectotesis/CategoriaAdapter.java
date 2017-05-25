package pe.edu.sise.proyectotesis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Angel Mamani on 6/05/2017.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    ArrayList<Entidad_Categoria> items;

    public Context thiscontext;

    public CategoriaAdapter(ArrayList<Entidad_Categoria> items){
        this.items = items;
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView descripcion;
        public TextView descripcionGeneral;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.vcImagen);
            descripcion = (TextView) itemView.findViewById(R.id.vcNombre);
            descripcionGeneral = (TextView) itemView.findViewById(R.id.vcDescripcion);
        }
    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        thiscontext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.catalago_card, parent, false );
        return new CategoriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        Entidad_Categoria categoria = items.get(position);


/*
        URL imageUrl = null;
        HttpURLConnection conn = null;

        try {
            String valor = "http://ranset2017.pe.hu/Imagenes/Categoria/"+categoria.getIcon()+".png";
            imageUrl = new URL("http://ranset2017.pe.hu/Imagenes/Categoria/"+categoria.getIcon()+".png");
            Log.i("imagen -",valor);
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

            Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            holder.icon.setImageBitmap(imagen);

        } catch (IOException e) {

            e.printStackTrace();

        }*/

        String url = "http://ranset2017.pe.hu/Imagenes/Categoria/"+categoria.getIcon()+".png";

        Picasso.with(thiscontext)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.icon);

        //holder.icon.setImageBitmap(obtener_imagen);
        /*Log.i("imagen -",categoria.getIcon());
        holder.icon.setImageResource(R.drawable.goku);*/
        holder.descripcion.setText("" + categoria.getDescripcion());
        holder.descripcionGeneral.setText(categoria.getDescripcionGeneral());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

