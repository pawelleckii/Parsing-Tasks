package refinitiv.TaskA1.parsers;

import refinitiv.TaskA1.ColumnParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser implements ColumnParser {

    SimpleDateFormat[] avaibleFormats = {new SimpleDateFormat("dd-MM-yyyy"), new SimpleDateFormat("yyyy/MM/dd"), new SimpleDateFormat("ddMMyyyy'T'HHmm")};
    SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public String parse(String rawData) {
        //formatters[0].setLenient(false);

        for (SimpleDateFormat formatter : avaibleFormats) {
            try {
                Date parsedDate = formatter.parse(rawData);
                if (formatter.format(parsedDate).equals(rawData)) {
                    //System.out.println("++validated DATE TIME ++" + formatter.format(parsedDate));
                    return outFormat.format(parsedDate);
                }
            } catch (ParseException ignored) {
                //ignore exception
            }
        }
        return rawData;
    }

}
