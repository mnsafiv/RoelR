package ru.safonoviv.roelr.Object.CharacterBattle;

import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.GenerateObject.Component.PersonComponent;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;
import ru.safonoviv.roelr.Object.CharacterModel.Bonus;
import ru.safonoviv.roelr.Object.CharacterModel.TierType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculateStats {
    private static CalculateStats calculateStats;
    private final MultiplierApp multiplier = new MultiplierApp();


    private CalculateStats() {

    }

    public static CalculateStats getInstance() {
        if (calculateStats == null) {
            calculateStats = new CalculateStats();
        }
        return calculateStats;
    }

    public DetailStats calculateStatsByLevel(CharacterPrototype stats, int level) {

        MultiplierBonus bonusArmor = multiplier.getBonus(stats.getArmorType().name());
        MultiplierBonus bonusJob = multiplier.getBonus(stats.getJobType().name());
        MultiplierBonus bonusAttack = multiplier.getBonus(stats.getAttackType().name());
        MultiplierBonus bonusTier = multiplier.getBonus(stats.getTierType().name());


        Map<String, Double> currentBonus = Arrays.stream(Bonus.values())
                .collect(Collectors.toMap(String::valueOf, t -> 0.0, (a, b) -> b));

        Stream.of(bonusAttack, bonusArmor, bonusJob, bonusTier).forEach(t ->
                t.getBonuses().forEach((key, value) -> currentBonus.put(key, currentBonus.computeIfAbsent(key, n -> 0.0) + value)));

        DetailStats detailStats = new DetailStats();

        int hp = (int) (stats.getBaseStats().getHp() + stats.getIncreaseStats().getHp() * level);
        int atk = (int) (stats.getBaseStats().getAtk() + stats.getIncreaseStats().getAtk() * level);
        int def = (int) (stats.getBaseStats().getDef() + stats.getIncreaseStats().getDef() * level);
        int spd = (int) (stats.getBaseStats().getSpeed() + stats.getIncreaseStats().getSpeed() * level);

        detailStats.setDamagePhysical((int) (atk * currentBonus.computeIfAbsent(Bonus.multiplierPhysicalAttack.name(), n -> 0.0)));
        detailStats.setDamageMagic((int) (atk * currentBonus.computeIfAbsent(Bonus.multiplierMagicAttack.name(), n -> 0.0)));
        detailStats.setDamagePure((int) (atk * currentBonus.computeIfAbsent(Bonus.multiplierPureAttack.name(), n -> 0.0)));

        detailStats.setDefencePhysical((int) (def * currentBonus.computeIfAbsent(Bonus.multiplierPhysicalDefence.name(), n -> 0.0)));
        detailStats.setDefenceMagic((int) (def * currentBonus.computeIfAbsent(Bonus.multiplierMagicDefence.name(), n -> 0.0)));

        detailStats.setMoveCost((int) (currentBonus.computeIfAbsent(Bonus.moveCost.name(), n -> 0.0) * 1));
        detailStats.setSkillCost((int) (currentBonus.computeIfAbsent(Bonus.skillCost.name(), n -> 0.0) * 50));


        detailStats.setSkillDistance(10);
        detailStats.setHealth(hp);


        //need logic
        detailStats.setActivePoint(50);
        detailStats.setSkillPoint(100);
        detailStats.setMovePoint(100);

        detailStats.setMoveSpeed(spd / 20);
        detailStats.setSkillCast(1);


        return detailStats;
    }


    public int getProgressExp(int level, double multiplierProgress) {
        int toPrevLevel = (int) multiplierProgress;
        int exp = 0;
        for (int next = 0; level-- > 1; next++) {
            toPrevLevel = (int) (Math.sqrt(next * multiplierProgress) + toPrevLevel);
            exp += toPrevLevel;
        }
        return exp;
    }

    public int getProgressLevel(int exp, double multiplierProgress) {
        int level = 0;
        int toPrevLevel = (int) multiplierProgress;
        for (int next = 0; exp > 0; next++) {
            toPrevLevel = (int) (Math.sqrt(next * multiplierProgress) + toPrevLevel);
            exp -= toPrevLevel;
            level++;
        }
        return level;
    }

    public int getExpByLevel(int level, @NotNull TierType tierType) {
        Double multiplierProgress = multiplier.getBonus(tierType.name()).getBonuses().
                computeIfAbsent(Bonus.multiplierProgress.name(), n -> 0.0);
        return getProgressExp(level, multiplierProgress);
    }


    public void updateStatsByLevel(@NotNull CharacterPlayable characterPlayable, int level) {
        characterPlayable.getAttribute().setLevel(level);
        CharacterPrototype characterPrototype = PersonComponent.getInstance().getCharacterPrototype(characterPlayable.getKey());
        DetailStats detailStats = calculateStatsByLevel(characterPrototype, level);
        characterPlayable.setStats(detailStats);
    }

    public void updateAttributeByExp(@NotNull CharacterPlayable characterPlayable, int curExp) {
        Double multiplierProgress = multiplier.getBonus(characterPlayable.getAttribute().getTierType().name()).getBonuses().computeIfAbsent(Bonus.multiplierProgress.name(), n -> 0.0);
        int level = getProgressLevel(curExp, multiplierProgress);
        updateStatsByLevel(characterPlayable, level);
    }
}
