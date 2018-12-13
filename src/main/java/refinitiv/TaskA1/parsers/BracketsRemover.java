package refinitiv.TaskA1.parsers;

import refinitiv.TaskA1.ColumnParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketsRemover implements ColumnParser {

    private Pattern p = Pattern.compile("([\\w ]+)[(]\\d+[)]");

    @Override
    public String parse(String rawData) {
        rawData = rawData.replace('%', ' ').replace('+', 'Y').replace("Ã³", "O").replace('.', ' ').trim().toUpperCase();
        Matcher matcher = p.matcher(rawData);
        if(matcher.matches()){
            return matcher.group(1).trim();
        }
        return rawData;
    }
}
