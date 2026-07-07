package barzahlung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest
{
    //TODO Mehr Test als immer nur 1 Testfall!
    @Test
    public void testGetCent()
    {
        Geldbetrag betrag = new Geldbetrag(250);

        assertEquals(250, betrag.getCent());
    }

    @Test
    public void testeFormatiertenString()
    {
        Geldbetrag betrag = new Geldbetrag(250);
        assertEquals("2,50€", betrag.getFormatiertenString());

        Geldbetrag betrag2 = new Geldbetrag(0);
        assertEquals("0,00€", betrag2.getFormatiertenString());

        Geldbetrag betrag3 = new Geldbetrag(1000);
        assertEquals("10,00€", betrag3.getFormatiertenString());

        Geldbetrag betrag4 = new Geldbetrag(1884);
        assertEquals("18,84€", betrag4.getFormatiertenString());
    }

    @Test
    public void testeAddiere()
    {
        Geldbetrag betrag1 = new Geldbetrag(250);
        Geldbetrag betrag2 = new Geldbetrag(300);

        Geldbetrag ergebnis = betrag1.addiere(betrag2);

        assertEquals(550, ergebnis.getCent());
    }

    @Test
    public void testeAddiereVeraendertUrspruenglicheBetraegeNicht()
    {
        Geldbetrag betrag1 = new Geldbetrag(250);
        Geldbetrag betrag2 = new Geldbetrag(300);

        betrag1.addiere(betrag2);

        assertEquals(250, betrag1.getCent());
        assertEquals(300, betrag2.getCent());
    }

    @Test
    public void testeSubtrahiere()
    {
        Geldbetrag betrag1 = new Geldbetrag(250);
        Geldbetrag betrag2 = new Geldbetrag(300);

        Geldbetrag ergebnis = betrag2.subtrahiere(betrag1);

        assertEquals(50, ergebnis.getCent());
    }

    @Test
    public void testeSubtrahiereNegativesErgebnis()
    {
        Geldbetrag betrag1 = new Geldbetrag(250);
        Geldbetrag betrag2 = new Geldbetrag(300);

        Geldbetrag ergebnis = betrag1.subtrahiere(betrag2);

        assertEquals(-50, ergebnis.getCent());
    }

    @Test
    public void testeSubtrahiereVeraendertOriginalNicht()
    {
        Geldbetrag betrag1 = new Geldbetrag(550);
        Geldbetrag betrag2 = new Geldbetrag(250);

        betrag1.subtrahiere(betrag2);

        assertEquals(550, betrag1.getCent());
        assertEquals(250, betrag2.getCent());
    }

    @Test
    public void testeMultipliziere()
    {
        Geldbetrag betrag = new Geldbetrag(250);

        Geldbetrag ergebnis = betrag.multipliziere(4);

        assertEquals(1000, ergebnis.getCent());
    }

    @Test
    public void testeMultipliziereMitNull()
    {
        Geldbetrag betrag = new Geldbetrag(250);

        Geldbetrag ergebnis = betrag.multipliziere(0);

        assertEquals(0, ergebnis.getCent());
    }

    @Test
    public void testeMultipliziereMitEins()
    {
        Geldbetrag betrag = new Geldbetrag(250);

        Geldbetrag ergebnis = betrag.multipliziere(1);

        assertEquals(250, ergebnis.getCent());
    }

    @Test
    public void testeMultipliziereVeraendertOriginalNicht()
    {
        Geldbetrag betrag = new Geldbetrag(250);

        betrag.multipliziere(4);

        assertEquals(250, betrag.getCent());
    }

    @Test
    public void testeParse()
    {
        Geldbetrag betrag = Geldbetrag.parse("10,00");

        assertEquals(1000, betrag.getCent());
    }

    @Test
    public void testeIstAddierbar()
    {
        Geldbetrag betrag1 = new Geldbetrag(100);
        Geldbetrag betrag2 = new Geldbetrag(200);

        assertTrue(betrag1.istAddierbar(betrag2));
    }

    @Test
    public void testeIstNichtAddierbarBeiOverflow()
    {
        Geldbetrag betrag1 = new Geldbetrag(Integer.MAX_VALUE);
        Geldbetrag betrag2 = new Geldbetrag(1);

        assertFalse(betrag1.istAddierbar(betrag2));
    }

    @Test
    public void testeIstSubtrahierbar()
    {
        Geldbetrag betrag1 = new Geldbetrag(500);
        Geldbetrag betrag2 = new Geldbetrag(300);

        assertTrue(betrag1.istSubtrahierbar(betrag2));
    }

    @Test
    public void testeIstNichtSubtrahierbarBeiOverflow()
    {
        Geldbetrag betrag1 = new Geldbetrag(Integer.MIN_VALUE);
        Geldbetrag betrag2 = new Geldbetrag(1);

        assertFalse(betrag1.istSubtrahierbar(betrag2));
    }
}