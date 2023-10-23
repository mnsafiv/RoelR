package ru.safonoviv.roelr.GenerateObject.Component;


import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;
import ru.safonoviv.roelr.Object.CharacterModel.Stats;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;
import ru.safonoviv.roelr.Object.CharacterModel.ArmorType;
import ru.safonoviv.roelr.Object.CharacterModel.AttackType;
import ru.safonoviv.roelr.Object.CharacterModel.JobType;
import ru.safonoviv.roelr.Object.CharacterModel.TierType;

import java.util.Set;

public class LightMage extends CharacterPrototype {

    public LightMage(String key, AttackType attackType, int icon_id, ArmorType armorType, JobType jobType, Set<CharacterSkill> skills, TierType tierType, Stats baseStats, Stats increaseStats) {
        super(key, icon_id, attackType, armorType, jobType, skills, tierType, baseStats, increaseStats);
    }
}
