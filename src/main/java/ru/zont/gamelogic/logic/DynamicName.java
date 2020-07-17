package ru.zont.gamelogic.logic;

import java.util.*;
import java.util.function.Consumer;

public class DynamicName implements Iterable<String> {
    private final ArrayList<String> list;

    public static class Builder {
        private final LinkedList<String> list;
        public Builder() {
            list = new LinkedList<>();
        }

        public Builder add(String str) {
            list.add(str);
            return this;
        }

        public DynamicName create() {
            return new DynamicName(list.toArray(new String[0]));
        }

    }
    /**
     * @param names From longest to shortest
     */
    public DynamicName(String... names) {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(names));
    }

    public int count() {
        return list.size();
    }

    public String get(int i) {
        return list.get(i);
    }

    public String shrt() {
        return list.get(list.size() - 1);
    }

    public String lng() {
        return list.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicName that = (DynamicName) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return lng();
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        list.forEach(action);
    }

    @Override
    public Spliterator<String> spliterator() {
        return list.spliterator();
    }
}
