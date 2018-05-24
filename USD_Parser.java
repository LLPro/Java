import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class USD_Parser {
    private static Document get_page() throws IOException {
       String url = "https://chel.74.ru/exchange/exchange.html";
       Document page = Jsoup.parse(new URL(url),3000);
       return page;
    }
    private static Pattern pater = Pattern.compile("\\d{2}\\W\\d{2}");

    private static String get_date(String string_date) throws Exception{
        Matcher matcher = pater.matcher(string_date);
        if (matcher.find())
            return matcher.group();
        throw new Exception("Can not found date");
    }

    public static void main (String[] args) throws Exception {
        Document page = get_page();
        Elements table = page.select("table[class=table_sales table_border_right]");
        String USD = table.select("td[class=block_position_center]").text();
        Element date = table.select("td[class=block_position_center]").get(3);
        String EUR = date.text();
        //System.out.println(table);
        System.out.println("USD = "+get_date(USD));
        System.out.println("EUR = "+get_date(EUR));
    }
}