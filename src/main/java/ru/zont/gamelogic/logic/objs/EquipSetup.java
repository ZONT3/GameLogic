package ru.zont.gamelogic.logic.objs;

import java.util.ArrayList;
import java.util.Objects;

public class EquipSetup {
    private final ArrayList<Equipment> list;

    public EquipSetup(int slots) {
        list = new ArrayList<>();
        for (int i = 0; i < slots; i++)
            list.add(new Equipment());
    }

    public EquipSetup(int slots, Equipment... eq) {
        this(slots);
        if (slots > eq.length) throw new IndexOutOfBoundsException("");
        for (int i = 0; i < eq.length; i++)
            put(i, eq[i]);
    }

    public Equipment get(int i) {
        return list.size() <= i ? new Equipment() : list.get(i);
    }

    public boolean put(int i, Equipment eq) {
        if (i >= list.size()) return false;
        if (!list.get(i).getClass().equals(Equipment.class)) return false;
        list.set(i, eq);
        return true;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        for (Equipment eq : list)
            if (!eq.isEmpty()) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipSetup that = (EquipSetup) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    public static class Template {
        private final ArrayList<Class<? extends Equipment>> list;

        public Template(int slots) {
            list = new ArrayList<>();
            for (int i = 0; i < slots; i++)
                list.add(Equipment.class);
        }

        @SafeVarargs
        public Template(int slots, Class<? extends Equipment>... eq) {
            this(slots);
            for (int i = 0; i < eq.length; i++)
                put(i, eq[i]);
        }

        public boolean put(int i, Class<? extends Equipment> eq) {
            if (i >= list.size()) return false;
            if (!list.get(i).equals(Equipment.class)) return false;
            list.set(i, eq);
            return true;
        }

        public EquipSetup buildSetup() {
            Equipment[] arr = new Equipment[list.size()];
            for (int i = 0; i < list.size(); i++) {
                try {
                    arr[i] = list.get(i).newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Cannot create Equipment instance");
                }
            }
            return new EquipSetup(list.size(), arr);
        }
    }
}