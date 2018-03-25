package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sovu
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void luotuOikeallaMaarallaRahaa() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void luotuOikeallaMaarallaEdullisia() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void luotuOikeallaMaarallaMaukkaita() {
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void syoEdullisestiRiittavallaRahallaKasvattaaOikein() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.kassassaRahaa() == 100240);
    }
    
    @Test
    public void syoMaukkaastiRiittavallaRahallaKasvattaaOikein() {
        kassapaate.syoMaukkaasti(500);
        assertTrue(kassapaate.kassassaRahaa() == 100400);
    }
    
    @Test
    public void syoEdullisestiAntaaOikeanVaihtorahan() {
        assertTrue(kassapaate.syoEdullisesti(340) == 100);
    }
    
    @Test
    public void syoMaukkaastiAntaaOikeanVaihtorahan() {
        assertTrue(kassapaate.syoMaukkaasti(500) == 100);
    }
    
    @Test
    public void syodytEdullisetLounaatKasvavatKunMaksuOnRiittava() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void syodytMaukkaatLounaatKasvavatKunMaksuOnRiittava() {
        kassapaate.syoMaukkaasti(500);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void syoEdullisestiEiKasvataJosRahatEivatRiita() {
        kassapaate.syoEdullisesti(1);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void syoMaukkaastiEiKasvataJosRahatEivatRiita() {
        kassapaate.syoMaukkaasti(1);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kaikkiRiittamattomanEdullisenRahatPalautetaanVaihtorahana() {
        assertTrue(kassapaate.syoEdullisesti(1) == 1);
    }
    
    @Test
    public void kaikkiRiittamattomanMaukkaanRahatPalautetaanVaihtorahana() {
        assertTrue(kassapaate.syoMaukkaasti(1) == 1);
    }
    
    @Test
    public void riittamatonRahaEiKasvataMyytyjenEdullistenMaaraa() {
        kassapaate.syoEdullisesti(1);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void riittamatonRahaEiKasvataMyytyjenMaukkaidenMaaraa() {
        kassapaate.syoMaukkaasti(1);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortillaOnTarpeeksiRahaaVeloittaaEdullisenHinnan() {
        maksukortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(maksukortti.saldo() == 60);
    }
    
    @Test
    public void kortillaOnTarpeeksiRahaaVeloittaaMaukkaanHinnan() {
        maksukortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(maksukortti.saldo() == 100);
    }
    
    @Test
    public void kortillaOnTarpeeksiRahaaPalauttaaTrueEdulliset() {
        maksukortti = new Maksukortti(300);
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void kortillaOnTarpeeksiRahaaPalauttaaTrueMaukkaat() {
        maksukortti = new Maksukortti(500);
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void josKortillaOnTarpeeksiRahaaMyytyjenEdullistenMaaraKasvaa() {
        maksukortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josKortillaOnTarpeeksiRahaaMyytyjenMaukkaidenMaaraKasvaa() {
        maksukortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMyytyjenEdullistenMaaraEiKasva() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMyytyjenMaukkaidenMaaraEiKasva() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaEdulliseenKortinRahamaaraEiMuutu() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(maksukortti.saldo() == 1);
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMaukkaaseenKortinRahamaaraEiMuutu() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(maksukortti.saldo() == 1);
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaEdulliseenPalautetaanFalse() {
        maksukortti = new Maksukortti(1);
        assertTrue(!kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMaukkaaseenPalautetaanFalse() {
        maksukortti = new Maksukortti(1);
        assertTrue(!kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaEdullinen() {
        maksukortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaMaukas() {
        maksukortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaEdullinenKunRahaEiRiita() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaMaukasKunRahaEiRiita() {
        maksukortti = new Maksukortti(1);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void rahaaLadattaessaKortinSaldoMuuttuu() {
        maksukortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertTrue(maksukortti.saldo() == 200);
    }
    
    @Test
    public void rahaaLadattaessaKassanRahaKasvaa() {
        maksukortti = new Maksukortti(9);
        kassapaate.lataaRahaaKortille(maksukortti, 23456);
        assertTrue(kassapaate.kassassaRahaa() == 123456);
    }
    
    @Test
    public void josYritetaanLadataNegatiivinenSummaKassanRahaEiMuutu() {
        maksukortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(maksukortti, -999);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
}
