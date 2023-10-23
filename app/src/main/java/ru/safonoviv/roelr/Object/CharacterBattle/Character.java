package ru.safonoviv.roelr.Object.CharacterBattle;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.GenerateObject.Battle.Enum.ObjectType;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Display.CursorPosition;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Object.trash.AbstractActivePlayableCharacter;
import ru.safonoviv.roelr.Object.CharacterModel.CharacterPrototype;
import ru.safonoviv.roelr.Object.trash.CharacterSkillPlayable;
import ru.safonoviv.roelr.Object.CharacterModel.Team;
import ru.safonoviv.roelr.SearchWay.ActiveState;
import ru.safonoviv.roelr.SearchWay.FieldPrototype;
import ru.safonoviv.roelr.SearchWay.MovePositionVector;
import ru.safonoviv.roelr.SearchWay.Node;
import ru.safonoviv.roelr.SearchWay.PathWay;

import java.util.Set;

public class Character extends AbstractActivePlayableCharacter {

//    private final Thread threadSearchPath;
    private Bitmap bitmap;


    private final Camera camera;
    private final CursorPosition cursorPosition;
    private final FieldSetting setting;

//    private final CharacterPlayable character;
//    private final PathWay path;
//    private final MovePositionVector movePosition;
//
//    private double currentResolution;
//    private Paint selectColor;
//    private Paint pathColor;
//    private Paint colorCharacterSelected;
//
//    private HealthBar healthBar;
//    private ShowStatus characterStatus;
//    private ShowStatus characterActiveStatus;
//    private final List<StatusProperties> statuses = new ArrayList<>();
//    private final ExistObjectField existObjectField;
//
//    private UpdateSelectCursor cursor;
//    private final TimeCounter timeCounter = new TimeCounter(1);
//
//    private ActiveState state = ActiveState.wait;
//    private final Boolean noAnimation;


    public Character(Context context, @NotNull CharacterPrototype characterPrototype, Team team, int areaX, int areaY) {
        super(Setting.getInstance().getFieldSetting().getCenterAreaPositionX(areaX, areaY),
                Setting.getInstance().getFieldSetting().getCenterAreaPositionY(areaX, areaY),
                ObjectType.Character,
                team);


//        noAnimation = false;

        //init base
//        this.character = new CharacterPlayable(characterPrototype, 3,team);
//        this.path = new PathWay(field);
        this.camera = Setting.getInstance().getCamera();
        this.cursorPosition = camera.getCursorPosition();
        this.setting = Setting.getInstance().getFieldSetting();

//        path.getField().addUnstable(areaX, areaY, this);
//        character.nextTurn();
//        updatePath();
//        threadSearchPath = new Thread(path);
//        threadSearchPath.setName("Search path: " + this);
//        threadSearchPath.start();
//        this.movePosition = new MovePositionVector(this);
//        existObjectField = ExistObjectField.getInstance();


        //init animation
//        this.healthBar = new HealthBar(context, this);
//        cursor = new UpdateSelectCursor(cursorPosition);
//        updateBitmap();
//        initPaint(context);
//        int screenX = Setting.getInstance().getCurrentWidth();
//        int screenY = Setting.getInstance().getCurrentHeight();
//
//        int statusMaxSizeX = (int) (screenX * DefaultValue.MAX_WIDTH_STATUS);
//        int statusMaxSizeY = (int) (screenY * DefaultValue.MAX_HEIGHT_STATUS);
//        int statusBorderPositionX = (int) (screenX * DefaultValue.DEFAULT_POSITION_STATUS_X);
//        int statusBorderPositionY = (int) (screenY * DefaultValue.DEFAULT_POSITION_STATUS_Y);
//
//
//        Paint paintBorder = new Paint();
//        Paint paintTextColor = new Paint();
//
//
//        paintBorder.setColor(ContextCompat.getColor(context, R.color.ColorBorderStatus));
//        paintTextColor.setColor(ContextCompat.getColor(context, R.color.ColorTextStatus));
//
//        characterStatus = new ShowStatus(statusBorderPositionX,
//                statusBorderPositionY,
//                statusMaxSizeX,
//                statusMaxSizeY,
//                paintBorder,
//                paintTextColor,
//                character.getStats().getCharacterStatus());
//
//        statusBorderPositionX += characterStatus.getWidth();
//
//        characterActiveStatus = new ShowStatus(statusBorderPositionX,
//                statusBorderPositionY,
//                statusMaxSizeX,
//                statusMaxSizeY,
//                paintBorder,
//                paintTextColor,
//                character.getStats().getActiveStatus());
//
//        statuses.add(characterActiveStatus);
//        statuses.add(characterStatus);


    }

//    public Character(@NotNull CharacterPrototype characterModel, Team team, int areaX, int areaY, Battlefield field) {
//        super(Setting.getInstance().getFieldSetting().getCenterAreaPositionX(areaX, areaY),
//                Setting.getInstance().getFieldSetting().getCenterAreaPositionY(areaX, areaY),
//                ObjectType.Character,
//                team);

//        noAnimation = true;

