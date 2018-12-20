import javax.swing.*;
import java.awt.*;

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

    public JComboBox getStartMonthCombo() {
        return StartMonthCombo;
    }

    public JComboBox getStartDayCombo() {
        return StartDayCombo;
    }

    public JComboBox getStartYearCombo() {
        return StartYearCombo;
    }

    public JComboBox getEndMonthCombo() {
        return EndMonthCombo;
    }

    public JComboBox getEndDayCombo() {
        return EndDayCombo;
    }

    public JComboBox getEndYearCombo() {
        return EndYearCombo;
    }

    public JComboBox getStockCombo() {
        return StockCombo;
    }

    public JButton getGoButton() {
        return GoButton;
    }

    public GridBagConstraints getGbc() {
        return gbc;
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
     * Constructor, add components tp the CenterPanel.
     */
    public CenterPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(-12828863));
        Font CenterPanelFont = this.getFont(null, -1, -1, this.getFont());
        if (CenterPanelFont != null) this.setFont(CenterPanelFont);
        this.setForeground(new Color(-4539718));
        this.setInheritsPopupMenu(false);

        DateComboSetter dcc1 = new DateComboSetter(new JComboBox(), new JComboBox(), new JComboBox());
        DateComboSetter dcc2 = new DateComboSetter(new JComboBox(), new JComboBox(), new JComboBox());
        //Add StartMonthCombo
        StartMonthCombo = dcc1.getMonth();
        gbc = setGbc(1, 1, 17, 1, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        this.add(StartMonthCombo, gbc);
        //Add StartDayCombo
        StartDayCombo = dcc1.getDay();
        gbc = setGbc(2, 1, 17, 1, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(StartDayCombo, gbc);
        //Add StartYearCombo
        StartYearCombo = dcc1.getYear();
        gbc = setGbc(3, 1, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(StartYearCombo, gbc);
        //Add EndMonthCombo
        EndMonthCombo = dcc2.getMonth();
        gbc = setGbc(1, 2, 17, 2, 5, 10 ,5, 5); //anchor: WEST, fill: BOTH
        this.add(EndMonthCombo, gbc);
        //Add EndDayCombo
        EndDayCombo = dcc2.getDay();
        gbc = setGbc(2, 2, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(EndDayCombo, gbc);
        //Add EndYearCombo
        EndYearCombo = dcc2.getYear();
        gbc = setGbc(3, 2, 17, 2, 5, 10 ,5, 10); //anchor: WEST, fill: BOTH
        this.add(EndYearCombo, gbc);

        //Add StockCombo
        StockCombo = new JComboBox();
        StockCombo.setForeground(new Color(-12828863));
        final DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("APPL");
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
