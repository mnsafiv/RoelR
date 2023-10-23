package ru.safonoviv.roelr.GenerateObject.Bonus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BonusValue {
    private final Set<BonusValueItem> bonusItems;


    public BonusValue(BonusType type, double multiplier) {
        bonusItems=new HashSet<>();
        bonusItems.add(new BonusValueItem(type, multiplier));
    }

    public void add(BonusType type, double value) {
        bonusItems.add(new BonusValueItem(type, value));
    }

    public Map<BonusType, Double> getBonusItems() {
        Map<BonusType, Double> items = new HashMap<>();
        bonusItems.forEach(t-> items.put(t.type,t.multiplier));
        return items;
    }

    static class BonusValueItem{
        BonusType type;
        double multiplier;

        public BonusValueItem(BonusType type, double multiplier) {
            this.type = type;
            this.multiplier = multiplier;
        }
    }
}
