import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;


public class GraphFrame extends JFrame implements ActionListener, ComponentListener {

    private StockData Stock;
    private GraphPanel graphPanel;
    private JCheckBox checkOpen;
    private JCheckBox checkHigh;
    private JCheckBox checkLow;
    private JCheckBox checkClose;
    private JCheckBox checkVolumn;
    private JButton Exit;

    public GraphFrame(ArrayList<Object> Stock) {
        this.Stock = Stock;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        this.setBounds((int) width/4, (int) height/4, (int) width/2, (int) height/2);

        Container contentPane = this.getContentPane();
        graphPanel = new GraphPanel(this.Stock);
        graphPanel.addComponentListener(this);
        contentPane.add(graphPanel, BorderLayout.CENTER);

        /* Create a column of buttons using GridLayout in an ordinary JPanel
         * (because it doesn't need extra functionality) on the EAST side of
         * the content pane.
         */
        JPanel columnOnTheRight = new JPanel(new GridLayout(6,1));
        checkOpen = addJCheckBox(columnOnTheRight,"Open",this);
        checkHigh = addJCheckBox(columnOnTheRight,"High",this);
        checkLow = addJCheckBox(columnOnTheRight,"Low",this);
        checkClose = addJCheckBox(columnOnTheRight,"Close",this);
        checkVolumn = addJCheckBox(columnOnTheRight,"Volumn",this);
        Exit = new JButton("Exit");
        // add it to the specified JPanel and make the JPanel listen
        columnOnTheRight.add(Exit);
        contentPane.add(columnOnTheRight,BorderLayout.EAST);



    }


    private JCheckBox addJCheckBox(JPanel p, String s, ActionListener a) {
        JCheckBox c = new JCheckBox(s);
        c.addActionListener(a);
        p.add(c);
        return c;
    }
}
