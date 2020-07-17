package ru.zont.gamelogic.logic.objs;

import javafx.util.Pair;
import ru.zont.gamelogic.logic.DynamicName;

import java.util.Objects;

public abstract class Unit extends StatedObject implements Entity {
    protected int hp;
    protected boolean entity;

    public abstract String manuf();
    public abstract String label();
    public abstract String alias();
    public abstract String suffix();
    public abstract EquipSetup getEquip();

    @Override
    public DynamicName getName() {
        return NameBuilder.from(getClass()).buildDefault();
    }

    public Unit() {
        hp = getMaxHP();
        entity = false;
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void enableEntity() {
        entity = true;
    }

    @Override
    public void disableEntity() {
        entity = false;
    }

    @Override
    public boolean isEntity() {
        return entity;
    }

    @Override
    public boolean decrHP(int decr) {
        hp -= decr;
        return hp > 0;
    }

    @Override
    public void resetHP() {
        hp = getMaxHP();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return unit.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName().hashCode());
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

        @Override
        public String toString() {
            return String.format("%s [%s]", get().toString(), disp());
        }
    }
}
