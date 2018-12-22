import java.util.ArrayList;
import java.util.Iterator;

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
            HighList.add(((StockEachDay) sed).getOpen());
        }
        return HighList;
    }

    public ArrayList<Double> getLowList() {
        LowList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            LowList.add(((StockEachDay) sed).getOpen());
        }
        return LowList;
    }

    public ArrayList<Double> getCloseList() {
        CloseList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            CloseList.add(((StockEachDay) sed).getOpen());
        }
        return CloseList;
    }

    public ArrayList<Double> getVolumeList() {
        VolumeList = new ArrayList<>();
        Iterator iterator = StockList.iterator();
        while (iterator.hasNext()) {
            sed = iterator.next();
            VolumeList.add(((StockEachDay) sed).getOpen());
        }
        return VolumeList;
    }

    public StockData(ArrayList<Object> StockList, String ticker_symbol) {
        this.StockList = StockList;
        this.ticker_symbol = ticker_symbol;
    }

}
