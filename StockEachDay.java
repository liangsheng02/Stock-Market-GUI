/**
 * This Class is used to store the six stock params (String) of each day.
 * They can be read into String/Double type by the getters.
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
     * @param Date String such as "01/01/2019"
     * @param Open String such as "129.13"
     * @param High String such as "129.13"
     * @param Low String such as "129.13"
     * @param Close String such as "129.13"
     * @param Volume String such as "34424"
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
