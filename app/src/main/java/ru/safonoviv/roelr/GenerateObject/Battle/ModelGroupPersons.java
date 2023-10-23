package ru.safonoviv.roelr.GenerateObject.Battle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ModelGroupPersons implements Serializable {
    private final List<CharacterPrototype> characterPrototypes;

}
