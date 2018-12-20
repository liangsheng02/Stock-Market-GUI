import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MarketGUI {
    private JPanel MainPanel;
    private JPanel NorthPanel;
    private MessagePanel mPanel;
    private JPanel CenterPanel;
    private JComboBox StockCombo;
    private JComboBox StartMonthCombo;
    private JComboBox StartDayCombo;
    private JComboBox StartYearCombo;
    private JComboBox EndMonthCombo;
    private JComboBox EndDayCombo;
    private JComboBox EndYearCombo;
    private JButton GoButton;

    public MarketGUI() {
        GoButton.addActionListener(new ActionListener() {
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
                            Date today = new Date();
                            if (end.after(today)) { //Error1
                                System.out.println("End Date Error: later than today.");
                                mPanel.setDisplayString("End Date Error: later than today.");
                            } else if (start.after(end)) { // Error2
                                System.out.println("Start Date Error: later than End Date.");
                                mPanel.setDisplayString("Start Date Error: later than End Date.");
                            } else { //OK
                                DataRetriever dr = new DataRetriever(choStock, choStartDate, choEndDate);
                                //ArrayList<Object> Stock = dr.getStock();
                                //System.out.println(Stock);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace(System.err);
                        }
                    } else { //Error3
                        System.out.println("Please select stock, start date and end date.");
                        mPanel.setDisplayString("Please select stock, start date and end date.");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MarketGUI");
        frame.setContentPane(new MarketGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println("frame: "+frame.getSize());
        //frame.setResizable(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        setupUI();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void setupUI() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());
        MainPanel.setForeground(new Color(-12828863));
        GridBagConstraints MainGBC=new GridBagConstraints();

        System.out.println("MainPanel: "+MainPanel.getSize());
        //North
        NorthPanel = new JPanel();
        NorthPanel.setLayout(new GridBagLayout());

        System.out.println("NorthPanel: "+NorthPanel.getSize());

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
        mPanel.setDisplayString("Hello world");
        MainPanel.add(mPanel, MainGBC);

        System.out.println("SouthPanel: "+ mPanel.getSize());

        mPanel.setPreferredSize(new Dimension(100, 40));

        //Center
        CenterPanel = new JPanel();
        CenterPanel.setLayout(new GridBagLayout());
        CenterPanel.setBackground(new Color(-12828863));
        Font CenterPanelFont = this.getFont(null, -1, -1, CenterPanel.getFont());
        if (CenterPanelFont != null) CenterPanel.setFont(CenterPanelFont);
        CenterPanel.setForeground(new Color(-4539718));
        CenterPanel.setInheritsPopupMenu(false);

        MainGBC.gridx=0;
        MainGBC.gridy=1;
        MainGBC.weightx=1;
        MainGBC.weighty=10;
        MainGBC.fill=GridBagConstraints.BOTH;
        MainPanel.add(CenterPanel, MainGBC);

        StartMonthCombo = new JComboBox();
        StartMonthCombo.setInheritsPopupMenu(false);
        StartMonthCombo.setMinimumSize(new Dimension(198, 30));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("7");
        defaultComboBoxModel1.addElement("12");
        StartMonthCombo.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 5);
        CenterPanel.add(StartMonthCombo, gbc);
        StartDayCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("1");
        StartDayCombo.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        CenterPanel.add(StartDayCombo, gbc);
        StartYearCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("2010");
        defaultComboBoxModel3.addElement("2012");
        StartYearCombo.setModel(defaultComboBoxModel3);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        CenterPanel.add(StartYearCombo, gbc);
        EndDayCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("12");
        defaultComboBoxModel4.addElement("31");
        EndDayCombo.setModel(defaultComboBoxModel4);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        CenterPanel.add(EndDayCombo, gbc);
        EndYearCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("2010");
        defaultComboBoxModel5.addElement("2009");
        EndYearCombo.setModel(defaultComboBoxModel5);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        CenterPanel.add(EndYearCombo, gbc);
        final JLabel label3 = new JLabel();
        label3.setBackground(new Color(-4539718));
        Font label3Font = this.getFont("Arial Black", -1, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-4539718));
        label3.setText("Start Date :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 0, 0, 5);
        CenterPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-4539718));
        Font label4Font = this.getFont("Arial Black", -1, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-4539718));
        label4.setText("End Date   :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 0, 0, 5);
        CenterPanel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-4539718));
        Font label5Font = this.getFont("Arial Black", -1, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-4539718));
        label5.setText("Month");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        CenterPanel.add(label5, gbc);
        final JLabel label6 = new JLabel();
        label6.setBackground(new Color(-4539718));
        Font label6Font = this.getFont("Arial Black", -1, -1, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-4539718));
        label6.setText("Day");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        CenterPanel.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-4539718));
        Font label7Font = this.getFont("Arial Black", -1, -1, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-4539718));
        label7.setText("Year");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        CenterPanel.add(label7, gbc);
        EndMonthCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel6 = new DefaultComboBoxModel();
        defaultComboBoxModel6.addElement("8");
        defaultComboBoxModel6.addElement("10");
        EndMonthCombo.setModel(defaultComboBoxModel6);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 5);
        CenterPanel.add(EndMonthCombo, gbc);
        StockCombo = new JComboBox();
        StockCombo.setAutoscrolls(false);
        StockCombo.setFocusable(false);
        StockCombo.setForeground(new Color(-12828863));
        final DefaultComboBoxModel defaultComboBoxModel7 = new DefaultComboBoxModel();
        defaultComboBoxModel7.addElement("APPL");
        defaultComboBoxModel7.addElement("FB");
        defaultComboBoxModel7.addElement("GOOG");
        defaultComboBoxModel7.addElement("YHOO");
        StockCombo.setModel(defaultComboBoxModel7);
        StockCombo.setSelectedIndex(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);
        CenterPanel.add(StockCombo, gbc);
        GoButton = new JButton();
        GoButton.setBackground(new Color(-12828863));
        Font GoButtonFont = this.getFont("Arial Black", -1, -1, GoButton.getFont());
        if (GoButtonFont != null) GoButton.setFont(GoButtonFont);
        GoButton.setForeground(new Color(-4539718));
        GoButton.setText("Go !");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);
        CenterPanel.add(GoButton, gbc);
        final JLabel label8 = new JLabel();
        label8.setBackground(new Color(-4539718));
        Font label8Font = this.getFont("Arial Black", -1, 16, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-4539718));
        label8.setHorizontalTextPosition(0);
        label8.setText("Find a Stock  :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 5);
        CenterPanel.add(label8, gbc);
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
