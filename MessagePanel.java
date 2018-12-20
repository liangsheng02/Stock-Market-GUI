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
        g.drawString(displayString, 10, 20);
    }
    public void setDisplayString(String s) {
        displayString = s;
        repaint();
    }
}
