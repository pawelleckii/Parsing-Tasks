package refinitiv.TaskA1.parsers;

import refinitiv.TaskA1.ColumnParser;

import java.text.*;
import java.util.Locale;

public class FloatParser implements ColumnParser {

    DecimalFormat[] avaibleFormats = {new DecimalFormat("#,###.##"), new DecimalFormat("#.##"), setLocale(new DecimalFormat("#.##"))};

    private DecimalFormat setLocale(DecimalFormat decimalFormat) {
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMANY));
        return decimalFormat;
    }

    DecimalFormat outFormat = setLocale(new DecimalFormat("#.##"));

    @Override
    public String parse(String rawData) {
        for (DecimalFormat formatter : avaibleFormats) {
            try {
                Number parsedNumber = formatter.parse(rawData);
                if (formatter.format(parsedNumber).equals(rawData)) {
                    //System.out.println("++validated DATE TIME ++" + formatter.format(parsedDate));
                    return outFormat.format(parsedNumber);
                }
            } catch (ParseException ignored) {
                //ignore exception
            }
        }
        return rawData;

    }
}
