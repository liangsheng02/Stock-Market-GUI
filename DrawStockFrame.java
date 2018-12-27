import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * This class is a JFrame that draws the stock data.
 * The buttons on the west choose which data would be showed on the graph (default: Close).
 * The JLabel shows the ticker symbol and number of Close changes during the period,
 * if stock raise, the label would be green, else red.
 * @author Sheng Liang
 * */
public class DrawStockFrame extends JFrame implements ActionListener {

    private Color backgroundColor = new Color(60, 63, 65, 255);
    private Color stringColor = new Color(186, 186, 186, 180);
    private Color riseColor = new Color(0, 255, 0, 180);
    private Color fallColor = new Color(255, 0, 0, 180);
    private String labelText;
    private Double Close0;
    private Double Close1;
    private DrawStock drawStock;
    private StockData stockData;



    /**
     * Constructor, add a DrawStock JPanel and a columnOfButtons JPanel on the Frame.
     * @param stockData StockData object
     * */
    public DrawStockFrame(StockData stockData) {
        this.stockData = stockData;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        this.setBounds((int) width/4, (int) height/4, (int) width/2, (int) height/2);

        Container contentPane = this.getContentPane();
        drawStock = new DrawStock(this.stockData.getCloseList(), this.stockData.getDateList());
        contentPane.add(drawStock, BorderLayout.CENTER);

        //Create a column of buttons and one label using GridLayout in an ordinary JPanel on the EAST side.
        JPanel columnOfButtons = new JPanel(new GridLayout(7,1,0,20));
        columnOfButtons.setBackground(backgroundColor);
        JLabel ticker_symbol = new JLabel("",JLabel.CENTER);
        Font labelFont = StaticMethods.getFont("Arial Black", -1, 16, ticker_symbol.getFont());
        if (labelFont != null) {ticker_symbol.setFont(labelFont);}
        Close0 = stockData.getCloseList().get(0);
        Close1 = stockData.getCloseList().get(stockData.getCloseList().size()-1);
        //if the stock falls, use rad color and "-" on the label. Use HTML to change line.
        if (Close0 > Close1) {
            labelText = "<html><body><p align=\"center\">" + stockData.getTicker_symbol() + "<br/>"
                    + "-" + String.format("%.2f",Close0-Close1) + "</p></body></html>";
            ticker_symbol.setForeground(fallColor);
        }
        //if the stock rises, use green and "+".
        else{
            labelText = "<html><body><p align=\"center\">" + stockData.getTicker_symbol() + "<br/>"
                    + "+" + String.format("%.2f",Close1-Close0) + "</p></body></html>";
            ticker_symbol.setForeground(riseColor);

        }
        ticker_symbol.setText(labelText);
        //add buttons
        columnOfButtons.add(ticker_symbol);
        makeButton(columnOfButtons, "Open", this);
        makeButton(columnOfButtons, "Close", this);
        makeButton(columnOfButtons, "High", this);
        makeButton(columnOfButtons, "Low", this);
        makeButton(columnOfButtons, "Volume", this);
        makeButton(columnOfButtons, "Quit", this);
        contentPane.add(columnOfButtons, BorderLayout.EAST);
    }

    /**
     * This method creates button and add ActionListener to it.
     * @param p JPanel that this button will add to
     * @param name name of the button
     * @param target ActionListener
     * */
    private void makeButton(JPanel p, String name, ActionListener target) {
        JButton b = new JButton(name);
        b.setBackground(backgroundColor);
        Font f = StaticMethods.getFont("Arial Black", -1, -1, this.getFont());
        if (f != null){b.setFont(f);}
        b.setForeground(stringColor);
        // add it to the specified JPanel and make the JPanel listen
        p.add(b);
        b.addActionListener(target);
    }

    /**
     * This method detects which button is clicked by User,
     * then send corresponding ArrayList to drawStock, and repaint the line graph.
     * */
    public void actionPerformed(ActionEvent e) {
        // Do the appropriate thing depending on which button is pressed.
        // Use the getActionCommand() method to identify the button.
        String command = e.getActionCommand();
        if (command.equals("Quit")) {
            System.exit(0);
        }
        else if (command.equals("Open")) {
            drawStock.setData(stockData.getOpenList());
        }
        else if (command.equals("Close")) {
            drawStock.setData(stockData.getCloseList());
        }
        else if (command.equals("High")) {
            drawStock.setData(stockData.getHighList());
        }
        else if (command.equals("Low")) {
            drawStock.setData(stockData.getLowList());
        }
        else if (command.equals("Volume")) {
            drawStock.setData(stockData.getVolumeList());
        }

    }

    /**
     * Main method for testing.
     * */
    public static void main(String args[]) throws IOException {
        DataRetriever dr = new DataRetriever("FB", "01/01/2018", "12/31/2018");
        StockData stockData = dr.getStockData();
        JFrame frm = new DrawStockFrame(stockData);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }


}
