package ru.zont.gamelogic.logic.objs;

import ru.zont.gamelogic.logic.DynamicName;

import java.util.Objects;

public abstract class Unit extends StatedObject implements Entity {
    protected int hp;
    protected boolean entity;
    protected EquipSetup equip;

    public abstract String manuf();
    public abstract String label();
    public abstract String alias();
    public abstract String suffix();
    public abstract EquipPatterns defaultEquip();
    public abstract Property<Integer> eqSlots();

    public final EquipSetup getEquip() {
        if (equip == null) equip = defaultEquip().get();
        if (equip == null) equip = new EquipSetup(eqSlots().get());
        return equip;
    }

    @Override
    public final DynamicName getName() {
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
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName().hashCode());
    }

}
