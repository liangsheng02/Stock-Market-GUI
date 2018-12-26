import javax.swing.*;
import java.awt.*;

public class MarketGUI {
    private JPanel MainPanel;
    private JPanel NorthPanel;
    private MessagePanel mPanel;
    private CenterPanel cPanel;
    private StockData Stock;

    /**
     * This method receives a status, if the status is OK then gets the stock from CenterPanel,
     * otherwise prints error messages by using MessagePanel.
     * @param status
     */
    public void getMessage(int status){
        if (status == 0){//OK
            Stock = cPanel.getStock();
            mPanel.setDisplayString("Welcome to MarketGUI !");
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
        GridBagConstraints MainGBC=new GridBagConstraints();

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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MarketGUI");
        frame.setContentPane(new MarketGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    /**
     * @noinspection ALL
     */
    private Font getFont(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent getRootComponent() {
        return MainPanel;
    }

}
