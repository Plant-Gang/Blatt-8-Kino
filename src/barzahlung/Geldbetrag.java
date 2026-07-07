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
        assert _cent >= 0 : "Vorbedingung verletzt: _cent >= 0";
        this._cent = _cent;
    }

    /**
     * 
     * @return
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
}
