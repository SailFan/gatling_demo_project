package common;

import java.util.Iterator;
import java.util.Map;

public class OrderIterator implements Iterator<Map> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Map next() {
        return Map.of();
    }
}
