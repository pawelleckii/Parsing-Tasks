package refinitiv.TaskA1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import refinitiv.TaskA1.parsers.BracketsRemover;
import refinitiv.TaskA1.parsers.DateParser;
import refinitiv.TaskA1.parsers.StringAndNumberParser;

import java.io.File;
import java.io.IOException;

public class TaskA1 {

    public static void main(String[] args) {

        try {
            File file = new File("src/main/resources/task_A1.html");
            Document doc = Jsoup.parse(file,  null);

            for (Element table : doc.select("table")) {
                for (Element row : table.select("tr")) {
                    Elements ths = row.select("th");
                    for(int i = 0; i<ths.size(); i++){
                        System.out.print(getParser(i).parse(ths.get(i).text()) + "\t\t");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ColumnParser getParser(int i) {
        switch(i) {
            case 0:
                return new DateParser();
            case 2:
                return new StringAndNumberParser();
            case 4:
            case 5:
            case 6:
            case 7:
                return new BracketsRemover();
            default:
               return ColumnParser.defaultParser;
        }
    }
}
