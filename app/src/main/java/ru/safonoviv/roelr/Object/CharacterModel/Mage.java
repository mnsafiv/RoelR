package ru.safonoviv.roelr.Object.CharacterModel;

import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;

import java.util.Set;

public class Mage extends CharacterPrototype {


    public Mage(String key, int icon_id, AttackType attackType, ArmorType armorType, JobType jobType, Set<CharacterSkill> skills, TierType tierType, Stats baseStats, Stats increaseStats) {
        super(key, icon_id, attackType, armorType, jobType, skills, tierType, baseStats, increaseStats);
    }
}
