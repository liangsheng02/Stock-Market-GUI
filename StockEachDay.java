/**
 * This Class is used to store the six stock params of each day.
 * @author Sheng Liang
 */
public class StockEachDay {

    private String Date;
    private String Open;
    private String High;
    private String Low;
    private String Close;
    private String Volume;

    public String getDate() {
        return Date;
    }
    public double getOpen() {
        return Double.parseDouble(Open);
    }
    public double getHigh() {
        return Double.parseDouble(High);
    }
    public double getLow() {
        return Double.parseDouble(Low);
    }
    public double getClose() {
        return Double.parseDouble(Close);
    }
    public double getVolume() {
        return Double.parseDouble(Volume);
    }

    /**
     * Constructor, input the six params of a stock each day.
     * @param Date
     * @param Open
     * @param High
     * @param Low
     * @param Close
     * @param Volume
     */
    public StockEachDay(String Date,String Open,String High,String Low,String Close,String Volume) {
        this.Date = Date;
        this.Open = Open;
        this.High = High;
        this.Low = Low;
        this.Close = Close;
        this.Volume = Volume;
    }
}
