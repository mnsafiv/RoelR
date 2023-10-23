package ru.safonoviv.roelr.Object.trash;


import lombok.Getter;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.ObjectType;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.Object.ActiveState;
import ru.safonoviv.roelr.Object.CharacterModel.Team;
import ru.safonoviv.roelr.SearchWay.FieldPrototype;


public abstract class AbstractActivePlayable extends AbstractActiveObject {
    protected boolean isActive;
    @Getter
    private final ObjectType type;
    @Getter
    private final Team team;
    @Getter
    private ActiveState state = ActiveState.wait;

    public AbstractActivePlayable(double positionX, double positionY, ObjectType type, Team team) {
        super(positionX, positionY, null);
        this.type = type;
        this.team = team;
    }

    public AbstractActivePlayable(AbstractActivePlayable owner, ObjectType type) {
        super(owner.getPositionX(), owner.getPositionY(), null);
        this.type = type;
        this.team = owner.getTeam();
    }


    public boolean getAction(boolean result) {
        return false;
    }

    public abstract double getSizeX();

    public abstract double getSizeY();

    public abstract boolean getTouchCollision(int coordinate);


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setState(ActiveState state) {
        this.state = state;
    }


    public abstract void nextRound();

    public abstract FieldPrototype getField();


//    public abstract List<StatusProperties> getStatus();

    //    public abstract CommonModelObject getModel();


    public abstract void isCollided(AbstractActivePlayable object);

    public abstract boolean getCollisionAction(AbstractActivePlayable object);

    public abstract double getMoveSpeed();
}
