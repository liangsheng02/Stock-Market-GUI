import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MarketGUI extends JFrame {
    private JPanel MainPanel;
    private JPanel NorthPanel;
    private MessagePanel mPanel;
    private CenterPanel cPanel;
    private StockData stockData;
    private GridBagConstraints MainGBC;

    /**
     * This method would be used in CenterPanel. It receives a status from CenterPanel,
     * if the status is OK, then gets the stockData and open a new DrawStockFrame to draw the graph,
     * otherwise prints error messages by using MessagePanel.
     * @param status
     */
    public void getMessage(int status){
        if (status == 0){//OK
            stockData = cPanel.getStockData();
            mPanel.setDisplayString("Your last selection is : " + stockData.getTicker_symbol()
                    + " from " + stockData.getDateList().get(0)
                    + " to " + stockData.getDateList().get(stockData.getDateList().size()-1));
            //open new window
            this.setEnabled(false);
            MarketGUI This=this;
            //create new window
            DrawStockFrame drawStockFrame= new DrawStockFrame(stockData);
            drawStockFrame.setVisible(true);
            drawStockFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    This.setEnabled(true);
                }
            });
        }
        else if(status == 1){//Error1
            mPanel.setDisplayString("End Date Error: Later Than today.");
        }
        else if(status == 2){//Error2
            mPanel.setDisplayString("Start Date Error: Not Before End Date.");
        }
        else if( status == 3){//Error3
            mPanel.setDisplayString("Please Select Stock, Start Date and End Date.");
        }
    }

    public MarketGUI() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());
        MainPanel.setForeground(new Color(-12828863));
        MainGBC=new GridBagConstraints();

        //North
        NorthPanel = new JPanel();
        NorthPanel.setLayout(new GridBagLayout());
        MainGBC.gridx=0;
        MainGBC.gridy=0;
        MainGBC.weightx=1;
        MainGBC.weighty=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(NorthPanel, MainGBC);
        final JLabel label1 = new JLabel();
        label1.setText("Sheng Liang");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        NorthPanel.add(label1, gbc);

        //South
        mPanel = new MessagePanel();
        MainGBC.gridx=0;
        MainGBC.gridy=2;
        MainGBC.weightx=1;
        MainGBC.weighty=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        mPanel.setDisplayString("Welcome to MarketGUI !");
        MainPanel.add(mPanel, MainGBC);
        mPanel.setPreferredSize(new Dimension(100, 40));

        //Center
        cPanel= new CenterPanel(this);
        MainGBC.gridx=0;
        MainGBC.gridy=1;
        MainGBC.weightx=1;
        MainGBC.weighty=10;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(cPanel, MainGBC);

        this.setContentPane(MainPanel);
    }

    public static void main(String[] args) {
        MarketGUI frame = new MarketGUI();
        frame.setContentPane(new MarketGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
