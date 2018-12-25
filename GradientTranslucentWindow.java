import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradientTranslucentWindow extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

    private int counter = 0;
    private int draw = -1;
    private int red[] = {58,71,231,243,255};
    private int green[] = {54,224,235,109,40};
    private int blue[] = {241,95,61,52,40};
    private int R = 240;
    private int G = 240;
    private int B = 200;
    private Point start, end;
    private Graphics gd;
    private JPanel panel;

    public GradientTranslucentWindow() {
        setBackground(new Color(0,0,0,0));
        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    //Paint p =  new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(), new Color(R, G, B, 150), true);
                    Graphics2D g2d = (Graphics2D)g;
                    //g2d.setPaint(p);
                    g2d.setColor(new Color(255,255,255,255));
                    if(draw==-1) g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        setContentPane(panel);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GradientTranslucentWindow().setVisible(true);
    }

    public void mousePressed(MouseEvent e) { start = new Point(e.getX(), e.getY()); }
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) draw = 1;
        if(e.getButton() == MouseEvent.BUTTON3) draw = 0;
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e)  {}

    public void mouseMoved(MouseEvent e) {
        gd = this.getGraphics();

        if(draw==1){
            end = new Point(e.getX(), e.getY());
            System.out.println(e.getX()+ " " +e.getY());
            gd.setColor(new Color( red[counter],green[counter],blue[counter]));
            panel.repaint();
            gd.drawLine(start.x, 500, start.x, end.y);
            start = end;
            //panel.repaint();
            System.out.println(start.x + " - " + start.y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            counter++;
            if ( counter > 4 ) counter = 0;
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}