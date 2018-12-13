package refinitiv.TaskA1.parsers;

import refinitiv.TaskA1.ColumnParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAndNumberParser implements ColumnParser {

    Pattern p = Pattern.compile("([\\w ]+)[(]([\\d,.]+)[)]");

    @Override
    public String parse(String rawData) {
        rawData = rawData.trim().toUpperCase();
        Matcher matcher = p.matcher(rawData);
        if(matcher.matches()){

            return matcher.group(1) + "(" + printNumber(matcher.group(2)) + ")";
        }
        return rawData;
    }

    private String printNumber(String group) {
        return new FloatParser().parse(group);
    }
}
