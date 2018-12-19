public class text {
    public text(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
        String url_start = "http://quotes.wsj.com/";
        String url_end = "/historical-prices/download?MOD_VIEW=page&num_rows=300&startDate=01/01/2018&endDate=12/31/2018";
        this. GET_URL = url_start + ticker_symbol + url_end;
        System.out.println(GET_URL);
    }

}