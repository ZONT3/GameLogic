package ru.zont.gamelogic.logic.objs;

import java.util.Random;

public abstract class Basic/* implements Cloneable*/ {
    protected static final Random rand = new Random(1337L);
    protected static long nextId = Long.MIN_VALUE;

    protected final long id;

    public Basic() {
        id = nextId++;
    }

    public Basic(boolean sample) {
        id = 0;
    }

    public final long getId() {
        return id;
    }

    public final boolean sameId(Basic o) {
        return getId() == o.getId();
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

//
//    @Override
//    public Basic clone() throws CloneNotSupportedException {
//        if (getClass().isAnonymousClass()) throw new CloneNotSupportedException("Object is anonymous");
//        try {
//            return getClass().newInstance();
//        } catch (IllegalAccessException | InstantiationException e) {
//            e.printStackTrace();
//            throw new CloneNotSupportedException();
//        }
//    }
}
