package ru.zont.gamelogic.logic.objs;

import javafx.util.Pair;
import ru.zont.gamelogic.logic.DynamicName;

import java.util.ArrayList;
import java.util.LinkedList;

public class NameBuilder {
    public static final int LABEL_IMP = 0;
    public static final int ALIAS_IMP = 1;
    public static final int ALIAS_MANUF_IMP = 3;

    private final Unit u;
    private final ArrayList<Pair<String, Integer>> nameMap;

    public static NameBuilder from(Class<? extends Unit> cls) {
        try {
            return from(cls.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return from((Unit) null);
        }
    }

    public static NameBuilder from(Unit unit) {
        return new NameBuilder(unit);
    }

    private NameBuilder(Unit unit) {
        u = unit;
        nameMap = new ArrayList<>();
    }

    public void addName(String name, int weight) {
        if (name == null) return;
        nameMap.add(new Pair<>(name, weight));
    }

    public DynamicName build() {
        if (nameMap.isEmpty()) return new DynamicName("");

        LinkedList<String> result = new LinkedList<>();
        Pair<String, Integer> candidate = null;
        while (!nameMap.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Pair<String, Integer> p: nameMap) {
                if (candidate == null || p.getValue() < candidate.getValue())
                    candidate = p;
                builder.append(builder.length() > 0 ? " " : "")
                        .append(p.getKey());
            }
            nameMap.remove(candidate);
            candidate = null;
            result.add(builder.toString());
        }
        return new DynamicName(result.toArray(new String[0]));
    }

    public DynamicName buildDefault() {
        return buildTemplate(LABEL_IMP);
    }

    public DynamicName buildTemplate(int template) {

        if (u == null) return new DynamicName("NameBuilder ERROR! <NPE>");
        switch (template) {
            case ALIAS_MANUF_IMP:
                addName(u.manuf(), 1);
                addName(u.label(), 0);
                addName(u.alias(), 3);
                addName(u.suffix(), 2);
                return build();
            case ALIAS_IMP:
                addName(u.manuf(), 0);
                addName(u.label(), 1);
                addName(u.alias(), 3);
                addName(u.suffix(), 2);
                return build();
            case LABEL_IMP:
            default:
                addName(u.manuf(), 0);
                addName(u.label(), 3);
                addName(u.alias(), 1);
                addName(u.suffix(), 2);
                return build();
        }
    }
}
