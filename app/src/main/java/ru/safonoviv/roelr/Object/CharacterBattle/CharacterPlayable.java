package ru.safonoviv.roelr.Object.CharacterBattle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CharacterPlayable {
    private String key;
    private int keyId;
    private DetailStats stats;
    private List<CharacterSkill> skills;
    private DetailAttribute attribute;

    public CharacterPlayable(CharacterPrototype characterPrototype, int level) {
        this.stats = CalculateStats.getInstance().calculateStatsByLevel(characterPrototype, level);
        this.key = characterPrototype.getKey();
        this.keyId = characterPrototype.getICON_ID();
        this.skills = characterPrototype.getSkills();
        this.attribute = new DetailAttribute(level,
                CalculateStats.getInstance().getExpByLevel(level,characterPrototype.getTierType()),
                0,
                characterPrototype.getJobType(),
                characterPrototype.getAttackType(),
                characterPrototype.getArmorType(),
                characterPrototype.getTierType());
    }





}
