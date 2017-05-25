package pe.edu.sise.proyectotesis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LoginFrag.OnFragmentInteractionListener,RegistrarFrag.OnFragmentInteractionListener,
        ListadoProductoFrag.OnFragmentInteractionListener,RecuperarContraFrag.OnFragmentInteractionListener,PerfilFragment.OnFragmentInteractionListener,CatalogoFragment.OnFragmentInteractionListener{

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new SessionManagement(getApplicationContext());

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_NAME);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(name != ""){
            Intent intent = getIntent();
            Bundle dato = intent.getExtras();
            String valor = dato.getString("valor");
            if(valor == null || valor == ""){
                changeFragment(new CatalogoFragment());
            }else {
                ObtDatosEmpleado(valor);
            }
        }
        /*Intent  intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle.get("valor") == 1) {

        }*/

    }

    /*
    * AUTOR : AMAMANI
    * FECHA : 29-04-2017
    * */


    public void ObtDatosEmpleado(String val){
        AsyncHttpClient client = new AsyncHttpClient();

        //String url = "http://10.0.2.2:8080/Android-Web/Info_Empleado.php?filtro="+val;
        String url = "http://ranset2017.pe.hu/Info_Empleado.php?filtro="+val;

        RequestParams parametros = new RequestParams();

        client.get(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200)
                {
                    Entidad_Empleado obj = new Entidad_Empleado();
                    obj = obtieneDatosJSONEmpleado(new String(responseBody));
                    Toast.makeText(MainActivity.this, "Bienvenido " + obj.getNombres() + ", " + obj.getApellidos() , Toast.LENGTH_SHORT).show();
                    TextView nombre = (TextView) findViewById(R.id.nombre_user);
                    nombre.setText(obj.getNombres() /*+ ", " + obj.getApellidos()*/);
                    TextView email = (TextView) findViewById(R.id.email_user);
                    email.setText(obj.getDni());
                    changeFragment(new CatalogoFragment());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "onFail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Entidad_Empleado obtieneDatosJSONEmpleado(String response){
        Entidad_Empleado obj = new Entidad_Empleado();
        try {
            JSONArray jsonArray2 = new JSONArray(response);
            for(int i=0;i<jsonArray2.length();i++) {
                obj.setid_persona(jsonArray2.getJSONObject(i).getString("id_persona"));
                obj.setApellidos(jsonArray2.getJSONObject(i).getString("apellidos"));
                obj.setNombres(jsonArray2.getJSONObject(i).getString("nombres"));
                obj.setDireccion(jsonArray2.getJSONObject(i).getString("direccion"));
                obj.setDni(jsonArray2.getJSONObject(i).getString("dni"));
                obj.setTelefono(jsonArray2.getJSONObject(i).getString("telefono"));
                obj.setSexo(jsonArray2.getJSONObject(i).getString("sexo"));
                obj.setEstado(jsonArray2.getJSONObject(i).getString("estado"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    /**/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragTrans = false;

        //noinspection SimplifiableIfStatement

        /*if (id == R.id.action_categoria) {
=======
        if (id == R.id.action_categoria) {
            return true;
        }else if(id == R.id.action_login){
            return true;
        }else if(id == R.id.action_close){
>>>>>>> origin/master
            return true;
        }else*/ if(id == R.id.action_login){
            fragment = new LoginFrag();
            fragTrans = true;
            //return true;
        }else if(id == R.id.action_close){
            Intent intent = new Intent(getApplicationContext(),InicioActivity.class);
            startActivity(intent);
            //return true;
        }

        if(fragTrans){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main,fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        //return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment fragment){
        boolean fragTrans = false;
        Fragment fragmentGeneral = null;
        fragmentGeneral = fragment;
        fragTrans = true;

        if(fragTrans){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main,fragmentGeneral).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        boolean fragTrans = false;

        if (id == R.id.nav_categoria) {

                fragment = new CatalogoFragment();
                fragTrans = true;
        } /*else if (id == R.id.nav_login) {
                fragment = new LoginFrag();
                fragTrans = true;
        }*/ else if (id == R.id.nav_close) {
                Intent intent = new Intent(getApplicationContext(),InicioActivity.class);
                startActivity(intent);
        } else if (id == R.id.nav_perfil) {
                fragment = new PerfilFragment();
                fragTrans = true;
        } /*else if (id == R.id.nav_share) {
=======
                fragment = new LoginFrag();
                fragTrans = true;
        } else if (id == R.id.nav_login) {
                fragment = new LoginFrag();
                fragTrans = true;
        } else if (id == R.id.nav_close) {
                Intent intent = new Intent(getApplicationContext(),InicioActivity.class);
                startActivity(intent);
        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
>>>>>>> origin/master

        } else if (id == R.id.nav_send) {

        }*/

        if(fragTrans){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main,fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
