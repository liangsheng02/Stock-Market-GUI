import java.util.ArrayList;
import java.util.Iterator;

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
    private ArrayList<String> DateList;
    private ArrayList<Double> OpenList;
    private ArrayList<Double> HighList;
    private ArrayList<Double> LowList;
    private ArrayList<Double> CloseList;
    private ArrayList<Double> VolumeList;
    private Object sed;

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public ArrayList<String> getDateList() {
        DateList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            DateList.add(((StockEachDay) sed).getDate());
        }
        return DateList;
    }

    public ArrayList<Double> getOpenList() {
        OpenList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            OpenList.add(((StockEachDay) sed).getOpen());
        }
        return OpenList;
    }

    public ArrayList<Double> getHighList() {
        HighList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            HighList.add(((StockEachDay) sed).getHigh());
        }
        return HighList;
    }

    public ArrayList<Double> getLowList() {
        LowList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            LowList.add(((StockEachDay) sed).getLow());
        }
        return LowList;
    }

    public ArrayList<Double> getCloseList() {
        CloseList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            CloseList.add(((StockEachDay) sed).getClose());
        }
        return CloseList;
    }

    public ArrayList<Double> getVolumeList() {
        VolumeList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
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
