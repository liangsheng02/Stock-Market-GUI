import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

/**
 * This Class is an ItemListener to detect events and initialise date items of the drop down boxes.
 * @author Sheng Liang
 */
public class DateComboControler implements ItemListener{

    private JComboBox Month, Day, Year;

    /**
     * Constructor, input the three params of three JComboBox.
     * @param Month
     * @param Year
     * @param Day
     */
    public DateComboControler(JComboBox Year, JComboBox Month, JComboBox Day) {

        this.Month = Month;
        this.Day = Day;
        this.Year = Year;
    }

    {
        AddItems();
    }

    /**
     * This method initialises the three drop down boxes for month, year and day.
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
     * This method checks special dates and leap year.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object objMonth = Month.getSelectedItem();// get selected month
        if (objMonth != null) {
            Day.removeAllItems();
            int month = Integer.valueOf(objMonth.toString());
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                Month.removeItemAt(32);
            }
            else if (month == 2) {
                Month.removeItemAt(32);
                Month.removeItemAt(31);
            }//if
        }//if
        Object objYear = Year.getSelectedItem();// get selected year
        if (objYear != null) {
            int year = Integer.valueOf(objYear.toString());
            int month = Integer.valueOf(objYear.toString());

            if (month == 2 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) { // Not leap year
                Month.removeItemAt(30);
            }//if
        }//if
    }
}