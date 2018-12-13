package refinitiv.TaskB4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskB4 {

    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("#,###");
        try {
            Path file = new File("src/main/resources/task_B4.txt").toPath();
            List<String> lines = Files.readAllLines(file);
            Map<String, Integer> countryGDP = new TreeMap<>();
            for (String line : lines) {
                if (line.startsWith("Country")){ //remove header
                    continue;
                }
                String[] valuePairs = line.split("\t");
                int gdp;
                try {
                    gdp = format.parse(valuePairs[1]).intValue();
                } catch (Exception e) {
                    System.out.println("Cannot parse line: " + line);
                    continue;
                }
                String country = valuePairs[0];
                //some countries contain garbage starting from '[' character
                if (country.contains("[")){
                    country = country.substring(0 , country.indexOf("["));
                }
                if (isPrime(gdp)){
                    countryGDP.put(country.trim(), gdp);
                }
            }

            //print countries in alphabetical order
            for (Map.Entry<String, Integer> entry : countryGDP.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static boolean isPrime(int n){
        if (n % 2 == 0){
            return false;
        }
        int border = (int) Math.sqrt(n);
        for (int i = 3; i < border; i+=2) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
