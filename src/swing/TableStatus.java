package swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import main.StatusType;

public class TableStatus extends JLabel {

    private StatusType type;

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    public void setType(StatusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        // Appel de super.paintComponent pour dessiner le composant correctement
        super.paintComponent(grphcs); 

        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Définir le gradient en fonction du type de statut
            GradientPaint gradient;
            if (type == StatusType.PENDING) {
                gradient = new GradientPaint(0, 0, new Color(183, 123, 247), 0, getHeight(), new Color(167, 94, 236));
            } else if (type == StatusType.APPROVED) {
                gradient = new GradientPaint(0, 0, new Color(142, 142, 150), 0, getHeight(), new Color(123, 123, 245));
            } else {
                gradient = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
            }

            // Dessiner le fond avec le gradient
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Coins arrondis pour un meilleur effet visuel

            // Définir la couleur du texte (généralement blanc pour un bon contraste)
            g2.setColor(getForeground());

            // Obtenir les métriques de la police pour mesurer la taille du texte
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());   // Largeur du texte
            int textHeight = fm.getAscent();             // Hauteur du texte (ascent pour la hauteur réelle)

            // Calculer les positions pour centrer le texte
            int x = (getWidth() - textWidth) / 2;        // Centre horizontalement
            int y = (getHeight() + textHeight) / 2 - fm.getDescent(); // Centre verticalement en tenant compte de la descente du texte

            // Dessiner le texte centré
            g2.drawString(getText(), x, y);
        }
    }


}
