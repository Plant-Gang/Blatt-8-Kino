package barzahlung;

/**
 * Ein Geldbetrag - setzt sich bei Darstellung aus Euro, Komma und Cent zusammen.
 * Interne Umsetztung per Record -> Basic Konstruktor, getter, equals, hashCode, toString automatisch generiert.
 * TODO 08: Implementierung, Kommentare
 * TODO 08: Testklasse, Kommentare
 * TODO 08: Wenn Impl usw fertig: In Package Wertobjekte schieben.
 */
public record Geldbetrag(int _cent)
{
    /**
     * Konstruktor, der sicherstellt, dass Centbetrag >= 0 ist.
     * @param _cent
     * @ensure _cent >= 0
     */
    public Geldbetrag(int _cent)
    {
        // assert _cent >= 0 : "Vorbedingung verletzt: _cent >= 0";
        this._cent = _cent;
    }

    /**
     * Gibt den Geldbetrag im Format "Euro,Cent€" aus.
     * @return String im Format "Euro,Cent€"
     */
    public String getFormatiertenString()
    {
        return this.getEuroAsString() + "," + this.getCentAsString() + "€";
    }

    public int getEuro()
    {
        if (this._cent > 99)
        {
            String eur = String.valueOf(this._cent)
                .substring(0, this.length() - 2);
            int euro = Integer.parseInt(eur);
            return euro;
        }
        else
        {
            return 0;
        }
    }

    public String getEuroAsString()
    {
        if (this._cent > 99)
        {
            return String.valueOf(this._cent)
                .substring(0, this.length() - 2);
        }
        else
        {
            return "0";
        }
    }

    public int length()
    {
        int länge = String.valueOf(this._cent)
            .length();
        return länge;
    }

    /**
     * Gibt den Centbetrag als 2-stelligen String zurück.
     * @return String ggf mit führender 0 bei einstelligen Centbeträgen
     */
    public String getCentAsString()
    {
        if (this._cent < 10)
        {
            return "0" + String.valueOf(this._cent)
                .substring(this.length() - 1, this.length());
        }
        else
        {
            return String.valueOf(this._cent)
                .substring(this.length() - 2, this.length());
        }
    }

    public int getCent()
    {
        return _cent;
    }

    /**
     * Prüft, ob der angegebene Geldbetrag zu diesem Geldbetrag addiert werden kann,
     * ohne dass ein Integer-Overflow entsteht.
     *
     * @param betrag der zu addierende Geldbetrag.
     * @return true, wenn die Addition möglich ist, sonst false.
     * @require betrag != null
     */
    public boolean istAddierbar(Geldbetrag betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: betrag != null";

        return _cent <= Integer.MAX_VALUE - betrag._cent;
    }

    /**
     * Prüft, ob der angegebene Geldbetrag von diesem Geldbetrag subtrahiert werden
     * kann, ohne dass ein Integer-Overflow entsteht.
     *
     * @param betrag der abzuziehende Geldbetrag.
     * @return true, wenn die Subtraktion möglich ist, sonst false.
     * @require betrag != null
     */
    public boolean istSubtrahierbar(Geldbetrag betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: betrag != null";

        return _cent >= Integer.MIN_VALUE + betrag._cent;
    }

    /**
     * Addiert den angegebenen Geldbetrag zu diesem Geldbetrag und gibt das
     * Ergebnis als neuen Geldbetrag zurück.
     * 
     * @param betrag der zu addierende Geldbetrag.
     * @return die Summe der beiden Geldbeträge.
     * @require betrag != null
     * @ensure result != null
     */
    public Geldbetrag addiere(Geldbetrag betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: betrag != null";
        assert istAddierbar(
                betrag) : "Vorbedingung verletzt: istAddierbar(betrag)";

        return new Geldbetrag(this._cent + betrag._cent);
    }

    /**
     * Subtrahiert den angegebenen Geldbetrag von diesem Geldbetrag.
     *
     * @param betrag der abzuziehende Geldbetrag.
     *
     * @return die Differenz der beiden Geldbeträge.
     *
     * @require betrag != null
     * @ensure result != null
     */
    public Geldbetrag subtrahiere(Geldbetrag betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: betrag != null";
        assert istSubtrahierbar(
                betrag) : "Vorbedingung verletzt: istSubtrahierbar(betrag)";

        return new Geldbetrag(this._cent - betrag._cent);
    }

    /**
     * Multipliziert diesen Geldbetrag mit dem angegebenen Faktor.
     *
     * @param faktor der Multiplikationsfaktor.
     * @return der multiplizierte Geldbetrag.
     *
     * @require faktor >= 0
     * @ensure result != null
     */
    public Geldbetrag multipliziere(int faktor)
    {
        assert faktor >= 0 : "Vorbedingung verletzt: faktor >= 0";

        return new Geldbetrag(this._cent * faktor);
    }

    /**
     * Wandelt den angegebenen Text in einen Geldbetrag um.
     * Der Text muss entweder nur Euro enthalten, z.B. "10",
     * oder Euro und Cent kommasepariert, z.B. "10,50".
     *
     * @param text
     *            der umzuwandelnde Text.
     *
     * @return der Geldbetrag, der durch den Text dargestellt wird.
     *
     * @require text != null
     * @require text hat das Format "E,CC"
     *
     * @ensure result != null
     */
    public static Geldbetrag parse(String text)
    {
        assert text != null : "Vorbedingung verletzt: text != null";
        assert text.matches(
                "[0-9]+,[0-9][0-9]") : "Vorbedingung verletzt: text hat kein gueltiges Format";

        String[] teile = text.split(",");

        int euro = Integer.parseInt(teile[0]);
        int cent = Integer.parseInt(teile[1]);

        return new Geldbetrag(euro * 100 + cent);
    }

}
