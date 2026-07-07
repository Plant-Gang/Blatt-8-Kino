package barzahlung;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Die UI des {@link BarzahlungsController}.
 * 
 * @author SE2-Team
 * @version SoSe 2026
 */
class BarzahlungsView
{
    // Die Widgets, aus denen das UI sich zusammensetzt
    private JDialog _dialog;
    private JLabel _preisLabel;
    private JTextField _bezahltTextField;
    private JLabel _restLabel;
    private JButton _okButton;
    private JButton _abbrechenButton;

    /**
     * Initialisiert die UI.
     */
    public BarzahlungsView(Component cmpt)
    {
        _dialog = erstelleDialog(cmpt);
    }

    /**
     * Erzeugt den Dialog, in dem die Barzahlung durchgeführt wird.
     */
    private JDialog erstelleDialog(Component cmpt)
    {
        JDialog dialog = new JDialog();
        dialog.setTitle("Barzahlung");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        JPanel formularPanel = erstelleFormularPanel();
        JPanel buttonPanel = erstelleButtonPanel();

        dialog.add(formularPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(cmpt);

        return dialog;
    }

    /**
     * Erzeugt das Panel mit Preis, Eingabefeld und Restbetrag.
     */
    private JPanel erstelleFormularPanel()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Gesamtpreis:"));
        _preisLabel = new JLabel();
        panel.add(_preisLabel);

        panel.add(new JLabel("Bezahlt:"));
        _bezahltTextField = new JTextField();
        panel.add(_bezahltTextField);

        panel.add(new JLabel("Restbetrag:"));
        _restLabel = new JLabel();
        panel.add(_restLabel);

        return panel;
    }

    /**
     * Erzeugt das Panel mit den Buttons.
     */
    private JPanel erstelleButtonPanel()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        _okButton = new JButton("OK");
        panel.add(_okButton);

        _abbrechenButton = new JButton("Abbrechen");
        panel.add(_abbrechenButton);

        return panel;
    }

    /**
     * Zeigt den Dialog an.
     */
    public void zeigeDialog()
    {

        _dialog.setVisible(true);
    }

    /**
     * Schließt den Dialog.
     */
    public void schliesseDialog()
    {
        _dialog.dispose();
    }

    /**
     * Gibt das Label für die Preisanzeige zurück.
     */
    public JLabel getPreisLabel()
    {
        return _preisLabel;
    }

    /**
     * Gibt das Eingabefeld für den bezahlten Betrag zurück.
     */
    public JTextField getBezahltTextField()
    {
        return _bezahltTextField;
    }

    /**
     * Gibt das Label für den Restbetrag zurück.
     */
    public JLabel getRestLabel()
    {
        return _restLabel;
    }

    /**
     * Gibt den OK-Button zurück.
     */
    public JButton getOKButton()
    {
        return _okButton;
    }

    /**
     * Gibt den Abbrechen-Button zurück.
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }
}