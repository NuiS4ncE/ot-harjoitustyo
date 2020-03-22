/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ugteo
 */
public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti, kortti2, tyhjakortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(100000);
        kortti2 = new Maksukortti(2);
        tyhjakortti = new Maksukortti(0);
        kassapaate = new Kassapaate();
    }

    @Test
    public void rahamaaraJaMyydytLounaat() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maksunRiittavyys() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
        assertEquals(100240, kassapaate.kassassaRahaa());

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(120, kassapaate.syoEdullisesti(120));

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());

        assertEquals(100, kassapaate.syoMaukkaasti(500));
        assertEquals(100640, kassapaate.kassassaRahaa());

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());

        assertEquals(120, kassapaate.syoMaukkaasti(120));

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void korttiostoToimii() {
        assertTrue(kassapaate.syoEdullisesti(kortti));

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());

        assertTrue(kassapaate.syoMaukkaasti(kortti));

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());

        assertFalse(kassapaate.syoMaukkaasti(kortti2)
                && kassapaate.maukkaitaLounaitaMyyty() == 1
                && !kortti2.equals(2) && kassapaate.syoEdullisesti(kortti2)
                && kassapaate.edullisiaLounaitaMyyty() == 1 && !kortti2.equals(2));

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void korttiostoEiToimi() {
        assertFalse(kassapaate.syoEdullisesti(tyhjakortti));
    }

    @Test
    public void kortilleRahaa() {
        kassapaate.lataaRahaaKortille(kortti2, 100);
        kassapaate.lataaRahaaKortille(kortti, -200);
        assertEquals(102, kortti2.saldo());
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
}
