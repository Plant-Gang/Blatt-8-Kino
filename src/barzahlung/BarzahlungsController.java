package de.uni_hamburg.informatik.swt.se2.kino.ui.barzahlung;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import de.uni_hamburg.informatik.swt.se2.kino.model.wertobjekte.Geldbetrag;

/**
 * Mit diesem UI-Modul kann eine Barzahlung durchgeführt werden.
 * 
 * Das Modul zeigt den zu zahlenden Gesamtpreis an, liest den vom Kunden
 * bezahlten Betrag ein und berechnet den Restbetrag.
 * 
 * @author SE2-Team
 * @version SoSe 2026
 */
public class BarzahlungsController
{
    private BarzahlungsView _view;

    private Geldbetrag _gesamtpreis;

    private boolean _barzahlungErfolgreich;

    /**
     * Initialisiert das Barzahlungsmodul.
     * 
     * @param gesamtpreis
     *            der zu zahlende Gesamtpreis.
     * 
     * @require gesamtpreis != null
     */
    public BarzahlungsController(Geldbetrag gesamtpreis)
    {
        assert gesamtpreis != null : "Vorbedingung verletzt: gesamtpreis != null";

        _gesamtpreis = gesamtpreis;
        _barzahlungErfolgreich = false;

        _view = new BarzahlungsView();

        initialisiereAnzeige();
        registriereUIAktionen();
    }

    /**
     * Führt die Barzahlung durch.
     * 
     * @return true, wenn die Barzahlung erfolgreich abgeschlossen wurde,
     *         sonst false.
     */
    public boolean fuehreBarzahlungDurch()
    {
        _barzahlungErfolgreich = false;
        _view.zeigeDialog();

        return _barzahlungErfolgreich;
    }

    /**
     * Initialisiert die Anzeige des Barzahlungsfensters.
     */
    private void initialisiereAnzeige()
    {
        _view.getPreisLabel()
            .setText(_gesamtpreis.getFormatiertenString());

        _view.getRestLabel()
            .setText("0,00");

        _view.getOKButton()
            .setEnabled(false);
    }

    /**
     * Fügt der UI die Funktionalität hinzu.
     */
    private void registriereUIAktionen()
    {
        _view.getOKButton()
            .addActionListener(e -> bestaetigeBarzahlung());

        _view.getAbbrechenButton()
            .addActionListener(e -> brecheBarzahlungAb());

        _view.getBezahltTextField()
            .addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyReleased(KeyEvent event)
                {
                    aktualisiereRestbetrag();
                }
            });
    }

    /**
     * Aktualisiert den Restbetrag anhand des eingegebenen Betrags.
     */
    private void aktualisiereRestbetrag()
    {
        String eingabe = _view.getBezahltTextField()
            .getText();

        try
        {
            Geldbetrag bezahlt = Geldbetrag.parse(eingabe);
            Geldbetrag restbetrag = bezahlt.subtrahiere(_gesamtpreis);

            _view.getRestLabel()
                .setText(restbetrag.getFormatiertenString());

            _view.getOKButton()
                .setEnabled(bezahlt.getCent() >= _gesamtpreis.getCent());
        }
        catch (AssertionError error)
        {
            _view.getRestLabel()
                .setText("Ungültige Eingabe");

            _view.getOKButton()
                .setEnabled(false);
        }
    }

    /**
     * Bestätigt die Barzahlung.
     */
    private void bestaetigeBarzahlung()
    {
        _barzahlungErfolgreich = true;
        _view.schliesseDialog();
    }

    /**
     * Bricht die Barzahlung ab.
     */
    private void brecheBarzahlungAb()
    {
        _barzahlungErfolgreich = false;
        _view.schliesseDialog();
    }
}