package ru.safonoviv.roelr.GenerateObject.Component;


import ru.safonoviv.roelr.Object.Model.GroupExploreModel;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeDifficult;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeEnvironment;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeGrade;
import ru.safonoviv.roelr.Object.CharacterExplore.BattleMapType;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;

import java.util.ArrayList;
import java.util.List;


public class GroupComponent {
    private static volatile GroupComponent groupComponent;

    private GroupComponent() {
    }

    public GroupComponent getInstance() {
        GroupComponent singleton = groupComponent;
        if (singleton == null) {
            synchronized (GroupComponent.class) {
                if (groupComponent == null) {
                    groupComponent = new GroupComponent();
                }
            }
        }
        return groupComponent;
    }

    public static GroupExploreModel getMapGroupModel(GroupTypeDifficult difficult, GroupTypeEnvironment environment, GroupTypeGrade grade, BattleMapType mapType) {


        final PersonComponent personComponent = PersonComponent.getInstance();


        List<CharacterPrototype> playerPersons = new ArrayList<>();


        switch (environment) {
            case easy:
        }
        switch (difficult) {
            case easy001:
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Mage"));
                break;
            case easy002:
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Mage"));
                break;
            case easy003:
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Cup"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Warrior"));
                break;
            case hard001:
                playerPersons.add(personComponent.getCharacterPrototype("Archer"));
                playerPersons.add(personComponent.getCharacterPrototype("Mage"));
                playerPersons.add(personComponent.getCharacterPrototype("Warrior"));
                playerPersons.add(personComponent.getCharacterPrototype("LightMage"));
                break;
        }

        return new GroupExploreModel(playerPersons);
    }

    public static GroupExploreModel getMapGroupModel(BattleMapType mapType) {
        final PersonComponent personComponent = PersonComponent.getInstance();
        List<CharacterPrototype> playerPersons = new ArrayList<>();
        playerPersons.add(personComponent.getCharacterPrototype("Cup"));
        playerPersons.add(personComponent.getCharacterPrototype("Cup"));
        playerPersons.add(personComponent.getCharacterPrototype("Archer"));
        playerPersons.add(personComponent.getCharacterPrototype("Archer"));
        playerPersons.add(personComponent.getCharacterPrototype("Mage"));
        return new GroupExploreModel(playerPersons);
    }

}
