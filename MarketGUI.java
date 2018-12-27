import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MarketGUI extends JFrame {
    private JPanel MainPanel;
    private JPanel logoPanel;
    private MessagePanel messagePanel;
    private CenterPanel centerPanel;
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
            stockData = centerPanel.getStockData();
            messagePanel.setDisplayString("Your last selection is : " + stockData.getTicker_symbol()
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
            messagePanel.setDisplayString("End Date Error: Later Than today.");
        }
        else if(status == 2){//Error2
            messagePanel.setDisplayString("Start Date Error: Not Before End Date.");
        }
        else if( status == 3){//Error3
            messagePanel.setDisplayString("Please Select Stock, Start Date and End Date.");
        }
    }

    public MarketGUI() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());
        this.setContentPane(MainPanel);
        MainGBC=new GridBagConstraints();

        //North: LogoPanel
        logoPanel = new LogoPanel();
        logoPanel.setLayout(new GridBagLayout());
        MainGBC.gridx=0;
        MainGBC.gridy=0;
        MainGBC.weightx=1;
        MainGBC.weighty=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(logoPanel, MainGBC);
        logoPanel.setPreferredSize(new Dimension(800, 150));

        //South: MessagePanel
        messagePanel = new MessagePanel();
        MainGBC.gridx=0;
        MainGBC.gridy=2;
        MainGBC.weightx=1;
        MainGBC.weighty=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        messagePanel.setDisplayString("Welcome to MarketGUI !");
        MainPanel.add(messagePanel, MainGBC);
        messagePanel.setPreferredSize(new Dimension(800, 50));

        //Center: CenterPanel
        centerPanel= new CenterPanel(this);
        MainGBC.gridx=0;
        MainGBC.gridy=1;
        MainGBC.weightx=1;
        MainGBC.weighty=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(centerPanel, MainGBC);
        centerPanel.setPreferredSize(new Dimension(800, 200));
    }

    public static void main(String[] args) {
        MarketGUI frame = new MarketGUI();
        frame.setContentPane(new MarketGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
