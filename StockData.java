import java.util.ArrayList;

/**
 * This class is used to store and read stock data.
 * It contains the ticker symbol, and an StockList during a period.
 * Data could be read into ArrayLists by using the getters in this class.
 * Each element of the StockList is a StockEachDay object, which contains 6 attributes.
 * (Actually this class could be merged with StockEachDay class, but it's more readable to be separated.)
 * @author Sheng Liang
 * */
public class StockData {

    private String ticker_symbol;
    private ArrayList<Object> StockList;

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public ArrayList<String> getDateList() {
        ArrayList<String> DateList = new ArrayList<>();
        for (Object sed : StockList){
            DateList.add(((StockEachDay) sed).getDate());
        }
        return DateList;
    }

    public ArrayList<Double> getOpenList() {
        ArrayList<Double> OpenList = new ArrayList<>();
        for (Object sed : StockList){
            OpenList.add(((StockEachDay) sed).getOpen());
        }
        return OpenList;
    }

    public ArrayList<Double> getHighList() {
        ArrayList<Double> HighList = new ArrayList<>();
        for (Object sed : StockList){
            HighList.add(((StockEachDay) sed).getHigh());
        }
        return HighList;
    }

    public ArrayList<Double> getLowList() {
        ArrayList<Double> LowList = new ArrayList<>();
        for (Object sed : StockList){
            LowList.add(((StockEachDay) sed).getLow());
        }
        return LowList;
    }

    public ArrayList<Double> getCloseList() {
        ArrayList<Double> CloseList = new ArrayList<>();
        for (Object sed : StockList){
            CloseList.add(((StockEachDay) sed).getClose());
        }
        return CloseList;
    }

    public ArrayList<Double> getVolumeList() {
        ArrayList<Double> VolumeList = new ArrayList<>();
        for (Object sed : StockList){
            VolumeList.add(((StockEachDay) sed).getVolume());
        }
        return VolumeList;
    }

    /**
     * Constructor
     * @param StockList ArrayList of StockEachDay objects
     * @param ticker_symbol String such as "FB"
     * */
    public StockData(ArrayList<Object> StockList, String ticker_symbol) {
        this.StockList = StockList;
        this.ticker_symbol = ticker_symbol;
    }
}
