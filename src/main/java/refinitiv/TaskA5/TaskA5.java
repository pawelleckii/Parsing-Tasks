package refinitiv.TaskA5;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class TaskA5 {

    private static final int BORDER_YEAR = 2015;
    private static final int SPEED_COLUMN = 6;

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            String hurricaneName = null;
            int maxSpeed = 0;
            Map<String, Integer> validHurricanes = new LinkedHashMap<>();
            while ((line = in.readLine()) != null) {
                if(isHeaderLine(line)){
                    maxSpeed = 0;
                    hurricaneName = getHurricaneName(line);
                } else {
                    if(hurricaneName == null){
                        continue;
                    }
                    int speed = getWindSpeedAfterYear(line, BORDER_YEAR);
                    if(speed > maxSpeed){
                        maxSpeed = speed;
                        validHurricanes.put(hurricaneName, maxSpeed);
                    }
                }
            }
            preetyPrint(validHurricanes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isHeaderLine(String line) {
        return Character.isAlphabetic(line.charAt(0));
    }

    /***
     * @param headerLine
     * @return hurricane name ending with "A" otherwise return null
     */
    private static String getHurricaneName(String headerLine) {
        if(!headerLine.contains("A")){
            return null;
        } else {
            String[] headerText = headerLine.split(",");
            String hurricaneName = headerText[1].trim();
            if(hurricaneName.endsWith("A")){
                return hurricaneName;
            }
        }
        return null;
    }

    /***
     * @param line
     * @return Wind speed in kts or -1 if year is smaller than year param
     */
    private static int getWindSpeedAfterYear(String line, int minYear) {
        String yearString = line.substring(0,4);
        int year = Integer.parseInt(yearString);
        if (year < minYear){
            return -1;
        }
        String[] records = line.split(",");
        return Integer.parseInt(records[SPEED_COLUMN].trim());
    }

    private static void preetyPrint(Map<String, Integer> map){
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " ktns");
        }
    }
}
