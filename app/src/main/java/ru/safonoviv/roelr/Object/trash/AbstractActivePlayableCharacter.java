package ru.safonoviv.roelr.Object.trash;

import lombok.Getter;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.ObjectType;
import ru.safonoviv.roelr.Object.CharacterBattle.CharacterPlayable;
import ru.safonoviv.roelr.Object.CharacterModel.Team;
import ru.safonoviv.roelr.SearchWay.MotionVector;
import ru.safonoviv.roelr.SearchWay.PathWay;

import java.util.Set;


@Getter
public abstract class AbstractActivePlayableCharacter extends AbstractActivePlayable implements MotionVector {
    protected boolean useActivePoint;


    public AbstractActivePlayableCharacter(double positionX, double positionY, ObjectType type, Team team) {
        super(positionX, positionY, type, team);
    }

    public abstract PathWay getPath();

    public abstract Set<CharacterSkillPlayable> getSkills();


    public abstract boolean decreaseSkillPointAndIncreaseUse(CharacterSkillPlayable skill);


//    @Override
//    public void drawPanel(Canvas canvas) {
//
//    }

    @Override
    public boolean getCollisionAction(AbstractActivePlayable object) {
        System.out.println();
        return false;
    }





    @Override
    public boolean isActive() {
        return super.isActive();
    }

//    @Override
//    public AbstractActivePlayable getOwner() {
//        return null;
//    }

    public void setUseActivePoint(boolean useActivePoint) {
        this.useActivePoint = useActivePoint;
    }


    public abstract double getSizeX();

    public abstract double getSizeY();

    @Override
    public void isCollided(AbstractActivePlayable object) {
        System.out.println();

    }

    public abstract CharacterPlayable getCharacter();

    public abstract void nextRound();

    public abstract void updateStatus();
}