        //init base
//        this.character = new CharacterPlayable(characterModel, team, team);
//        this.path = new PathWay(field);
//        this.camera = Setting.getInstance().getCamera();
//        this.cursorPosition = camera.getCursorPosition();
//        this.setting = Setting.getInstance().getFieldSetting();
//        path.getField().addUnstable(areaX, areaY, this);
//        character.nextTurn();
//        updatePath();
//        threadSearchPath = new Thread(path);
//        threadSearchPath.setName("Search path: " + this);
//        threadSearchPath.start();
//        this.movePosition = new MovePositionVector(this);
//        existObjectField = ExistObjectField.getInstance();


//    }


    private void initPaint(Context context) {
//        colorCharacterSelected = new Paint();
//        colorCharacterSelected.setColor(ContextCompat.getColor(context, R.color.colorCharacterSelected));
//        selectColor = new Paint();
//        selectColor.setColor(ContextCompat.getColor(context, R.color.selectColor));
//        pathColor = new Paint();
//        pathColor.setColor(ContextCompat.getColor(context, R.color.pathColor));
    }


//    @Override
//    public void update() {
//        super.update();
//        movePosition.update();
//
//
//        if (!noAnimation) {
//            return;
//        }
//
//
//        if (isActive && timeCounter.needUpdate()) {
//            updateStatus();
//        }
//
//
//        //update bitmap if resolution was changed
//        if (currentResolution != camera.cameraResolution.getResolutionSize()) {
//            currentResolution = camera.cameraResolution.getResolutionSize();
//            updateBitmap();
//        }
//
//
//    }


//    @Override
//    public boolean checkNextPosition(int coordinate) {
//        return false;
//    }
//
//    @Override
//    public boolean decreaseMovePoint(int coordinate) {
//        return false;
//    }
//
//    @Override
//    public void updatePath() {
//
//    }
//
//    @Override
//    public void switchUnstable(int coordinatePrev, int coordinateCur) {
//
//    }

//    @Override
//    public MovePositionVector getMovePosition() {
//        return null;
//    }

//    @Override
//    public void moveToNextCoordinate(Node node) {
//        if (node == null) {
//            System.out.println();
//        }
//        movePosition.moveToNewPosition(node);
//    }

    private void updateBitmap() {
//        bitmap = CharacterMap.getInstance().getBitmapById(character.getInfo().getIcon_id());
//        double resolution = currentResolution / (bitmap.getHeight() / setting.getSize());
//        bitmap = Bitmap.createScaledBitmap(bitmap,
//                (int) (bitmap.getWidth() * resolution),
//                (int) (bitmap.getHeight() * resolution),
//                false);
    }

    @Override
    public PathWay getPath() {
        return null;
    }

    @Override
    public Set<CharacterSkillPlayable> getSkills() {
        return null;
    }

//    @Override
//    public Set<CharacterSkillPlayable> getSkills() {
//        return null;
//    }

    @Override
    public boolean decreaseSkillPointAndIncreaseUse(CharacterSkillPlayable skill) {
//        return character.decreaseSkillPointAndIncreaseUse(skill);
        return false;
    }

    @Override
    public double getSizeX() {
        return 0;
    }

