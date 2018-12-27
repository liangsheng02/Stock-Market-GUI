import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class is the main GUI of the project.
 * It contains three panels on the ContentPane: logoPanel, centerPanel and messagePanel.
 * The logoPanel show a logo "MarketGUI" and my github URL. The messagePanel show some messages after user operates.
 * The centerPanel is where the main features are. It contains 7 JComboBoxes and a JButton.
 * After select all JComboBoxes and click the JButton, a new JFrame drawStockFrame will be opened.
 * In drawStockFrame, a graph of the stock Close data will be showed.
 * And user can select which data would be display by clicking the JButtons on the right.
 * @author Sheng Liang
 * */
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
            messagePanel.setDisplayString("Last Gotten Data : " + stockData.getTicker_symbol()
                    + " from " + stockData.getDateList().get(0)
                    + " to " + stockData.getDateList().get(stockData.getDateList().size()-1));
            //open new window
            this.setEnabled(false);
            MarketGUI This=this;
            //create new window
            DrawStockFrame drawStockFrame= new DrawStockFrame(stockData);
            drawStockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//close drawStockFrame when exit it, not all JFrames
            drawStockFrame.setVisible(true);
            drawStockFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    This.setEnabled(true);
                }
            });
        }
        else if (status == 1){//Error1
            messagePanel.setDisplayString("End Date Error: Later Than today.");
        }
        else if (status == 2){//Error2
            messagePanel.setDisplayString("Start Date Error: Not Before End Date.");
        }
        else if (status == 3){//Error3
            messagePanel.setDisplayString("Please Select Stock, Start Date and End Date.");
        }
        else if (status == 4){//Error4
            messagePanel.setDisplayString("Don't Have Enough Data");
        }
    }

    public MarketGUI() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());
        this.setContentPane(MainPanel);
        MainGBC=new GridBagConstraints();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        this.setBounds((int) width/4, (int) height/4, (int) width/2, (int) height/2);

        //North: LogoPanel
        logoPanel = new LogoPanel();
        logoPanel.setLayout(new GridBagLayout());
        MainGBC.gridx=0;
        MainGBC.gridy=0;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(logoPanel, MainGBC);
        logoPanel.setPreferredSize(new Dimension(800, 150));

        //South: MessagePanel
        messagePanel = new MessagePanel();
        MainGBC.gridx=0;
        MainGBC.gridy=2;
        MainGBC.fill=GridBagConstraints.BOTH;
        messagePanel.setDisplayString("Welcome to MarketGUI !");
        MainPanel.add(messagePanel, MainGBC);
        messagePanel.setPreferredSize(new Dimension(800, 40));

        //Center: CenterPanel
        centerPanel= new CenterPanel(this);
        MainGBC.gridx=0;
        MainGBC.gridy=1;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(centerPanel, MainGBC);
        centerPanel.setPreferredSize(new Dimension(800, 200));
    }

    public static void main(String[] args) {
        MarketGUI frame = new MarketGUI();
        frame.setTitle("MarketGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
