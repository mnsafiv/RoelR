package ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SkillArea implements Serializable {
    private final List<Boolean> area;

    public SkillArea(List<Boolean> area) {
        this.area = new ArrayList<>(area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillArea)) return false;
        SkillArea skillArea = (SkillArea) o;
        return Objects.equals(area, skillArea.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }
}
