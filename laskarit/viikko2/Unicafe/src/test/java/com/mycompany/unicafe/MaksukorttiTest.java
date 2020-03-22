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
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoOikeinAlussa() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void saldoKasvaaOikein() {
        kortti.lataaRahaa(20);
        assertEquals(30, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
        assertEquals("saldo: 0.5", kortti.toString());
    }

    @Test
    public void saldoEiMuutu() {
        kortti.otaRahaa(200);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahatRiittavat() {
        assertTrue(kortti.otaRahaa(5));
    }
}
