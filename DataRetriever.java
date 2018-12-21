import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * This Class retrieves stock data by URL and store them as StockEachDay objects into an ArrayList.
 * @author Sheng Liang
 */
public class DataRetriever {

    private static String URL;
    private ArrayList<Object> Stock;

    /**
     * Constructor, input the three params of a stock to get its full URL.
     * @param ticker_symbol
     * @param startDate
     * @param endDate
     */
    public DataRetriever(String ticker_symbol, String startDate, String endDate) {
        this.URL = "https://quotes.wsj.com/" + ticker_symbol
                + "/historical-prices/download?MOD_VIEW=page&num_rows=2000&startDate="
                + startDate + "&endDate=" + endDate;
    }

    /**
     * This method retrieves data from the URL, and store each line as a StockEachDay object into an ArrayList.
     * @return ArrayList<Object> of the stock data.
     * @exception IOException On input error.
     */
    public ArrayList<Object> getStock() throws IOException {
        Stock = new ArrayList<>();
        URL obj = new URL(this.URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String eachLine = reader.readLine();
            boolean flag = false;
            while (eachLine != null) {
                if (flag == true){//Use a flag to skip the first line
                    String Each[] = eachLine.split(",");
                    StockEachDay eachDay = new StockEachDay(Each[0], Each[1], Each[2], Each[3], Each[4], Each[5]);
                    Stock.add(eachDay);
                }
                else{
                    flag = true;
                }
                eachLine = reader.readLine();
            }
            reader.close();
        } else {
            System.out.println("Wrong URL");
        }
        return Stock;
    }

    /**
     * Use for testing the DataRetriever
     * */
    public static void main(String[] args) throws IOException {
        DataRetriever dr=new DataRetriever("AAPL", "01/01/2018", "12/31/2018");
        ArrayList<Object> stock = dr.getStock();
        System.out.println(stock);
    }

}
