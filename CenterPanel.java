import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Class is the CenterPanel (with GridBagLayout) contained in MainPanel of MarketGUI.
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
    private StockData Stock;
    private MarketGUI gui;

    public StockData getStock() {
        return Stock;
    }

    /**
     * This setter is used to set a GridBagConstraints.
     * @param x
     * @param y
     * @param anchor
     * @param fill
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @return a GridBagConstraints
     */
    public GridBagConstraints setGbc(int x, int y, int anchor, int fill, int top, int left, int bottom, int right) {
        gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }

    /**
     * This setter is used to set a Label.
     * @param labelText
     * @param font
     * @param style
     * @param size
     * @return a JLabel
     */
    public JLabel setLabel(String labelText, String font, int style, int size) {
        JLabel label = new JLabel();
        label.setBackground(new Color(-4539718));
        Font labelFont = this.getFont(font, style, size, label.getFont());
        if (labelFont != null) label.setFont(labelFont);
        label.setForeground(new Color(-4539718));
        label.setText(labelText);
        return label;
    }

    /**
     * Constructor, add components to the CenterPanel.
     * @param gui
     */
    public CenterPanel(MarketGUI gui) {
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(-12828863));
        Font CenterPanelFont = this.getFont(null, -1, -1, this.getFont());
        if (CenterPanelFont != null) this.setFont(CenterPanelFont);
        this.setForeground(new Color(-4539718));
        this.setInheritsPopupMenu(false);

        //Add StartMonthCombo
        StartMonthCombo = new JComboBox();
        gbc = setGbc(1, 1, 17, 1, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        this.add(StartMonthCombo, gbc);
        //Add StartDayCombo
        StartDayCombo = new JComboBox();
        gbc = setGbc(2, 1, 17, 1, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(StartDayCombo, gbc);
        //Add StartYearCombo
        StartYearCombo = new JComboBox();
        gbc = setGbc(3, 1, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(StartYearCombo, gbc);
        //Add EndMonthCombo
        EndMonthCombo = new JComboBox();
        gbc = setGbc(1, 2, 17, 2, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        this.add(EndMonthCombo, gbc);
        //Add EndDayCombo
        EndDayCombo = new JComboBox();
        gbc = setGbc(2, 2, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(EndDayCombo, gbc);
        //Add EndYearCombo
        EndYearCombo = new JComboBox();
        gbc = setGbc(3, 2, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(EndYearCombo, gbc);

        AddItems(StartMonthCombo, StartDayCombo, StartYearCombo);
        AddItems(EndMonthCombo, EndDayCombo, EndYearCombo);
        DateComboListener dateComboListener1 = new DateComboListener(StartMonthCombo, StartDayCombo, StartYearCombo);
        DateComboListener dateComboListener2 = new DateComboListener(EndMonthCombo, EndDayCombo, EndYearCombo);
        StartMonthCombo.addItemListener(dateComboListener1);
        //StartDayCombo.addItemListener(dateComboListener1);
        StartYearCombo.addItemListener(dateComboListener1);
        EndMonthCombo.addItemListener(dateComboListener2);
        //EndDayCombo.addItemListener(dateComboListener2);
        EndYearCombo.addItemListener(dateComboListener2);

        //Add StockCombo
        StockCombo = new JComboBox();
        StockCombo.setForeground(new Color(-12828863));
        final DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("AAPL");
        defaultComboBoxModel.addElement("FB");
        defaultComboBoxModel.addElement("GOOG");
        defaultComboBoxModel.addElement("YHOO");
        StockCombo.setModel(defaultComboBoxModel);
        gbc = setGbc(2, 3, 17, 2, 10, 10 ,0, 10);//anchor: WEST, fill: HORIZONTAL
        this.add(StockCombo, gbc);

        //Add label "Start Date :"
        final JLabel label1 = setLabel("Start Date :","Arial Black",-1,-1);
        gbc = setGbc(0, 1, 13, 0, 5, 0 ,0, 5);//anchor: EAST, fill: None
        this.add(label1, gbc);
        //Add label "End Date   :"
        final JLabel label2 = setLabel("End Date   :","Arial Black",-1,-1);
        gbc = setGbc(0, 2, 13, 0, 5, 0 ,0, 5);//anchor: EAST, fill: None
        this.add(label2, gbc);
        //Add label "Month"
        final JLabel label3 = setLabel("Month","Arial Black",-1,-1);
        gbc = setGbc(1, 0, 17, 1, 0, 10 ,0, 0);//anchor: WEST, fill: BOTH
        this.add(label3, gbc);
        //Add label "Day"
        final JLabel label4 = setLabel("Day","Arial Black",-1,-1);
        gbc = setGbc(2, 0, 17, 1, 0, 10 ,0, 0);//anchor: WEST, fill: BOTH
        this.add(label4, gbc);
        //Add label "Year"
        final JLabel label5 = setLabel("Year","Arial Black",-1,-1);
        gbc = setGbc(3, 0, 17, 1, 0, 10 ,0, 10);//anchor: WEST, fill: BOTH
        this.add(label5, gbc);
        //Add label "Find a Stock  :"
        final JLabel label6 = setLabel("Find a Stock  :","Arial Black",-1,16);
        gbc = setGbc(0, 3, 17, 1, 10, 0 ,0, 5);//anchor: WEST, fill: BOTH
        gbc.gridwidth = 2;
        this.add(label6, gbc);

        //Add GoButton
        GoButton = new JButton();
        GoButton.setBackground(new Color(-12828863));
        Font GoButtonFont = this.getFont("Arial Black", -1, -1, GoButton.getFont());
        if (GoButtonFont != null) GoButton.setFont(GoButtonFont);
        GoButton.setForeground(new Color(-4539718));
        GoButton.setText("Go !");
        gbc = setGbc(3, 3, 17, 2, 10, 10 ,0, 10);//anchor: None, fill: HORIZONTAL
        this.add(GoButton, gbc);
        GoButton.addActionListener(new GoButtonActionListener());//Add ActionListener
    }

    /**
     * Create ActionListener for GoButton, to check whether the dates are legal, and change ErrorStatus.
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
                if (objStock != null && objSM != null && objSY != null && objSD != null
                        && objEM != null && objEY != null && objED != null) {
                    String choStock = objStock.toString();
                    String choStartDate = objSM.toString() + "/" + objSD.toString() + "/" + objSY.toString();
                    String choEndDate = objEM.toString() + "/" + objED.toString() + "/" + objEY.toString();
                    try {
                        Date start = new SimpleDateFormat("MM/dd/yyyy").parse(choStartDate);
                        Date end = new SimpleDateFormat("MM/dd/yyyy").parse(choEndDate);
                        choStartDate = new SimpleDateFormat("MM/dd/yyyy").format(start);//(1/7/2018->01/07/2018)
                        choEndDate = new SimpleDateFormat("MM/dd/yyyy").format(end);
                        Date today = new Date();
                        if (end.after(today)) { //Error1 "End Date Error: Later Than Today."
                            ErrorStatus = 1;
                        } else if (!start.before(end)) { // Error2 "Start Date Error: Not Before End Date."
                            ErrorStatus = 2;
                        } else { //OK
                            ErrorStatus = 0;
                            DataRetriever dr = new DataRetriever(choStock, choStartDate, choEndDate);
                            Stock = dr.getStock();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(System.err);
                    }
                } else { //Error3 "Please Select Stock, Start Date and End Date."
                    ErrorStatus = 3;
                }
                gui.getMessage(ErrorStatus);
            }
        }
    }

    /**
     * This method initialises the three drop down boxes for month, year and day.
     */
    public void AddItems(JComboBox Month, JComboBox Day, JComboBox Year) {
        // add 12 months
        for (int i = 0; i < 12; i++) {
            Month.addItem(i + 1);
        }
        // add years from 2016 to 2019
        for (int i = 2016; i <= 2019; i++) {
            Year.addItem(i);
        }
        // add 31 days
        for (int j = 0; j < 31; j++) {
            Day.addItem(j + 1);
        }
    }

    /**
     * This method is used to set font.
     * @param fontName
     * @param style
     * @param size
     * @param currentFont
     * @return a Font
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
}
