package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.logging.SimpleFormatter;

public class RandomProductIterator implements Iterator<Map> {

    private static final SimpleDateFormat simpleFormatter = new SimpleDateFormat("YYYYMMDD");
    private static final Random random = new Random();



    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Map next() {
        String date = simpleFormatter.format(new Date());
        String No = simpleFormatter.format(new Date())+String.format("%06d",random.nextInt());
        return Map.of();
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Map> action) {
        Iterator.super.forEachRemaining(action);
    }
}
