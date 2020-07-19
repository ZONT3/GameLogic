package ru.zont.gamelogic.logic.objs;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class EquipPatterns {
    private final ArrayList<Pair<EquipSetup, Integer>> patterns;
    private int totalW;
    protected Random rand;

    public EquipPatterns() {
        this.patterns = new ArrayList<>();
        totalW = 0;
        rand = new Random();
    }

    @SafeVarargs
    public EquipPatterns(Pair<EquipSetup, Integer>... patterns) {
        this();
        for (Pair<EquipSetup, Integer> s: patterns)
            add(s.getKey(), s.getValue());
    }

    public EquipPatterns(EquipSetup... patterns) {
        this();
        for (EquipSetup s: patterns)
            add(s, 1);
    }

    public EquipPatterns setSeed(long seed) {
        rand.setSeed(seed);
        return this;
    }

    public void add(EquipSetup pattern, Integer weight) {
        patterns.add(new Pair<>(pattern, weight));
        totalW += weight;
    }

    public EquipSetup get(Unit u) {
        EquipSetup r = get();
        return r == null ? new EquipSetup(u) : r;
    }

    public EquipSetup get() {
        if (patterns.isEmpty()) return null;

        int targ = rand.nextInt(totalW), i = 0;
        for (Pair<EquipSetup, Integer> pattern : patterns) {
            i += pattern.getValue();
            if (i >= targ) return pattern.getKey();
        }
        throw new RuntimeException("WTF exception");
    }
}
