/**
 * This Class is used to store the six stock attributes of each day.
 * They can be read into String/Double type by the getters.
 * In case some data is missing, the getters would replace missing data with zero.
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
        if (Open == null){
            Open = 0 + "";
        }
        return Double.parseDouble(Open);
    }
    public double getHigh() {
        if (High == null){
            High = 0 + "";
        }
        return Double.parseDouble(High);
    }
    public double getLow() {
        if (Low == null){
            Low = 0 + "";
        }
        return Double.parseDouble(Low);
    }
    public double getClose() {
        if (Close == null){
            Close = 0 + "";
        }
        return Double.parseDouble(Close);
    }
    public double getVolume() {
        if (Volume == null){
            Volume = 0 + "";
        }
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
