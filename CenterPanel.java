import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Class is the CenterPanel (with GridBagLayout) contained in MainPanel of MarketGUI.
 * It's where the user can operate.
 * It contains a JComboBox for choosing stock, and six JComboBoxes for choosing month, day and year,
 * a JButton to confirm all the selected things, and, of course, some JLabels.
 * To initialise this class, the constructor will receive a MarketGUI object,
 * so that the MarketGUI can make interaction with this panel.
 * In addition, the background of this panel uses gradient color, to show an effect that opposite from the messagePanel.
 * @author Sheng Liang
 */
public class CenterPanel extends JPanel {

    private JComboBox StartMonthCombo;
    private JComboBox StartDayCombo;
    private JComboBox StartYearCombo;
    private JComboBox EndMonthCombo;
    private JComboBox EndDayCombo;
    private JComboBox EndYearCombo;
    private JComboBox StockCombo;
    private JButton GoButton;
    private GridBagConstraints gbc;
    private int ErrorStatus;
    private StockData stockData;
    private MarketGUI gui;

    /**
     * This method initialises the ComboBoxes with month, year and day.
     * @param type String, between "month", "day" and "year".
     */
    private JComboBox setDateCombo(String type) {
        JComboBox comboBox = new JComboBox();
        if (type == "month"){
            //add 12 months
            for (int i = 0; i < 12; i++) {
                comboBox.addItem(i+1);
            }
        }
        else if (type == "day"){
            //add 31 days
            for (int i = 0; i < 31; i++) {
                comboBox.addItem(i+1);
            }
        }
        else if (type == "year"){
            //add years from 2016 to 2019 for example,
            //all years are available here, but these four years are enough for presentation,
            //since 2016 is a leap year, and this year is 2019.
            for (int i = 2016; i <= 2019; i++) {
                comboBox.addItem(i);
            }
        }
        return comboBox;
    }

    public StockData getStockData() {
        return stockData;
    }

    /**
     * This setter is used to set a Label.
     * @param labelText
     * @param font
     * @param style
     * @param size
     * @return a JLabel
     */
    private JLabel setLabel(String labelText, String font, int style, int size) {
        JLabel label = new JLabel();
        label.setBackground(StaticMethods.stringColor);
        Font labelFont = StaticMethods.getFont(font, style, size, label.getFont());
        if (labelFont != null) label.setFont(labelFont);
        label.setForeground(StaticMethods.stringColor);
        label.setText(labelText);
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint =new GradientPaint(0, 0,
                StaticMethods.backgroundColor, 0, getHeight(), StaticMethods.gradientColor,false);
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * This class creates ActionListener for GoButton, to check whether the dates are legal, and change ErrorStatus.
     * */
    private class GoButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == GoButton) {
                Object objStock = StockCombo.getSelectedItem();
                Object objSM = StartMonthCombo.getSelectedItem();
                Object objSY = StartYearCombo.getSelectedItem();
                Object objSD = StartDayCombo.getSelectedItem();
                Object objEM = EndMonthCombo.getSelectedItem();
                Object objEY = EndYearCombo.getSelectedItem();
                Object objED = EndDayCombo.getSelectedItem();
                if (gui.getStockLog().size() < 3){
                    if (objStock != null && objSM != null && objSY != null && objSD != null
                            && objEM != null && objEY != null && objED != null) {//if all ComboBoxes not empty
                        String choStock = objStock.toString();
                        String choStartDate = objSM.toString() + "/" + objSD.toString() + "/" + objSY.toString();
                        String choEndDate = objEM.toString() + "/" + objED.toString() + "/" + objEY.toString();
                        try {
                            Date start = new SimpleDateFormat("MM/dd/yyyy").parse(choStartDate);
                            Date end = new SimpleDateFormat("MM/dd/yyyy").parse(choEndDate);
                            choStartDate = new SimpleDateFormat("MM/dd/yyyy").format(start);//(1/7/2018->01/07/2018)
                            choEndDate = new SimpleDateFormat("MM/dd/yyyy").format(end);
                            Date today = new Date();
                            if (end.after(today)){//Error3 "End Date Error: Later Than Today."
                                ErrorStatus = 3;
                            }else if (!start.before(end)){//Error4 "Start Date Error: Not Before End Date."
                                ErrorStatus = 4;
                            }else {//get stock data
                                DataRetriever dr = new DataRetriever(choStock, choStartDate, choEndDate);
                                stockData = dr.getStockData();
                                if (stockData.getCloseList().size() == 0){//Error5 "Don't Have Any Data."
                                    ErrorStatus = 5;
                                }else if (stockData.getCloseList().size() == 1){//Error6 "Only Have One Data Point, Not Enough For A Graph."
                                    ErrorStatus = 6;
                                }
                                else if (gui.getStockLog().contains(stockData.getTicker_symbol()
                                        + " from " + stockData.getDateList().get(0)
                                        + " to " + stockData.getDateList().get(stockData.getDateList().size()-1))){
                                    //Error7 "You Have Gotten The Same Data."
                                    ErrorStatus = 7;
                                }
                                else{//OK!
                                    ErrorStatus = 0;
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace(System.err);
                        }
                    }else {//Error2 "Please Select Stock, Start Date and End Date."
                        ErrorStatus = 2;
                    }
                }else{//Error1 "Opened Too Many Windows."
                    ErrorStatus = 1;
                }
                //execute getMessage method in gui to display corresponding message.
                gui.getMessage(ErrorStatus);
            }
        }
    }

