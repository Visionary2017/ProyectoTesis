package pe.edu.sise.proyectotesis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private RecyclerView.Adapter adapter;

    private OnFragmentInteractionListener mListener;

    public CatalogoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogoFragment newInstance(String param1, String param2) {
        CatalogoFragment fragment = new CatalogoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vw = inflater.inflate(R.layout.fragment_catalogo, container, false);
/*
        ArrayList<Entidad_Categoria> categorias = null;

        recyclerView = (RecyclerView) v.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);

         //Layout Manager
            lManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(lManager);

        // Crear el adaptador
        adapter = new CategoriaAdapter(categorias);
        recyclerView.setAdapter(adapter);
*/
        ObtDatosCategoria(vw);

        return vw;
    }



    /*
    * AUTOR : AMAMANI
    * FECHA : 29-04-2017
    * */


    public void ObtDatosCategoria(final View vw){
        AsyncHttpClient client = new AsyncHttpClient();

        //String url = "http://10.0.2.2:8080/Android-Web/Entidad_Categoria.php";
        String url = "http://ranset2017.pe.hu/Entidad_Categoria.php";

        RequestParams parametros = new RequestParams();

        client.get(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200)
                {

                    ArrayList<Entidad_Categoria> list = new ArrayList<Entidad_Categoria>();
                    list = obtieneDatosJSONEmpleado(new String(responseBody));

                    recyclerView = (RecyclerView) vw.findViewById(R.id.reciclador);
                    recyclerView.setHasFixedSize(true);

                    //Layout Manager
                    lManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(lManager);

                    // Crear el adaptador
                    adapter = new CategoriaAdapter(list);
                    recyclerView.setAdapter(adapter);

                    /*for (Entidad_Categoria item: list) {
                        Log.i("Lista-Categoria",item.getDescripcion());
                    }
                    */
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getActivity(), "onFail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Entidad_Categoria> obtieneDatosJSONEmpleado(String response){
        Entidad_Categoria obj = new Entidad_Categoria();
        ArrayList<Entidad_Categoria> list = new ArrayList<Entidad_Categoria>();

        try {
            JSONArray jsonArray2 = new JSONArray(response);
            for(int i=0;i<jsonArray2.length();i++) {
                obj = new Entidad_Categoria();
                obj.setId_categoria(jsonArray2.getJSONObject(i).getInt("id_categoria"));
                obj.setDescripcion(jsonArray2.getJSONObject(i).getString("descripcion"));
                obj.setDescripcionGeneral(jsonArray2.getJSONObject(i).getString("descripcionGeneral"));
                obj.setIcon(jsonArray2.getJSONObject(i).getString("icon"));
                list.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**/




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
