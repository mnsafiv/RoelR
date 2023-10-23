package ru.safonoviv.roelr.GenerateObject.Component;


import ru.safonoviv.roelr.GenerateObject.Battle.Enum.ObjectType;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillBehaviorAfterCollide;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillCollision;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.SkillMoment;
import ru.safonoviv.roelr.GenerateObject.Model.CharacterBitmapModel;
import ru.safonoviv.roelr.GenerateObject.Model.CharacterSkillArea;
import ru.safonoviv.roelr.Object.CharacterModel.*;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;
import ru.safonoviv.roelr.Object.CharacterModel.ArmorType;
import ru.safonoviv.roelr.Object.CharacterModel.AttackType;
import ru.safonoviv.roelr.Object.CharacterModel.JobType;
import ru.safonoviv.roelr.Object.CharacterModel.TierType;

import java.util.*;

public class PersonComponent {
    private static PersonComponent personComponent;

    private final Map<String, CharacterPrototype> typeCharacters = new HashMap<>();
    private final Map<String, CharacterSkill> typeSkills = new HashMap<>();


    private PersonComponent() {
        initSkill();
        initCharacter();

    }

    public static PersonComponent getInstance() {
        PersonComponent singleton = personComponent;
        if (singleton == null) {
            synchronized (PersonComponent.class) {
                if (personComponent == null) {
                    personComponent = new PersonComponent();
                }
            }
        }
        return personComponent;
    }

    private void initCharacter() {

        String key = "Archer";
        Stats baseStats = new Stats(100, 30, 20, 100);
        Stats increaseStats = new Stats(5, 7, 2, 0.01);

        final CharacterBitmapModel characterBitmapModel = CharacterBitmapModel.getInstance();


        Set<CharacterSkill> skillSet = new HashSet<>();
        try {
            skillSet.add(getCharacterSkill("Arrow"));
            skillSet.add(getCharacterSkill("archerStrike"));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        typeCharacters.put(key, new Archer(key, characterBitmapModel.getBitmapId(key), AttackType.physical,
                ArmorType.light, JobType.range, skillSet, TierType.elite, baseStats, increaseStats));

        key = "Mage";
        baseStats = new Stats(70, 35, 35, 90);
        increaseStats = new Stats(10, 5, 5, 0.03);


        skillSet.clear();
        try {
            skillSet.add(getCharacterSkill("FireBall"));
            skillSet.add(getCharacterSkill("momentFireball"));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        typeCharacters.put(key, new Mage(key, characterBitmapModel.getBitmapId(key), AttackType.magical,
                ArmorType.light, JobType.mage, skillSet, TierType.elite, baseStats, increaseStats));


        key = "Cup";
        baseStats = new Stats(30, 10, 10, 115);
        increaseStats = new Stats(5, 3, 1, 0.01);

        skillSet.clear();
        try {
            skillSet.add(getCharacterSkill("PunchSimply"));
            skillSet.add(getCharacterSkill("SuperPunchSimply"));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        typeCharacters.put(key, new Cup(key, characterBitmapModel.getBitmapId(key), AttackType.magical, ArmorType.light, JobType.warrior, skillSet, TierType.common, baseStats, increaseStats));

        key = "Warrior";
        baseStats = new Stats(170, 50, 70, 130);
        increaseStats = new Stats(10, 3, 9, 0.015);

        skillSet.clear();
        try {
            skillSet.add(getCharacterSkill("PunchWarrior"));
            skillSet.add(getCharacterSkill("Slash"));
            skillSet.add(getCharacterSkill("ReversePunch"));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        typeCharacters.put(key, new Warrior(key, characterBitmapModel.getBitmapId(key), AttackType.magical, ArmorType.light, JobType.mage, skillSet, TierType.elite, baseStats, increaseStats));


        key = "LightMage";
        baseStats = new Stats(50, 50, 15, 115);
        increaseStats = new Stats(3, 9, 1, 0.05);

        skillSet.clear();
        try {
            skillSet.add(getCharacterSkill("ReverseFlyFireball"));
            skillSet.add(getCharacterSkill("TowerOfTower"));
            skillSet.add(getCharacterSkill("momentFireball"));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        typeCharacters.put(key, new LightMage(key, AttackType.magical, characterBitmapModel.getBitmapId(key), ArmorType.light, JobType.mage, skillSet, TierType.elite, baseStats, increaseStats));


    }

    private void initSkill() {

        int number = 1;

        //fireBall
        String key = "FireBall";
        CharacterSkill characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(150);
        characterSkill.setSkill_id(number++);
        characterSkill.setChargeRound(2);
        characterSkill.setChargeCapacity(5);
        characterSkill.setChargeStartCapacity(3);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's fireball");


        characterSkill.setObjectType(ObjectType.Throw);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.destroy);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);

        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);

        //Arrow
        key = "Arrow";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(0);
        characterSkill.setMultiplier(50);
        characterSkill.setSkill_id(1001);
        characterSkill.setChargeRound(15);
        characterSkill.setChargeCapacity(15);
        characterSkill.setChargeStartCapacity(15);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's arrow");


        characterSkill.setObjectType(ObjectType.Arrow);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.destroy);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);

        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);

