public class StockEachDay {

    public StockEachDay(String Date,String Open,String High,String Low,String Close,String Volume) {
        this.Date = Date;
        this.Open = Open;
        this.High = High;
        this.Low = Low;
        this.Close = Close;
        this.Volume = Volume;
    }

    private String Date, Open, High, Low, Close, Volume;

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