    /**
     * This Class implements ItemListener to detect events of date ComboBox items.
     * If choose Feb, there would be 29 days in a leap year, and 28 days in a not leap year.
     * If choose Jan, Mar, May, Jul, Aug, Oct or Dec, there would be 31 days.
     * If choose Apr, Jun, Sep or Nov, there would be 30 days.
     * These are approached by removing all items in Day Combo, and then add items to it according to Month and Year.
     */
    private class DateComboListener implements ItemListener {

        private JComboBox Month;
        private JComboBox Day;
        private JComboBox Year;

        /**
         * Constructor, input the three params of three JComboBox.
         * @param Month JComboBox with 12 months
         * @param Day JComboBox with 31 days
         * @param Year JComboBox with some years
         */
        public DateComboListener(JComboBox Month, JComboBox Day, JComboBox Year) {

            this.Month = Month;
            this.Day = Day;
            this.Year = Year;
        }

        /**
         * This method checks special dates and leap year.
         * In Feb, the items in Day ComboBox depends on Month and Year.
         * Otherwise, in other months, the items in Day ComboBox only depends on Month.
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            int month = Integer.valueOf(Month.getSelectedItem().toString());
            int year = Integer.valueOf(Year.getSelectedItem().toString());
            if (e.getSource()==Month && (month == 4 || month==6 || month==9 || month==11)){
                Day.removeAllItems();
                //30 days
                for (int i = 0; i < 30; i++) {
                    Day.addItem(i + 1);
                }
            }
            else if (e.getSource()==Month && (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)){
                Day.removeAllItems();
                //31days
                for (int i = 0; i < 31; i++) {
                    Day.addItem(i + 1);
                }
            }
            else if (month == 2){
                Day.removeAllItems();
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
                    //29 days
                    for (int i = 0; i < 29; i++){
                        Day.addItem(i + 1);
                    }
                }
                else{
                    //28 days
                    for (int i = 0; i < 28; i++){
                        Day.addItem(i + 1);
                    }
                }
            }
        }
    }

    /**
     * Constructor, add components to the CenterPanel.
     * In this Panel it has 7 JComboBoxes, 6 JLabels and one JButton.
     * @param gui MarketGUI object
     */
    public CenterPanel(MarketGUI gui) {
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        Font CenterPanelFont = StaticMethods.getFont("Arial", -1, -1, this.getFont());
        if (CenterPanelFont != null) {
            this.setFont(CenterPanelFont);
        }
        this.setForeground(StaticMethods.stringColor);

        //Add StartMonthCombo
        StartMonthCombo = setDateCombo("month");
        gbc = StaticMethods.setGbc(null,1, 1, 17, 1, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        gbc.ipadx = 20;
        this.add(StartMonthCombo, gbc);
        //Add StartDayCombo
        StartDayCombo = setDateCombo("day");
        gbc = StaticMethods.setGbc(gbc,2, 1, 17, 1, 5, 12 ,5, 8); //anchor: WEST, fill: BOTH
        gbc.ipadx = 0;
        this.add(StartDayCombo, gbc);
        //Add StartYearCombo
        StartYearCombo = setDateCombo("year");
        gbc = StaticMethods.setGbc(gbc,3, 1, 17, 1, 5, 10 ,5, 2); //anchor: WEST, fill: BOTH
        this.add(StartYearCombo, gbc);
        //Add EndMonthCombo
        EndMonthCombo = setDateCombo("month");
        gbc = StaticMethods.setGbc(gbc,1, 2, 17, 1, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        this.add(EndMonthCombo, gbc);
        //Add EndDayCombo
        EndDayCombo = setDateCombo("day");
        gbc = StaticMethods.setGbc(gbc,2, 2, 17, 1, 5, 12 ,5, 8); //anchor: WEST, fill: BOTH
        this.add(EndDayCombo, gbc);
        //Add EndYearCombo
        EndYearCombo = setDateCombo("year");
        gbc = StaticMethods.setGbc(gbc,3, 2, 17, 1, 5, 10 ,5, 2); //anchor: WEST, fill: BOTH
        this.add(EndYearCombo, gbc);

        //to control the Day items, initialise the three combo boxes, and add ItemListeners to Month and Year.
        DateComboListener dateComboListener1 = new DateComboListener(StartMonthCombo, StartDayCombo, StartYearCombo);
        DateComboListener dateComboListener2 = new DateComboListener(EndMonthCombo, EndDayCombo, EndYearCombo);
        StartMonthCombo.addItemListener(dateComboListener1);
        StartYearCombo.addItemListener(dateComboListener1);
        EndMonthCombo.addItemListener(dateComboListener2);
        EndYearCombo.addItemListener(dateComboListener2);

        //Add StockCombo, add 3 ticker symbols for example.
        StockCombo = new JComboBox();
        StockCombo.setForeground(Color.BLACK);
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("AAPL");
        defaultComboBoxModel.addElement("FB");
        defaultComboBoxModel.addElement("GOOG");
        StockCombo.setModel(defaultComboBoxModel);
        gbc = StaticMethods.setGbc(gbc,2, 3, 17, 2, 10, 12 ,0, 8);//anchor: WEST, fill: HORIZONTAL
        this.add(StockCombo, gbc);

        //Add label "Start Date :"
        JLabel label1 = setLabel("Start Date :","Arial Black",-1,-1);
        gbc = StaticMethods.setGbc(gbc,0, 1, 13, 0, 5, 0 ,0, 5);//anchor: EAST, fill: None
        this.add(label1, gbc);
        //Add label "End Date   :"
        JLabel label2 = setLabel("End Date   :","Arial Black",-1,-1);
        gbc = StaticMethods.setGbc(gbc,0, 2, 13, 0, 5, 0 ,0, 5);//anchor: EAST, fill: None
        this.add(label2, gbc);
        //Add label "Month"
        JLabel label3 = setLabel("Month","Arial Black",-1,-1);
        gbc = StaticMethods.setGbc(gbc,1, 0, 17, 1, 0, 10 ,0, 0);//anchor: WEST, fill: BOTH
        this.add(label3, gbc);
        //Add label "Day"
        JLabel label4 = setLabel("Day","Arial Black",-1,-1);
        gbc = StaticMethods.setGbc(gbc,2, 0, 17, 1, 0, 12 ,0, 0);//anchor: WEST, fill: BOTH
        this.add(label4, gbc);
        //Add label "Year"
        JLabel label5 = setLabel("Year","Arial Black",-1,-1);
        gbc = StaticMethods.setGbc(gbc,3, 0, 17, 1, 0, 10 ,0, 10);//anchor: WEST, fill: BOTH
        this.add(label5, gbc);
        //Add label "Find a Stock  :"
        JLabel label6 = setLabel("Find a Stock  :","Arial Black",-1,18);
        gbc = StaticMethods.setGbc(gbc,0, 3, 17, 1, 10, 0 ,0, 5);//anchor: WEST, fill: BOTH
        gbc.gridwidth = 2;
        this.add(label6, gbc);

        //Add GoButton
        GoButton = new JButton();
        Font GoButtonFont = StaticMethods.getFont("Arial Black", -1, -1, GoButton.getFont());
        if (GoButtonFont != null) {
            GoButton.setFont(GoButtonFont);
        }
        GoButton.setForeground(Color.black);
        GoButton.setText("Go!");
        gbc = StaticMethods.setGbc(gbc,3, 3, 17, 1, 10,10,0,15);//anchor: West, fill: BOTH
        this.add(GoButton, gbc);
        GoButton.addActionListener(new GoButtonActionListener());//Add ActionListener
    }
}
