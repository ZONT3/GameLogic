package ru.zont.gamelogic.logic.objs;

import javafx.util.Pair;
import ru.zont.gamelogic.logic.DynamicName;

import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class StatedObject extends Basic {
    public abstract DynamicName getName();

    public String shortName() {
        return getName().shrt();
    }

    public String longName() {
        return getName().lng();
    }

    /**
     * Alias of longName()
     */
    @Override
    public String toString() {
        return longName();
    }

    public HashMap<String, Unit.Property<?>> getStats() {
        HashMap<String, Unit.Property<?>> res = new HashMap<>();
        try {
            for (Method m : getClass().getMethods())
                if (m.getReturnType().equals(Unit.Property.class))
                    res.put(m.getName(), (Unit.Property<?>) m.invoke(this));
        } catch (Exception e) {
            throw new RuntimeException("Bad property method in object " + getClass().getSimpleName(), e);
        }
        return res;
    }

    public static class Property<T> {
        private final T val;
        private Pair<T, T> range;

        public Property(T val) {
            this.val = val;
        }

        public Property(T val, T disp) {
            this.val = val;
            range = new Pair<>(disp, disp);
        }

        public Property(T val, T min, T max) {
            this.val = val;
            range = new Pair<>(min, max);
        }

        public T get() { return val; }
        public T min() { return range == null ? val : range.getKey(); }
        public T max() { return range == null ? val : range.getValue(); }
        public String disp() { return range != null
                ? ( range.getValue() != range.getKey() ? range.getKey() + "-" + range.getValue() : range.getKey()+"" )
                : val+""; }

        public boolean equals(Object o) {
            if (super.equals(o)) return true;
            if (o == null) return false;
            if (o.getClass().isInstance(val)) return o.equals(val);
            if (o instanceof Property) {
                Property prop = (Property) o;
                return val.equals(prop.val)
                        && (range == null && prop.range == null || range.equals(prop.range));
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("%s [%s]", get().toString(), disp());
        }
    }
}
