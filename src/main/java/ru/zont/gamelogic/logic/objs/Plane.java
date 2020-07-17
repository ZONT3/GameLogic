package ru.zont.gamelogic.logic.objs;

public abstract class Plane extends Unit {
    public abstract Property<Integer> size();
    public abstract Property<Integer> speed();
    public abstract Property<Integer> man();
}
