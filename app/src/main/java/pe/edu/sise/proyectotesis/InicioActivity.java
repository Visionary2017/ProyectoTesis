package pe.edu.sise.proyectotesis;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.io.Console;

import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

public class InicioActivity extends AppCompatActivity {
    private SmallBang mSmallBang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mSmallBang = SmallBang.attach2Window(this);
    }

    public void startLogin(View view){

        mLogin(view);

    }

    public void startCatalogo(View view){

        mCatalogo(view);

    }

    public void mLogin(View view){
        mSmallBang.bang(view,new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("valor",1);
                startActivity(intent);

            }
        });
    }

    public void mCatalogo(View view){
        mSmallBang.bang(view,new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("valor",0);
                startActivity(intent);
            }
        });
    }



}
