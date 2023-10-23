package ru.safonoviv.roelr.Graphics.Layer;


import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Common.Utils;

public class HexHorizontal extends FieldSetting {


    public HexHorizontal(Setting setting) {
        super(setting);

    }


    private int topSideArea(double positionX, double positionY, int sectorAreaX, int sectorAreaY) {
        int supposedAreaX = sectorAreaX / 4;
        int supposedAreaY = sectorAreaY / 3;
        float centerX = getCenterAreaPositionX(supposedAreaX, supposedAreaY);
        float centerY = getCenterAreaPositionY(supposedAreaX, supposedAreaY);

        double distance = Utils.getDistanceBetweenTwoPoints(centerX, centerY, positionX, positionY);

        if (distance < gridSetting.getHeight()) {
            return getCoordinate(supposedAreaX, supposedAreaY);
        }

        boolean isRightSide = positionX > centerX;

        if (isRightSide) {
            if (supposedAreaY == 0) supposedAreaY++;
            return getCoordinate(supposedAreaX, supposedAreaY - 1);

        } else {
            if (supposedAreaX == 0) supposedAreaX++;
            if (supposedAreaY == 0) supposedAreaY++;
            return getCoordinate(supposedAreaX - 1, supposedAreaY - 1);
        }


        //need control h*sin(x)

    }


    private int bottomSideArea(double positionX, double positionY, int sectorAreaX, int sectorAreaY) {
        int supposedAreaX = sectorAreaX / 4;
        int supposedAreaY = (sectorAreaY + 2) / 3 - 1;
        float centerX = getCenterAreaPositionX(supposedAreaX, supposedAreaY);
        float centerY = getCenterAreaPositionY(supposedAreaX, supposedAreaY);

        double distance = Utils.getDistanceBetweenTwoPoints(centerX, centerY, positionX, positionY);

        if (distance < gridSetting.getHeight()) {
            return getCoordinate(supposedAreaX, supposedAreaY);
        }


        if (distance > gridSetting.getSize() / 2) {
            return getNextCoordinate(supposedAreaX, supposedAreaY, positionX, centerX);
        }


        return calculateCoordinate(supposedAreaX, supposedAreaY, centerX, centerY, distance, positionX, positionY);


    }

    private int calculateCoordinate(int supposedAreaX, int supposedAreaY, double centerX, double centerY, double distance, double positionX, double positionY) {

        //control h/sin(x)

        return getNextCoordinate(supposedAreaX, supposedAreaY, positionX, centerX);
    }


    private int getNextCoordinate(int supposedAreaX, int supposedAreaY, double positionX, double centerX) {
        boolean isRightSide = positionX > centerX;

        if (isRightSide) {
            return getCoordinate(supposedAreaX, supposedAreaY + 1);
        } else {
            if (supposedAreaX == 0) supposedAreaX++;
            return getCoordinate(supposedAreaX - 1, supposedAreaY + 1);
        }

    }


    private int getSectorAreaX(double positionX) {
        if (positionX < 0)
            positionX -= gridSetting.getNextX() / 4;
        return (int) (positionX / (gridSetting.getNextX() / 4));
    }

    private int getSectorAreaY(double positionY) {
        if (positionY < 0)
            positionY -= gridSetting.getNextY() / 6;
        return (int) (positionY / (gridSetting.getNextY() / 6));
    }


    @Override
    public int getCoordinate(double positionX, double positionY) {
        int sectorAreaX = getSectorAreaX(positionX);
        int sectorAreaY = getSectorAreaY(positionY);

        System.out.println();

        //sector 4*6
        int sectorY = (sectorAreaY + 1) % 6;


        if (sectorY == 2 || sectorY == 3) {
            return getCoordinate(sectorAreaX / 4, sectorAreaY / 3);
        }

        if (sectorY == 5 || sectorY == 0) {
            return getCoordinate((sectorAreaX - 2) / 4, sectorAreaY / 3);
        }


        if (sectorY == 1) {
            return topSideArea(positionX, positionY, sectorAreaX, sectorAreaY);
        } else {
            //sectorY == 4
            return bottomSideArea(positionX, positionY, sectorAreaX, sectorAreaY);
        }


    }


    @Override
    public float getCenterAreaPositionX(int coordinate) {
        return getCenterAreaPositionX(
                getAreaX(coordinate),
                getAreaY(coordinate));
    }

    @Override
    public float getCenterAreaPositionY(int coordinate) {
        return getCenterAreaPositionY(
                getAreaX(coordinate),
                getAreaY(coordinate));
    }

    @Override
    public float getCenterAreaPositionX(int areaX, int areaY) {
        if (areaY % 2 == 0) {
            return areaX * gridSetting.getNextX() + gridSetting.getNextX() / 2;
        } else {
            return areaX * gridSetting.getNextX() + (gridSetting.getNextX() + gridSetting.getNextX()) / 2;
        }
    }


    @Override
    public float getCenterAreaPositionY(int areaX, int areaY) {
        return areaY * gridSetting.getNextY() / 2 + gridSetting.getSize() / 2;
    }

}
