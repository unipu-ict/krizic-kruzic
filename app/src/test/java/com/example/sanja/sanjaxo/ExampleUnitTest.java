package com.example.sanja.sanjaxo;

import org.junit.Test;
import com.example.sanja.sanjaxo.KrizicKruzicPloca;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void provjeraPoRedu(){
        KrizicKruzicPloca test = new KrizicKruzicPloca();
        test.ocistiPlocu();
        test.postavi(1,0,'X');
        test.postavi(1,1,'X');
        test.postavi(1,2,'X');
        assertEquals(test.provjeraPobjednika(), 'X');
    }
    @Test
    public void provjeraPokoloni(){
        KrizicKruzicPloca test = new KrizicKruzicPloca();
        test.ocistiPlocu();
        test.postavi(0,0,'X');
        test.postavi(1,0,'X');
        test.postavi(2,0,'X');
        assertEquals(test.provjeraPobjednika(), 'X');
    }
    @Test
    public void provjeradijagonale(){
        KrizicKruzicPloca test = new KrizicKruzicPloca();
        test.ocistiPlocu();
        test.postavi(0,0,'X');
        test.postavi(1,1,'X');
        test.postavi(2,2,'X');
        assertEquals(test.provjeraPobjednika(), 'X');
    }
    @Test
    public void provjeraPlayera(){
        KrizicKruzicPloca test = new KrizicKruzicPloca();
        System.out.println("PROVJERA Trenutnog");
        System.out.println(test.trenIgrac());
        test.izmjeniIgraca();
        System.out.println(test.trenIgrac());
        //assertEquals(test.provjeraPobjednika(), 'X');
    }

}