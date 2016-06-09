package com.example.sanja.sanjaxo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivityXO extends AppCompatActivity {

    KrizicKruzicPloca Ploca = new KrizicKruzicPloca();

    Button Btn[][];
    boolean kraj = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_xo);

        Ploca.ocistiPlocu();        //postavljenje ploče

        Btn = new Button[Ploca.mRed][Ploca.mKolona];
        Btn[0][0] = (Button) findViewById(R.id.button1);
        Btn[0][1] = (Button) findViewById(R.id.button2);
        Btn[0][2] = (Button) findViewById(R.id.button3);
        Btn[1][0] = (Button) findViewById(R.id.button4);
        Btn[1][1] = (Button) findViewById(R.id.button5);
        Btn[1][2] = (Button) findViewById(R.id.button6);
        Btn[2][0] = (Button) findViewById(R.id.button7);
        Btn[2][1] = (Button) findViewById(R.id.button8);
        Btn[2][2] = (Button) findViewById(R.id.button9);

        Toast.makeText(getApplicationContext(), "Klikni za start!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("Nova igra");
        return true;
    }
}


