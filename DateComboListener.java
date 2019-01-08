import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

/**
 * This Class implements ItemListener to detect events of date ComboBox items.
 * If choose Feb, there would be 28 days in a leap year, and 29 days in a not leap year.
 * If choose Jan, Mar, May, Jul, Aug, Oct or Dec, there would be 31 days.
 * If choose Apr, Jun, Sep or Nov, there would be 30 days.
 * These are approached by removing all items in Day Combo, and then add items to it according to Month and Year.
 * @author Sheng Liang
 */
public class DateComboListener implements ItemListener{

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
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object objMonth = Month.getSelectedItem();//get selected month
        Object objYear = Year.getSelectedItem();//get selected year
        Day.removeAllItems();
        int month = Integer.valueOf(objMonth.toString());
        int year = Integer.valueOf(objYear.toString());
        if (month == 4 || month==6 || month==9 || month==11) {//30 days
            for (int i = 0; i < 30; i++) {
                Day.addItem(i + 1);
            }
        }
        else if (month == 1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {//31days
            for (int i = 0; i < 31; i++) {
                Day.addItem(i + 1);
            }
        }
        else if (month == 2 && year % 4 != 0 ) {//29 days in Feb in a Not leap year
            for (int i = 0; i < 29; i++) {
                Day.addItem(i + 1);
            }
        }
        else{
            for (int i = 0; i < 28; i++) {//28 days in Feb in a leap year
                Day.addItem(i + 1);
            }
        }
    }
}