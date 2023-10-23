package ru.safonoviv.roelr.Object.trash;

import android.content.Context;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import lombok.Getter;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.CharacterSkill;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterSkill.SkillActive;
import ru.safonoviv.roelr.R;
import ru.safonoviv.roelr.SearchWay.FieldPrototype;

public abstract class AbstractActiveSkill extends AbstractActivePlayable {
    protected final CharacterSkill skill;
    protected final AbstractActivePlayable owner;
//    protected ShowStatus status;
    protected int expectedTime;
//    protected final Battlefield field;
    @Getter
    protected SkillActive active = SkillActive.Active;
//    protected ExistObjectField existObjectField;
    protected Camera camera;
    protected FieldSetting setting;



    public AbstractActiveSkill(Context context, CharacterSkill skill, AbstractActivePlayable owner) {
        super(owner, skill.getObjectType());
        this.skill = skill;
        this.owner = owner;

//        this.existObjectField = ExistObjectField.getInstance();
        this.camera = Setting.getInstance().getCamera();
        this.setting = Setting.getInstance().getFieldSetting();

//        this.field = (Battlefield) owner.getField();
        initialize(context);

    }




    private void initialize(Context context) {

        int screenX = Setting.getInstance().getCurrentWidth();
        int screenY = Setting.getInstance().getCurrentHeight();

        int statusMaxSizeX = (int) (screenX* DefaultValue.MAX_WIDTH_STATUS);
        int statusMaxSizeY = (int) (screenY *DefaultValue.MAX_HEIGHT_STATUS);
        int statusBorderPositionX = (int) (screenX * DefaultValue.DEFAULT_POSITION_STATUS_X);
        int statusBorderPositionY = (int) (screenY * DefaultValue.DEFAULT_POSITION_STATUS_Y);



        Paint paintBorderInfo = new Paint();
        Paint paintStatusInfo = new Paint();


        paintBorderInfo.setColor(ContextCompat.getColor(context, R.color.ColorBorderStatus));
        paintStatusInfo.setColor(ContextCompat.getColor(context, R.color.ColorTextStatus));


//        Set<StructureMessage> detailInfo = new HashSet<>();
//        detailInfo.add(new StructureMessage("Coordinate: ", String.valueOf(getCoordinate())));
//        detailInfo.add(new StructureMessage("Expected time: ", String.valueOf(expectedTime)));
//        detailInfo.add(new StructureMessage("State: ", String.valueOf(getState())));
//
//
//
//        status = new ShowStatus(statusBorderPositionX,
//                statusBorderPositionY,
//                statusMaxSizeX,
//                statusMaxSizeY,
//                paintBorderInfo,
//                paintStatusInfo,
//                detailInfo);





    }

//    @Override
//    public CommonModelObject getModel() {
//        return null;
//    }

    @Override
    public double getMoveSpeed() {
        return 0;
    }

    @Override
    public boolean getCollisionAction(AbstractActivePlayable object) {
        System.out.println();
        return false;
    }


    @Override
    public FieldPrototype getField() {
        return null;
//        return field;
    }

//    @Override
//    public int getActualPhysicalDamage() {
//       return owner.getActualPhysicalDamage();
//    }

//    @Override
//    public int getActualMagicDamage() {
//        return owner.getActualMagicDamage();
//    }

    @Override
    public double getSizeX() {
        return 0;
    }

    @Override
    public double getSizeY() {
        return 0;
    }



    @Override
    public void update() {
        expectedTime--;
        if (expectedTime < 0) {
//            existObjectField.addToRemove(this);
        }
        super.update();
    }

    @Override
    public boolean getTouchCollision(int coordinate) {
        return false;
    }

    public void setActive(SkillActive active) {
        this.active = active;
    }


//    @Override
//    public AbstractActivePlayable getOwner() {
//        return owner;
//    }

//    @Override
//    public void drawPanel(Canvas canvas) {
//
//    }

//    @Override
//    public List<StatusProperties> getStatus() {
//        return null;
//    }

//    @Override
//    public void resetActive() {
//
//    }

//    @Override
//    public void drawStatus(Canvas canvas) {
//        status.draw(canvas);
//
//    }

    @Override
    public void isCollided(AbstractActivePlayable object) {
        System.out.println();
    }



    public int getMagicMultiplier() {
        return skill.getMultiplier();
    }

    public int getPhysicalMultiplier() {
        return skill.getMultiplier();
    }



    @Override
    public void nextRound() {
        System.out.println();
    }
}
