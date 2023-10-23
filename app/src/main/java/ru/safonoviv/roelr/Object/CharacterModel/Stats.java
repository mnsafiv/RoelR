package ru.safonoviv.roelr.Object.CharacterModel;

import lombok.Getter;

@Getter
public class Stats {
    private double hp;
    private double atk;
    private double def;
    private double speed;

    public Stats(double hp, double atk, double def, double speed) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
    }
}