package ru.safonoviv.roelr.Object.CharacterBattle;

import lombok.AllArgsConstructor;
import ru.safonoviv.roelr.Object.CharacterModel.*;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
public class MultiplierApp {
    private final Map<String, MultiplierBonus> maps;

    public MultiplierApp() {
        maps = new HashMap<>();
        Map<String, Double> common = new HashMap<>();
        Map<String, Double> elite = new HashMap<>();
        Map<String, Double> hero = new HashMap<>();
        common.put(Bonus.multiplierProgress.name(), 100.0);
        elite.put(Bonus.multiplierProgress.name(), 120.0);
        hero.put(Bonus.multiplierProgress.name(), 300.0);

        addBonus(common, TierType.common.name());
        addBonus(elite, TierType.elite.name());
        addBonus(hero, TierType.hero.name());

        Map<String, Double> pure = new HashMap<>();
        Map<String, Double> physical = new HashMap<>();
        Map<String, Double> magical = new HashMap<>();

        pure.put(Bonus.multiplierPureAttack.name(), 1.0);
        pure.put(Bonus.moveCost.name(), -1.0);
        pure.put(Bonus.skillCost.name(), -1.0);

        physical.put(Bonus.multiplierMagicAttack.name(), 0.5);
        physical.put(Bonus.multiplierPhysicalAttack.name(), 2.0);
        physical.put(Bonus.moveCost.name(), 2.0);
        physical.put(Bonus.skillCost.name(), -2.0);

        magical.put(Bonus.multiplierMagicAttack.name(), 2.0);
        magical.put(Bonus.multiplierPhysicalAttack.name(), 0.5);
        magical.put(Bonus.moveCost.name(), -2.0);
        magical.put(Bonus.skillCost.name(), 2.0);

        addBonus(pure, AttackType.pure.name());
        addBonus(physical, AttackType.physical.name());
        addBonus(magical, AttackType.magical.name());

        Map<String, Double> light = new HashMap<>();
        Map<String, Double> medium = new HashMap<>();
        Map<String, Double> heavy = new HashMap<>();

        light.put(Bonus.multiplierPhysicalDefence.name(), 0.5);
        light.put(Bonus.multiplierMagicDefence.name(), 2.0);

        medium.put(Bonus.multiplierPhysicalDefence.name(), 1.0);
        medium.put(Bonus.multiplierMagicDefence.name(), 1.0);

        heavy.put(Bonus.multiplierPhysicalDefence.name(), 2.0);
        heavy.put(Bonus.multiplierMagicDefence.name(), 1.0);

        addBonus(light, ArmorType.light.name());
        addBonus(medium, ArmorType.medium.name());
        addBonus(heavy, ArmorType.heavy.name());

        Map<String, Double> warriorJob = new HashMap<>();
        Map<String, Double> mageJob = new HashMap<>();
        Map<String, Double> rangeJob = new HashMap<>();
        rangeJob.put(Bonus.multiplierPhysicalAttack.name(), 1.0);
        rangeJob.put(Bonus.multiplierMagicAttack.name(), 0.1);
        rangeJob.put(Bonus.multiplierPureAttack.name(), 0.0);

        mageJob.put(Bonus.multiplierPhysicalAttack.name(), 0.1);
        mageJob.put(Bonus.multiplierMagicAttack.name(), 1.2);
        mageJob.put(Bonus.multiplierPureAttack.name(), 0.0);

        warriorJob.put(Bonus.multiplierPhysicalAttack.name(), 1.0);
        warriorJob.put(Bonus.multiplierMagicAttack.name(), 0.1);
        warriorJob.put(Bonus.multiplierPureAttack.name(), 0.0);

        addBonus(rangeJob, JobType.range.name());
        addBonus(mageJob,JobType.mage.name());
        addBonus(warriorJob,JobType.warrior.name());


    }

    public MultiplierBonus getBonus(String key) {
        MultiplierBonus multiplierBonus = maps.get(key);
        if (multiplierBonus == null) {
            throw new IllegalArgumentException("not contains key: " + key);
        }
        return multiplierBonus;
    }

    private void addBonus(Map<String, Double> bonuses, String name) {
        MultiplierBonus multiplierBonus = new MultiplierBonus(bonuses);
        MultiplierBonus put = maps.put(name, multiplierBonus);
        if (put != null) {
            throw new IllegalArgumentException("duplicate parameter bonus");
        }
    }


}
