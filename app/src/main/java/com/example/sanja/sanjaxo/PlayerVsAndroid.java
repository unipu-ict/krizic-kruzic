package com.example.sanja.sanjaxo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Korisnik on 12.6.2016..
 */
public class PlayerVsAndroid extends Activity{

    KrizicKruzicPloca Ploca = new KrizicKruzicPloca();

    Button Btn[][];
    boolean kraj = false;

    private TextView Info;
    private TextView countIgrac;
    private TextView countIzjednaceno;
    private TextView countAndroid;

    private int counterIgrac = 0;
    private int counterIzjednaceno = 0;
    private int counterAndroid = 0;

    private static final char Covjek = 'O';
    private static final char Stroj = 'X';
    private static final char Prazno_polje = '_';

    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_vs_android);


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

        Info = (TextView) findViewById(R.id.info);
        countIgrac = (TextView) findViewById(R.id.counterIgrac);
        countIzjednaceno = (TextView) findViewById(R.id.counterIzjednaceno);
        countAndroid = (TextView) findViewById(R.id.counterAndroid);

        countIgrac.setText(Integer.toString(counterIgrac));
        countIzjednaceno.setText(Integer.toString(counterIzjednaceno));
        countAndroid.setText(Integer.toString(counterAndroid));

        Toast.makeText(getApplicationContext(), "Klikni za start!", Toast.LENGTH_SHORT).show();

        start();
    }




    private class Klik implements View.OnClickListener {
        int R;
        int K;

        public Klik(int r, int k) {
            R = r;
            K = k;
        }

        public void onClick(View view) {
            if (!kraj) {
                if (Btn[R][K].isEnabled()) {

                    Btn[R][K].setEnabled(false);
                    Btn[R][K].setText(String.valueOf(Covjek));

                    if (Ploca.trenIgrac() == 'O') Btn[R][K].setTextColor(Color.BLUE);
                    else Btn[R][K].setTextColor(Color.GREEN);

                    Ploca.postavi(R, K, Covjek);
                    Ploca.izmjeniIgraca();
                    provjeraPobjednika();

                }
            }
        }
    }


    public void provjeraPobjednika() {

        switch (Ploca.provjeraPobjednika()) {
            case '_':
                potezStroja();
                break;
            case 'X':
                Info.setText("Pobijedio je stroj");
                counterAndroid++;
                countAndroid.setText(Integer.toString(counterAndroid));
                kraj = true;
                break;
            case 'O':
                Info.setText("Pobijedio je covjek");
                counterIgrac++;
                countIgrac.setText(Integer.toString(counterIgrac));
                kraj = true;
                break;
            default:
                Info.setText("Izjednaceno");
                counterIzjednaceno++;
                countIzjednaceno.setText(Integer.toString(counterIzjednaceno));
                kraj = true;
                break;
        }
    }

    public void potezStroja() {

        //prvo provjera dali android moze pobijediti
        for (int i = 0; i < Ploca.mRed; i++) {
            for (int j = 0; j < Ploca.mKolona; j++) {

                //provjera dali je slobodno mjesto na ovome polju
                if (Ploca.vratiIgraca(i, j) == Prazno_polje) {

                    //trenutno ili za stalno se postavlja vrijednost stroja na prazno polje
                    Ploca.postavi(i, j, Stroj);

                    //ukoliko stavljanjem na to mjesto dolazi do  pobjede stroja
                    if (Ploca.provjeraPobjednika() == Stroj) {

                        Btn[i][j].setEnabled(false);
                        Btn[i][j].setText(String.valueOf(Stroj));
                        Btn[i][j].setTextColor(Color.GREEN);
                        provjeraPobjednika();
                        return;

                    } else Ploca.postavi(i, j, Prazno_polje);
                }
            }
        }

        // Provjera ako moze covjek pobijediti pa ce mu stroj zatvoriti polje
        for (int i = 0; i < Ploca.mRed; i++) {
            for (int j = 0; j < Ploca.mKolona; j++) {

                //provjera dali je slobodno mjesto, ukoliko je, slijedi provjera dali postoji mogućnost da covjek pobijedi
                if (Ploca.vratiIgraca(i, j) == Prazno_polje) {

                    //trenutno se postavlja vrijednost covjeka na prazno polje
                    Ploca.postavi(i, j, Covjek);

                    // ukoliko je na ovome polju mogućnost da čovjek pobijedi, na to mjesto se zatim postavlja vrijednost stroja
                    if (Ploca.provjeraPobjednika() == Covjek) {

                        Ploca.postavi(i, j, Stroj);
                        Ploca.izmjeniIgraca();
                        Btn[i][j].setEnabled(false);
                        Btn[i][j].setText(String.valueOf(Stroj));
                        Btn[i][j].setTextColor(Color.GREEN);
                        return;

                    } else Ploca.postavi(i, j, Prazno_polje);
                }
            }
        }

        int potezRed, potezKolona;

        //ne moze se pobijediti pa stroj stavlja potez na slučajno odabranu poziciju
        potezRed = mRandom.nextInt(Ploca.mRed);
        potezKolona = mRandom.nextInt(Ploca.mKolona);

        // vrti while petlju dok ne bude odabrano polje koje je prazno,
        while (Ploca.vratiIgraca(potezRed, potezKolona) != Prazno_polje) {
            potezRed = mRandom.nextInt(Ploca.mRed);
            potezKolona = mRandom.nextInt(Ploca.mKolona);
        }

        //  postavlja se X na neku slučajno dodjeljenu poziciju i mjesto koje je prazno
        Ploca.postavi(potezRed, potezKolona, Stroj);
        Ploca.izmjeniIgraca();
        Btn[potezRed][potezKolona].setEnabled(false);
        Btn[potezRed][potezKolona].setText(String.valueOf(Stroj));
        Btn[potezRed][potezKolona].setTextColor(Color.GREEN);

        //Provjera dali je bio zadnji potez u igri, ako je idi na provjeru pobjednika
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Ploca.vratiIgraca(i, j) == Prazno_polje) counter++;
            }
        }
        if (counter == 0) provjeraPobjednika();

    }

    public void start() {
        for (int i = 0; i < Ploca.mRed; i++) {
            for (int j = 0; j < Ploca.mKolona; j++) {
                Btn[i][j].setEnabled(true);
                Btn[i][j].setOnClickListener(new Klik(i, j));
            }
        }

        Ploca.ocistiPlocu();        //postavljenje ploče
        if (Ploca.trenIgrac() == 'X') potezStroja();


    }


        public void goBack(View view){

        Intent GoBack = new Intent();
        setResult(RESULT_OK, GoBack);
        finish();
    }




}
