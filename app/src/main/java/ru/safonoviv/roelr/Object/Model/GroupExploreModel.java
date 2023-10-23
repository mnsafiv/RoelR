package ru.safonoviv.roelr.Object.Model;

import lombok.Getter;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class GroupExploreModel implements Serializable, CharacterModelGenerate {

    private final Long sequence;
    @Getter
    private int iconId;
    @Getter
    private final List<CharacterPrototype> characterPrototypes;


    public GroupExploreModel(int iconId, List<CharacterPrototype> characterPrototypes) {
        this.characterPrototypes = characterPrototypes;
        this.iconId = iconId;
        this.sequence = ModelSequence.sequence.incrementAndGet();
    }

    public GroupExploreModel() {
        this.iconId = DefaultValue.NO_EXIST_GROUP;
        this.sequence= ModelSequence.sequence.incrementAndGet();
        this.characterPrototypes = new ArrayList<>();
    }

    public GroupExploreModel(List<CharacterPrototype> characterPrototypes) {
        this.characterPrototypes = characterPrototypes;
        this.iconId = initIconId();
        this.sequence= ModelSequence.sequence.incrementAndGet();
    }

    private int initIconId() {
        if (characterPrototypes.isEmpty()) {
            return DefaultValue.NO_EXIST_GROUP;
        }
        AtomicInteger curGrade = new AtomicInteger(0);
        AtomicInteger curLevel = new AtomicInteger(0);
        AtomicInteger curId = new AtomicInteger(0);


        characterPrototypes.forEach(t -> curId.set(t.getICON_ID()));

        return curId.get();
    }


    public boolean add(CharacterPrototype characterModel) {
        if (characterPrototypes.add(characterModel)) {
            this.iconId = initIconId();
            return true;
        }
        return false;

    }

    public boolean remove(CharacterPrototype characterModel) {
        if (characterPrototypes.remove(characterModel)) {
            this.iconId = initIconId();
            return true;
        }
        return false;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupExploreModel that = (GroupExploreModel) o;
        return Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence);
    }
}
