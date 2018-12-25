import javax.swing.*;
import java.awt.*;

public class GlassPanel extends JPanel {

    private Point pX, pY, p;
    private String textX, textY;
    private Graphics2D g2;

    public void reDraw(Point pX, Point pY, Point p, String textX, String textY) {
        this.pX = pX;
        this.pY = pY;
        this.p = p;
        this.textX = textX;
        this.textY = textY;
        invalidate();
        this.repaint();
    }

    public GlassPanel() {
        this.pX = new Point();
        this.pY = new Point();
        this.p = new Point();
        this.textX = "";
        this.textY = "";
        this.setBackground(new Color(255,255,255,255));
        //this.setOpaque(false);
        JLabel label = new JLabel();
        label.setText("Fuck");
        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0,0,0,128));

        //draw lines
        g2.drawLine(pX.x, pX.y, p.x, p.y);//to x axis
        g2.drawLine(pY.x, pY.y, p.x, p.y);//to y axis

        //draw strings
        g2.drawString(textX, pX.x, pX.y+15);//x axis label
        g2.drawString(textY, pY.x+5, pY.y);//y axis label
    }

    /**
     * Main method for testing
     * */
    public static void main(String[] args) {
        GlassPanel glassPanel = new GlassPanel();
        glassPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(glassPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        glassPanel.reDraw(new Point(2,441),new Point(770,569),new Point(770,441),"X","Y");
    }

}