    @Override
    public double getSizeY() {
        return 0;
    }

    @Override
    public CharacterPlayable getCharacter() {
        return null;
    }

    @Override
    public void nextRound() {

    }

    public void updateStatus() {
//        if (noAnimation) {
//            return;
//        }
//        characterStatus.updateInfo(character.getStats().getCharacterStatus());
//        characterActiveStatus.updateInfo(character.getStats().getActiveStatus());
    }

    @Override
    public boolean getTouchCollision(int coordinate) {
        return false;
    }

    @Override
    public boolean getTouchCollision(double positionX, double positionY) {
        return false;
    }

    @Override
    public boolean reset() {
        return true;
    }

    @Override
    public FieldPrototype getField() {
        return null;
    }

    @Override
    public void setPositionX(double positionX) {

    }

    @Override
    public void setPositionY(double positionY) {

    }

    @Override
    public boolean canMoveToNextCoordinate(int coordinate) {
        return false;
    }

    @Override
    public boolean decreaseMovePoint(int coordinate) {
        return false;
    }

    @Override
    public void updatePath() {

    }

    @Override
    public void setActiveState(ActiveState activeState) {

    }

    @Override
    public MovePositionVector getMovePosition() {
        return null;
    }

    @Override
    public void moveToNextCoordinate(Node node) {

    }

    @Override
    public double getMoveSpeed() {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {

    }


//    @Override
//    public void draw(Canvas canvas) {
//        healthBar.draw(canvas);
//        if (isActive) {
//            Random random = new Random();
//
//            Bitmap bitmapNew = Utils.changeColor(bitmap, (byte) (random.nextInt(50) + 50));
//            canvas.drawBitmap(bitmapNew,
//                    (float) camera.getDistanceToCameraX(getPositionX() - bitmap.getWidth() / 2.0),
//                    (float) camera.getDistanceToCameraY(getPositionY() - bitmap.getHeight() / 2.0),
//                    null);
//        } else {
//            canvas.drawBitmap(bitmap,
//                    (float) camera.getDistanceToCameraX(getPositionX() - bitmap.getWidth() / 2.0),
//                    (float) camera.getDistanceToCameraY(getPositionY() - bitmap.getHeight() / 2.0),
//                    null);
//        }
//
//
//    }

//    @Override
//    public void drawStatus(Canvas canvas) {
//        characterStatus.draw(canvas);
//        characterActiveStatus.draw(canvas);
//    }

//    @Override
//    public void resetActive() {
//        isActive = false;
//    }

//    @Override
//    public boolean getTouchCollision() {
//        if (cursor.isUpdatedCursorPosition()) {
//            int coordinate = setting.getCoordinate(getPositionX(), getPositionY());
//            int coordinateCursor = cursorPosition.getCursorCoordinate();
//            return coordinate == coordinateCursor;
//        }
//
//        return false;
//    }

//    @Override
//    public boolean isActive() {
//        return isActive;
//    }

//    @Override
//    public double getSizeX() {
//        return bitmap.getWidth();
//    }

//    @Override
//    public double getSizeY() {
//        return bitmap.getHeight();
//
//    }

//    @Override
//    public CharacterPlayable getCharacter() {
//        return null;
//    }

//    @Override
//    public void nextRound() {
//
//    }

//    @Override
//    public void setActive(boolean isActive) {
//        this.isActive = isActive;
//    }

//    @Override
//    public FieldPrototype getField() {
//        return null;
//    }

//    @Override
//    public List<StatusProperties> getStatus() {
//        return null;
//    }


//    @Override
//    public CommonModelObject getModel() {
//        return new CommonModelObject(this);
//    }

//    @Override
//    public boolean getCollisionAction(AbstractActivePlayable object) {
//
//        return true;
//    }

//    @Override
//    public double getMoveSpeed() {
//        return 0;
//    }


//    @Override
//    public int getActualPhysicalDamage() {
//        return 0;
//    }

//    @Override
//    public int getActualMagicDamage() {
//        return 0;
//    }

//    @Override
//    public float getActualHealth() {
//        return 0;
//    }

//    @Override
//    public float getMaxHealth() {
//        return 0;
//    }
}


