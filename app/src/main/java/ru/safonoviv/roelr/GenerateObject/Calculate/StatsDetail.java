package ru.safonoviv.roelr.GenerateObject.Calculate;

import androidx.annotation.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class StatsDetail implements Cloneable, Serializable {
    private int health;
    private int damagePhysical;
    private int damageMagic;
    private int damagePure;
    private int defencePhysical;
    private int defenceMagic;
    private int activePoint;
    private int skillPoint;
    private int movePoint;
    private int skillDistance;
    private int moveCost;
    private int skillCost;
    private int moveSpeed;
    private int skillCast;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatsDetail)) return false;
        StatsDetail that = (StatsDetail) o;
        return health == that.health && damagePhysical == that.damagePhysical && damageMagic == that.damageMagic && damagePure == that.damagePure && defencePhysical == that.defencePhysical && defenceMagic == that.defenceMagic && activePoint == that.activePoint && skillPoint == that.skillPoint && movePoint == that.movePoint && skillDistance == that.skillDistance && moveCost == that.moveCost && skillCost == that.skillCost && moveSpeed == that.moveSpeed && skillCast == that.skillCast;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, damagePhysical, damageMagic, damagePure, defencePhysical, defenceMagic, activePoint, skillPoint, movePoint, skillDistance, moveCost, skillCost, moveSpeed, skillCast);
    }

    @NonNull
    @NotNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int getProgressExp() {
        return health/10+(damageMagic+damagePhysical+defencePhysical+defenceMagic)/3;
    }
}
