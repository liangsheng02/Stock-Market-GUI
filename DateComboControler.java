import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

public class DateComboControler implements ItemListener{
    private JComboBox Month;
    private JComboBox Day;
    private JComboBox Year;

    /**
     *
     * @param Month
     * @param Year
     * @param Day
     */
    public DateComboControler(JComboBox Year, JComboBox Month, JComboBox Day) {

        this.Month = Month;
        this.Day = Day;
        this.Year = Year;
    }

    /**
     * Initialize the three drop down boxes for month, year and day.
     */
    public void AddItems() {
        // add 12 months
        for (int i = 0; i < 12; i++) {
            Month.addItem("" + (i + 1));
        }
        // add years from 2016 to 2019
        for (int i = 2016; i <= 2019; i++) {
            Year.addItem("" + i);
        }

        // add 31 days
        for (int j = 0; j < 31; j++) {
            Day.addItem("" + (j + 1));
        }
    }

    /**
     * Use Month to control Day elements, and check leap Year.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object objMonth = Month.getSelectedItem();// get selected month
        if (objMonth != null) {
            Day.removeAllItems();
            int month = Integer.valueOf(objMonth.toString());
            int day31 = 31;
            int day30 = 30;
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                Month.removeItem(day31);
            }
            else if (month == 2) {
                Month.removeItem(day31);
                Month.removeItem(day30);
            }//if
        }//if
        Object objYear = Year.getSelectedItem();// get selected year
        if (objYear != null) {
            int year = Integer.valueOf(objYear.toString());
            int month = Integer.valueOf(objYear.toString());
            int days = 29;
            if (month == 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) { // leap year
                Month.removeItem(days);
            }//if
        }//if
    }
}