        //momentFireball
        key = "momentFireball";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(50);
        characterSkill.setSkill_id(number++);
        characterSkill.setChargeRound(2);
        characterSkill.setChargeCapacity(5);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's Moment fireball");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.destroy);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);

        //archerStrike
        key = "archerStrike";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(20);
        characterSkill.setSkill_id(number++);
        characterSkill.setChargeRound(2);
        characterSkill.setChargeCapacity(5);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's Archer strike");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.destroy);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //Reverse fly fireball
        key = "ReverseFlyFireball";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(15);
        characterSkill.setSkill_id(number++);
        characterSkill.setChargeRound(2);
        characterSkill.setChargeCapacity(5);
        characterSkill.setChargeStartCapacity(3);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's reverse fly fireball");


        characterSkill.setObjectType(ObjectType.Reverse);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.destroy);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //towerOfFire
        key = "TowerOfTower";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(3);
        characterSkill.setMultiplier(75);
        characterSkill.setSkill_id(number++);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(1);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's tower of fire");


        characterSkill.setObjectType(ObjectType.Active);
        characterSkill.setSkillCollision(SkillCollision.High);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("circle"));
        typeSkills.put(key, characterSkill);


        //Push away
        key = "PushAway";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(3);
        characterSkill.setMultiplier(75);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(2);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's super ball");


        characterSkill.setObjectType(ObjectType.PushAway);
        characterSkill.setSkillCollision(SkillCollision.High);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //PunchSimply
        key = "PunchSimply";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(75);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(2);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's PunchSimply");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);

        //SuperPunchSimply
        key = "SuperPunchSimply";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(3);
        characterSkill.setMultiplier(150);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(1);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's SuperPunchSimply");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //PunchWarrior
        key = "PunchWarrior";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(1);
        characterSkill.setMultiplier(75);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(2);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's PunchWarrior");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //Slash
        key = "Slash";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(3);
        characterSkill.setMultiplier(250);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(2);
        characterSkill.setChargeCapacity(1);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's Slash");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.No);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.Moment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


        //ReversePunch
        key = "ReversePunch";
        characterSkill = new CharacterSkill();
        characterSkill.setSkillCostPoint(2);
        characterSkill.setMultiplier(100);
        characterSkill.setSkill_id(number);
        characterSkill.setChargeRound(1);
        characterSkill.setChargeCapacity(1);
        characterSkill.setChargeStartCapacity(1);

        characterSkill.setSkillName(key);
        characterSkill.setSkillDescription("It's ReversePunch");


        characterSkill.setObjectType(ObjectType.Passive);
        characterSkill.setSkillCollision(SkillCollision.High);
        characterSkill.setSkillBehaviorAfterCollide(SkillBehaviorAfterCollide.action);
        characterSkill.setSkillMoment(SkillMoment.NoMoment);


        characterSkill.setArea(CharacterSkillArea.getInstance().getSkillAreas("base"));
        typeSkills.put(key, characterSkill);


    }


    public CharacterPrototype getCharacterPrototype(String key) {
        return typeCharacters.get(key);
    }

    private CharacterSkill getCharacterSkill(String key) throws CloneNotSupportedException {
        return (CharacterSkill) Objects.requireNonNull(typeSkills.get(key)).clone();
    }


}
