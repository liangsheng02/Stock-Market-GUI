import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * This class is a JFrame that draws the stock data.
 * @author Sheng Liang
 * */
public class DrawStockFrame extends JFrame implements ActionListener {

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
        JPanel columnOfButtons = new JPanel(new GridLayout(7,1));
        JLabel ticker_symbol = new JLabel(stockData.getTicker_symbol(), JLabel.CENTER);
        Font labelFont = StaticMethods.getFont("Arial Black", -1, 16, ticker_symbol.getFont());
        if (labelFont != null) {ticker_symbol.setFont(labelFont);}
        if (stockData.getCloseList().get(0) > stockData.getCloseList().get(stockData.getCloseList().size()-1)) {
            ticker_symbol.setForeground(new Color(255,0,0));
        }//if the stock falls, use rad color on the label. else if the stock rises, use green.
        else{ticker_symbol.setForeground(new Color(0,255,0));}
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
        DataRetriever dr = new DataRetriever("AAPL", "01/01/2018", "12/31/2018");
        StockData stockData = dr.getStockData();
        JFrame frm = new DrawStockFrame(stockData);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }


}
