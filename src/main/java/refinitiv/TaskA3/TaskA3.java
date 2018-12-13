package refinitiv.TaskA3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskA3 {

    private static List<EnergyStatistics> today = new ArrayList<>(24);
    private static List<EnergyStatistics> tomorrow = new ArrayList<>(24);

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("http://www.mercado.ren.pt/EN/Electr/MarketInfo/Gen/Pages/Forecast.aspx").timeout(30000).get();

            for (Element table : doc.select("tr.trIMPAR, .trPAR")) {
                Elements tds = table.select("td");
                boolean isToday = tds.size() == 5;
                if(tds.size() == 5){
                    tds.remove(0);
                }
                EnergyStatistics statistics = new EnergyStatistics(
                        Integer.parseInt(tds.get(0).text()),
                        Integer.parseInt(tds.get(1).text()),
                        Integer.parseInt(tds.get(2).text()),
                        tds.hasClass("txtPREV"));

                if(isToday){
                    today.add(statistics);
                } else {
                    tomorrow.add(statistics);
                }
            }
            System.out.println("\n\nTODAY STATISTICS:\n");
            for(int hr = 0; hr < today.size(); hr++){
                EnergyStatistics statistics = today.get(hr);
                System.out.println( (statistics.isForecast()? "Forecast:" : "Actual:") + "\t" + printHour(hr+1) + "\t" + statistics);
            }
            System.out.println("\n\nTOMORROW STATISTICS:\n");
            for(int hr = 0; hr < tomorrow.size(); hr++){
                EnergyStatistics statistics = tomorrow.get(hr);
                System.out.println( (statistics.isForecast()? "Forecast:" : "Actual:") + "\t" + printHour(hr+1) + "\t" + statistics);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String printHour(int hour){
        return hour >= 10? "" + hour : "0" + hour;
    }
    private static class EnergyStatistics{
        private int wind;
        private int solar;
        private int others;
        private boolean isForecast;

        public EnergyStatistics(int wind, int solar, int others, boolean isForecast) {
            this.wind = wind;
            this.solar = solar;
            this.others = others;
            this.isForecast = isForecast;
        }

        @Override
        public String toString() {
            return "Wind:" + wind + "\tSolar:" + solar + "\tOthers:" + others;
        }

        public boolean isForecast() {
            return isForecast;
        }
    }

}
