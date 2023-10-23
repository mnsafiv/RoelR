package ru.safonoviv.roelr.GenerateObject.Model;


import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.SkillArea;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class CharacterSkillArea {
    private static CharacterSkillArea characterSkillArea;
    private final Map<String, SkillArea> skillAreas;




    public static CharacterSkillArea getInstance() {
        CharacterSkillArea singleton = characterSkillArea;
        if (singleton == null) {
            synchronized (CharacterSkillArea.class) {
                if (characterSkillArea == null) {
                    characterSkillArea = new CharacterSkillArea();
                }
            }
        }
        return characterSkillArea;
    }

    private CharacterSkillArea() {
        skillAreas = new HashMap<>();

        skillAreas.put("base",new SkillArea(Collections.singletonList(true)));
        skillAreas.put("advance",new SkillArea(Arrays.asList(true,true,true)));
        skillAreas.put("circle",new SkillArea(Arrays.asList(false,true,true,true,true,true,true)));
    }

    public SkillArea getSkillAreas(String key) {
        return skillAreas.get(key);
    }
}
