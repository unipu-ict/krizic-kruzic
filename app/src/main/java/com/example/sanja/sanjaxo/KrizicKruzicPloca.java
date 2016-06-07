package com.example.sanja.sanjaxo;
import static java.lang.System.*;

import java.util.Random;

public class KrizicKruzicPloca {

    //--------- varijable klase
    private final static int mRed  = 3;
    private final static int mKolona  = 3;

    public static final char Covjek = 'O';
    public static final char Stroj = 'X';
    public static final char Prazno_polje = '_';

    //Postavlja se random igrac koji pocinje igru
    private Random mRandom = new Random();
    public char trenutniIgrac = mRandom.nextBoolean() ? 'X' : 'O';

    // postavljanje ploce za igranje
    private char[][] mPloca = new char[mRed][mKolona];
    //-----

    public KrizicKruzicPloca(){
        ocisti();
    }

    public void ocisti(){
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

    //trebam napraviti potez u kojem racunalo gleda dali ce pobijediti ukoliko odigra potez na odrecenom mjestu.
    // ako nece pobijediti potez se odigrava na random mjesto

    // trebam napraviti provjeru dali ima pobjednika


}
