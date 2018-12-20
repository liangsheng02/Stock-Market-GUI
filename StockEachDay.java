/**
 * This Class is used to store the six stock data of each day.
 * @author Sheng Liang
 */
public class StockEachDay {

    private String Date, Open, High, Low, Close, Volume;

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

    public String getDate() {
        return Date;
    }
    public String getOpen() {
        return Open;
    }
    public String getHigh() {
        return High;
    }
    public String getLow() {
        return Low;
    }
    public String getClose() {
        return Close;
    }
    public String getVolume() {
        return Volume;
    }
}
