package ru.safonoviv.roelr.Object.CharacterBattle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DetailStats implements Cloneable, Serializable {
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

    @NotNull
    @Override
    public DetailStats clone() {
        try {
            return (DetailStats) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
