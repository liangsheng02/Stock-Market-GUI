import javax.swing.*;
import java.awt.*;

/**
 * This Class shows messages after click the button on MainPanel.
 * @author Sheng Liang
 */
public class MessagePanel extends JPanel {

    private String displayString = "";

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(StaticMethods.backgroundColor);
        Font displayStringFont = StaticMethods.getFont("Arial", -1, 12, g.getFont());
        if (displayStringFont != null) {
            g.setFont(displayStringFont);
        }
        FontMetrics metrics = g.getFontMetrics();
        int labelWidth = metrics.stringWidth(displayString);
        int x = getWidth()/2 - labelWidth/2;
        g.drawString(displayString, x, 30);
    }
    public void setDisplayString(String s) {
        displayString = s;
        repaint();
    }

    public MessagePanel() {
        this.setBackground(StaticMethods.stringColor);
    }
}
