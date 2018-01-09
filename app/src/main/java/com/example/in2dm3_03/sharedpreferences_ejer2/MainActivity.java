package com.example.in2dm3_03.sharedpreferences_ejer2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private TextView mNumero, mHighScore;
    private int valor, highscore;

    private static final String NUMEROALTO="numAlto";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs=getSharedPreferences(NUMEROALTO, Context.MODE_PRIVATE);

        mNumero=(TextView)findViewById(R.id.textViewNumero);
        mHighScore=(TextView)findViewById(R.id.textViewHighScore);

        mHighScore.setText(prefs.getString(NUMEROALTO,""));

    }

    public void Play(View v) {

        valor = (int) (Math.random() * 500);
        String resultado = String.valueOf(valor);
        mNumero.setText(resultado);

        if (highscore<valor) {
                highscore = valor;
        }

        String hs=String.valueOf(highscore);

        mHighScore.setText(hs);



    }

    public void Reset(View v){
        valor=0;
        String resultado=String.valueOf(valor);
        mNumero.setText(resultado);
    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs=getSharedPreferences(NUMEROALTO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        //salvar los valores en la vista Edittext a preferencias
        editor.putString(NUMEROALTO,mHighScore.getText().toString());

        //salvar los valores
        editor.commit();

    }
}
