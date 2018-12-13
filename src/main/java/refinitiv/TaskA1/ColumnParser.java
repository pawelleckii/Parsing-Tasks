package refinitiv.TaskA1;

public interface ColumnParser {

    String parse(String rawData);

    ColumnParser defaultParser = new ColumnParser() {
        @Override
        public String parse(String rawData) {
            return rawData.toUpperCase().trim();
        }
    };
}
