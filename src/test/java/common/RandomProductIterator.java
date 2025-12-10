package common;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class RandomProductIterator implements Iterator<Map> {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Map next() {
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
