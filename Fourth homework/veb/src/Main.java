import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Main {
        public static void main(String[] args) throws Exception {
                Document v1 = Jsoup.connect("https://weather.rambler.ru/v-sankt-peterburge/").userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
                Document v2 = Jsoup.connect("https://pogoda7.ru/prognoz/gorod134243-Russia-g_Sankt_Peterburg-Sankt_Peterburg/14days/full").userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
                Document v3 = Jsoup.connect("https://rp5.ru/Погода_в_Санкт-Петербурге").userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
                Document v = Jsoup.connect("https://www.gismeteo.ru/weather-sankt-peterburg-4079/").userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
                Elements veb1 = null, veb2 = null, veb3 = null, veb = null;
                try {
                    veb1 = v1.select("div.aLlQ");
                    veb2 = v2.select("div.temperature");
                    veb3 = v3.select("div.ArchiveTemp");
                    veb = v.select("div.weather-value");

                    String site1 = veb1.text(), site2 = veb2.text(), site3 = veb3.text(), ex = veb.text();


                    int[] info = new int[5];
                    info[0] = Parse(ex);
                    info[1] = Parse(site1);
                    info[2] = Parse(site2);
                    info[3] = Parse(site3);
                    double reg = info[1]*(-0.09)+info[2]*0.54+info[3]*0.54 + 1.8;
                    info[4] = (int) reg;
                    new Desktop(info);
                    GrGis gr = new GrGis("Гистограмма",info);
                    gr.setVisible(true);

                } catch (Throwable cause ) {
                    cause.printStackTrace();
                }
            }
        public static int Parse(String site){
            String d = "";
            for (int i = 0; i < site.length();i++){
                char c = site.charAt(i);
                if(c == ' ' || c == ','){
                    site = d;
                    break;
                }
                if(c >= '0' && c <= '9'){
                    d = d + c;
                }

            }
            return Integer.parseInt(d);
        }
}

