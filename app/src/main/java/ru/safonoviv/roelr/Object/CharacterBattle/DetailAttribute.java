package ru.safonoviv.roelr.Object.CharacterBattle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.safonoviv.roelr.Object.CharacterModel.ArmorType;
import ru.safonoviv.roelr.Object.CharacterModel.AttackType;
import ru.safonoviv.roelr.Object.CharacterModel.JobType;
import ru.safonoviv.roelr.Object.CharacterModel.TierType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailAttribute {
    private int level;
    private int curExp;
    private int availableExp;
    private JobType jobType;
    private AttackType attackType;
    private ArmorType armorType;
    private TierType tierType;

}
