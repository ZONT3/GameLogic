package ru.zont.gamelogic.logic.objs;

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
}
