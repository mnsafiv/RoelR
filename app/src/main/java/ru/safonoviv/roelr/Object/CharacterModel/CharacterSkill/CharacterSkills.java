package ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterSkills implements Serializable {
    private List<CharacterSkill> skills=new ArrayList<>();

    public CharacterSkills(List<CharacterSkill> skills) {
        this.skills = skills;
    }

    public CharacterSkills() {

    }

    public List<CharacterSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<CharacterSkill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterSkills)) return false;
        CharacterSkills that = (CharacterSkills) o;
        return Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills);
    }
}
