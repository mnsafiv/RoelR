package ru.safonoviv.roelr.Active;

import lombok.Getter;
import ru.safonoviv.roelr.Object.CharacterExplore.GroupExplore;


@Getter
public class PlayerGroup {
    private static volatile PlayerGroup playerGroup;
    private GroupExplore playGroupExplore;

    private PlayerGroup() {
    }

    public static synchronized PlayerGroup getInstance() {
        PlayerGroup singleton = playerGroup;
        if (singleton == null) {
            synchronized (PlayerGroup.class) {
                if (playerGroup == null) {
                    playerGroup = new PlayerGroup();
                }
            }
        }
        return playerGroup;
    }

    public void setPlayGroupExplore(GroupExplore playGroupExplore) {
        this.playGroupExplore = playGroupExplore;
    }
}
