package com.example.sanja.sanjaxo;
import static java.lang.System.*;

import java.util.Random;


public class KrizicKruzicPloca {

    //******** varijable klase ********
    public final static int mRed  = 3;
    public final static int mKolona  = 3;

    private static final char Covjek = 'O';  //ovdje mogu napraviti da prilikom pocinjanja igre, igrac bira koji znak ce biti
    private static final char Stroj = 'X';
    private static final char Prazno_polje = '_';

    //Postavlja se random igrac koji pocinje igru
    private Random mRandom = new Random();
    private char trenutniIgrac = mRandom.nextBoolean() ? 'X' : 'O';

    // ploca za igranje
    private char[][] mPloca = new char[mRed][mKolona];
    //*************************************


    //********** funkcije klase *********
    public KrizicKruzicPloca(){
    }

    public char trenIgrac(){
        return trenutniIgrac;
    }

    public void ocistiPlocu(){
        for(int i=0; i<mRed; i++){
            for(int j=0; j<mKolona; j++){
                mPloca[i][j] = Prazno_polje;
            }
        }
    }

    public void izmjeniIgraca(){
        if (trenutniIgrac == Covjek) trenutniIgrac = Stroj;
        else trenutniIgrac = Covjek;
    }

    public void postavi(int red, int kolona, char igrac){
        mPloca[red][kolona] = igrac;
        izmjeniIgraca();
    }

    public char potezStroja(){

        int potezRed, potezKolona;

        //prvo provjera dali android moze pobijediti
        for(int i = 0; i < mRed; i++){
            for( int j = 0; j < mKolona; j++){

                //provjera dali je slobodno mjesto
                if(mPloca[i][j] == Prazno_polje){

                    postavi(i,j,Stroj);  //trenutno ili za stalno se postavlja vrijednost stroja na polje

                    if(provjeraPobjednika() == Stroj){
                        return Stroj;
                    }
                    else postavi(i,j,Prazno_polje);
                }
            }
        }

        // Provjera ako moze covjek pobijediti pa ce mu stroj zatvoriti polje
        for(int i = 0; i < mRed; i++){
            for( int j = 0; j < mKolona; j++){

                //provjera dali je slobodno mjesto
                if(mPloca[i][j] == Prazno_polje){

                    postavi(i,j, Covjek);  //trenutno ili za stalno se postavlja vrijednost stroja na polje

                    if(provjeraPobjednika() == Covjek){
                        return Covjek;
                    }

                    else postavi(i,j,Prazno_polje);
                }
            }
        }

        //ne moze se pobijediti pa stroj stavlja potez na random mjesto
        potezRed = mRandom.nextInt(mRed);
        potezKolona = mRandom.nextInt(mKolona);

        while(mPloca[potezRed][potezKolona] != Prazno_polje){
            potezRed = mRandom.nextInt(mRed);
            potezKolona = mRandom.nextInt(mKolona);
        }
        // Trebam provjeru dali je ploca ispunjena

        postavi(potezRed, potezKolona, Stroj);
       return mPloca[potezRed][potezKolona];

    }

    public char provjeraPobjednika(){

        char winner = Prazno_polje;

        //provjera red po red
        for(int i = 0; i < mRed; i++){
            if(winner == Prazno_polje && mPloca[i][0] ==  mPloca[i][1] &&  mPloca[i][2] ==  mPloca[i][2]) winner = mPloca[i][0];
        }

        //provjera kolona po kolona
        for(int i = 0; i < mKolona; i++){
            if(winner == Prazno_polje && mPloca[0][i] ==  mPloca[1][i] &&  mPloca[1][i] ==  mPloca[2][i]) winner = mPloca[2][i];
        }

        //dijagonalna provjera
        if((winner == Prazno_polje && mPloca[0][0] == mPloca[1][1] && mPloca[1][1] == mPloca[2][2]) || (winner == Prazno_polje && mPloca[0][2] == mPloca[1][1] && mPloca[1][1] == mPloca[2][0]))  winner = mPloca[1][1];

        //provjera dali je puno polje, tj dali je izjednaceno
        int counter = 0;
        for(int i = 0; i<mRed; i++){
            for (int j = 0; j<mKolona; j++){
                if(mPloca[i][j] == Prazno_polje) counter++;
            }
        }
        if(counter == 0) winner = 'I'; //ako nema praznih polja je Izjednaceno

        return winner;
    }


}
