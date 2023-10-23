package ru.safonoviv.roelr.Object.CharacterBattle;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MultiplierBonus {
    private final Map<String, Double> bonuses;
}
