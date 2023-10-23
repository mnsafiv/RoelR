package ru.safonoviv.roelr.Object.CharacterModel;

import lombok.Getter;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public abstract class CharacterPrototype {
    private final String key;
    private final int ICON_ID;
    protected List<CharacterSkill> skills;
    protected TierType tierType;
    protected AttackType attackType;
    protected JobType jobType;
    protected ArmorType armorType;
    private final Stats baseStats;
    private final Stats increaseStats;


    public CharacterPrototype(String key, int icon_id, AttackType attackType,ArmorType armorType, JobType jobType, Set<CharacterSkill> skills, TierType tierType, Stats baseStats, Stats increaseStats) {
        this.attackType = attackType;
        this.key = key;
        this.ICON_ID = icon_id;
        this.armorType = armorType;
        this.jobType = jobType;
        this.skills = new ArrayList<>(skills);
        this.tierType = tierType;
        this.baseStats = baseStats;
        this.increaseStats = increaseStats;
    }

}