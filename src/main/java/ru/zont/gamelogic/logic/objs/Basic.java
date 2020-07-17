package ru.zont.gamelogic.logic.objs;

import java.util.Objects;
import java.util.Random;

public abstract class Basic implements Cloneable {
    protected static final Random rand = new Random(1337L);
    protected static long nextId = Long.MIN_VALUE;

    protected final long id;
    protected final boolean sample;

    public Basic() {
        this(false);
    }

    public Basic(boolean sample) {
        this.sample = sample;
        id = sample ? 0 : nextId++;
    }

    public long getId() {
        return id;
    }

    public boolean sameId(Basic o) {
        return getId() == o.getId();
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basic)) return false;
        Basic basic = (Basic) o;
        return sample == basic.sample;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sample);
    }

    @Override
    public Basic clone() throws CloneNotSupportedException {
        if (getClass().isAnonymousClass()) throw new CloneNotSupportedException("Object is anonymous");
        try {
            return getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
    }
}
