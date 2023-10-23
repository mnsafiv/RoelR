package ru.safonoviv.roelr.Object.trash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import lombok.Getter;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;
import ru.safonoviv.roelr.Object.CharacterModel.Team;
import ru.safonoviv.roelr.R;

public class CharacterSkillPlayable {

    @Getter
    private final CharacterSkill skill;


    private boolean isActive = false;

//    @Getter
//    private ShowStatus showStatus;
//    private ShowSkillIconStatus showSkillIconStatus;
    private final Boolean noAnimation;

    public CharacterSkillPlayable(Context context, CharacterSkill characterSkill, Team team, int position) {
        noAnimation = false;
        this.skill = characterSkill;

        Paint paint = new Paint();
        Paint paintBase = new Paint();
        Paint paintBorderInfo = new Paint();
        Paint paintStatusInfo = new Paint();

        paintBorderInfo.setColor(ContextCompat.getColor(context, R.color.ColorBorderStatus));
        paintStatusInfo.setColor(ContextCompat.getColor(context, R.color.ColorTextStatus));
        paint.setColor(ContextCompat.getColor(context, R.color.SelectObject));

        switch (team) {
            case player:
                paintBase.setColor(ContextCompat.getColor(context, R.color.SkillColorBorderBase));
                break;
            case opponent:
                paintBase.setColor(ContextCompat.getColor(context, R.color.SkillColorBorderBaseNoActive));
                break;
            case neutral:
                break;
        }

//        showSkillIconStatus = new ShowSkillIconStatus(context, skill, team, position, 200,200);
//
//        int screenX = Setting.getInstance().getCurrentWidth();
//        int screenY = Setting.getInstance().getCurrentHeight();
//
//        int statusMaxSizeX = (int) (screenX * DefaultValue.MAX_WIDTH_STATUS);
//        int statusMaxSizeY = (int) (screenY * DefaultValue.MAX_HEIGHT_STATUS);
//        int statusBorderPositionX = (int) (screenX * DefaultValue.DEFAULT_POSITION_STATUS_X);
//        int statusBorderPositionY = (int) (screenY * DefaultValue.DEFAULT_POSITION_STATUS_Y);
//
//        Set<StructureMessage> detailInfo = new HashSet<>();
//
//        showStatus = new ShowStatus(statusBorderPositionX,
//                statusBorderPositionY,
//                statusMaxSizeX,
//                statusMaxSizeY,
//                paintBorderInfo,
//                paintStatusInfo,
//                detailInfo);

    }

    public CharacterSkillPlayable(CharacterSkill characterSkill) {
        this.skill = characterSkill;

        noAnimation=true;


    }

    public void updateSkillStatus(){
        if(noAnimation){
            return;
        }

//        Set<StructureMessage> detailInfo = new HashSet<>();

//        detailInfo.add(new StructureMessage("Multiplier: ", String.valueOf(skill.getMultiplier())));
//        detailInfo.add(new StructureMessage("Skill cost: ", String.valueOf(skill.getSkillCostPoint())));
//        detailInfo.add(new StructureMessage("Charge: ", String.valueOf(skill.getChargeCurrentCapacity())));
//        detailInfo.add(new StructureMessage("Re charge: ", String.valueOf(skill.getChargeRound())));
//
//        showStatus.updateInfo(detailInfo);

    }

    public void drawSkillIcon(Canvas canvas, boolean isLocked) {
//        showSkillIconStatus.draw(canvas, isLocked, isActive);
    }


    public CharacterSkillPlayable isCollide() {
//        if (showSkillIconStatus.getCollision()) {
//            return this;
//        }
        return null;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //    public int getSkillCost() {
//        return skill.getSkillCostPoint();
//    }

    public void updateSkillNextTurn() {
        skill.setChargeCurrentCapacity(
                Math.min(skill.getChargeCapacity(), skill.getChargeCurrentCapacity()+ skill.getChargeRound()));
        updateSkillStatus();
    }

    public int getSkillCost() {
        System.out.println();
        return 0;
    }
}
