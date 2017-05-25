package pe.edu.sise.proyectotesis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import pe.edu.sise.proyectotesis.ClassConstante;

import static pe.edu.sise.proyectotesis.ClassConstante.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFrag extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText txtUsuario ,txtContrasena;
    Button btnIngresar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFrag newInstance(String param1, String param2) {
        LoginFrag fragment = new LoginFrag();
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
        View v =inflater.inflate(R.layout.fragment_login, container, false);

        txtUsuario = (EditText) v.findViewById(R.id.txtUsuario);
        txtContrasena = (EditText) v.findViewById(R.id.txtContrasena);
        btnIngresar = (Button) v.findViewById(R.id.btnIngresar);

        TextView tvRecuperar = (TextView)v.findViewById(R.id.tvContraseña);
        /*Button botonLogin = (Button)v.findViewById(R.id.btnLogin);*/
        TextView tvRegistrar = (TextView)v.findViewById(R.id.tvRegistrar);

        tvRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecuperarContraFrag fragment = new RecuperarContraFrag();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });
        /*botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListadoProductoFrag fragment = new ListadoProductoFrag();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });*/
        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarFrag fragment = new RegistrarFrag();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });

        btnIngresar.setEnabled(true);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIngresar.setEnabled(false);
                ObtDatos();
            }
        });

        return v;
    }


    /*Autor : AMamani
    * Fecha : 29-04-2017
    *
    * */

    // Session Manager Class
    SessionManagement session;

    public void ObtDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            String val = txtUsuario.getText().toString().equals("") ? "" : "?user="+txtUsuario.getText().toString()+"&pass="+txtContrasena.getText().toString()+"";

            //String url = "http://10.0.2.2:8080/Android-Web/Login.php"+val;
            String URL_LOGIN = DOMINIO + V_LOGIN;
            RequestParams parametros = new RequestParams();
            parametros.put("user",txtUsuario.getText().toString());
            parametros.put("pass",txtContrasena.getText().toString());

            client.get(URL_LOGIN, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        txtContrasena.setText("");
                        btnIngresar.setEnabled(true);
                        Toast.makeText(getActivity(), "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                    }else if(statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-02")){
                        txtContrasena.setText("");
                        btnIngresar.setEnabled(true);
                        Toast.makeText(getActivity(), "Debe Llenar los datos.", Toast.LENGTH_SHORT).show();
                        btnIngresar.setEnabled(true);
                    }else if(statusCode == 200){
                        session.createLoginSession("Android Hive", "anroidhive@gmail.com");
                        txtUsuario.setText("");
                        txtContrasena.setText("");
                        txtUsuario.requestFocus();
                        btnIngresar.setEnabled(true);
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("valor",obtieneDatosJSON(new String(responseBody)).toString());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getActivity(), "onFail", Toast.LENGTH_SHORT).show();
                    btnIngresar.setEnabled(true);
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



    public String obtieneDatosJSON(String response){
        String texto="";
        try {
            JSONObject jsonArray2 = new JSONObject(response);
            texto = jsonArray2.getString("usuario");
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
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

    public void invocarListado(){
        ListadoProductoFrag fragment = new ListadoProductoFrag();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
    }

}
