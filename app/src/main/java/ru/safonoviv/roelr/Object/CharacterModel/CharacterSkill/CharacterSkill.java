package ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.ObjectType;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillBehaviorAfterCollide;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillCollision;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillMoment;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CharacterSkill implements Cloneable, Serializable {
    private long id;
    private int skill_id;
    private int multiplier;
    private int skillCostPoint;
    private int chargeStartCapacity;
    private int chargeCurrentCapacity;
    private int chargeCapacity;
    private int chargeRound;

    private int area_id;
    private SkillArea area;
    private ObjectType objectType;
    private SkillCollision skillCollision;
    private SkillMoment skillMoment;
    private SkillBehaviorAfterCollide skillBehaviorAfterCollide;
    private String skillName;
    private String skillDescription;


    @NotNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



}
