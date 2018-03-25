package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2);
        assertTrue(kortti.saldo() == 12);
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(6);
        assertTrue(kortti.saldo() == 4);
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(12);
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void palauttaaTrueJosRahaaOnTarpeeksi() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void palauttaaFalseJosRahaaEiOleTarpeeksi() {
        assertTrue(!kortti.otaRahaa(15));
    }
    
    @Test
    public void oikeaToStringMetodi() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
