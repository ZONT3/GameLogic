package ru.zont.gamelogic.logic.objs;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class EquipPatterns {
    private final ArrayList<Pair<EquipSetup.Template, Integer>> patterns;
    private int totalW;
    protected Random rand;

    private EquipPatterns() {
        this.patterns = new ArrayList<>();
        totalW = 0;
        rand = new Random();
    }

    /**
     * Equipment setup patterns.
     * @param patterns Integer in pair - weight of pattern.
     *                 When there is more than one pattern, method get() will return a random one,
     *                 depends on that property.
     */
    @SafeVarargs
    public EquipPatterns(Pair<EquipSetup.Template, Integer>... patterns) {
        this();
        for (Pair<EquipSetup.Template, Integer> s: patterns)
            add(s.getKey(), s.getValue());
    }

    public EquipPatterns(EquipSetup.Template... patterns) {
        this();
        for (EquipSetup.Template s: patterns)
            add(s, 1);
    }

    public EquipPatterns setSeed(long seed) {
        rand.setSeed(seed);
        return this;
    }

    public void add(EquipSetup.Template pattern, Integer weight) {
        patterns.add(new Pair<>(pattern, weight));
        totalW += weight;
    }

    public EquipSetup get() {
        if (patterns.isEmpty()) throw new RuntimeException("Patterns is empty");

        int targ = rand.nextInt(totalW), i = 0;
        for (Pair<EquipSetup.Template, Integer> pattern : patterns) {
            i += pattern.getValue();
            if (i > targ) return pattern.getKey().buildSetup();
        }
        throw new RuntimeException("WTF exception");
    }
}
