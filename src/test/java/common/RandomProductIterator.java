package common;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomProductIterator implements Iterator<Map<String, Object>> {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");



    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Map next() {
        String date = LocalDateTime.now().format(DATE_FORMATTER);
        String orderNo = "TS"+date+
                String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return Map.of(
                "date", date,
                "orderNo", orderNo
        );
    }
}
