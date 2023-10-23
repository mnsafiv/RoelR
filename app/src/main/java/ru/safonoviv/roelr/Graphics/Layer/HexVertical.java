package ru.safonoviv.roelr.Graphics.Layer;


import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Common.Utils;

public class HexVertical extends FieldSetting {

    public HexVertical(Setting setting) {
        super(setting);
    }


    @Override
    public int getCoordinate(double positionX, double positionY) {

        int sectorAreaX = getSectorAreaX(positionX);
        int sectorAreaY = getSectorAreaY(positionY);

        int curSectorX = sectorAreaX + 1;
//        int curSectorY = sectorAreaY + 1;

        int sectorX = curSectorX % 6;
//        int sectorY = curSectorY % 4;

        if (sectorX == 2 || sectorX == 3) {
            return getCoordinate(sectorAreaX / 6, (sectorAreaY / 4) * 2);

        }
        if (sectorX == 5 || sectorX == 0) {
            return getCoordinate(sectorAreaX / 6, ((sectorAreaY - 2) / 4) * 2 + 1);
        }
        if (sectorX == 1) {

            return leftSideArea(positionX, positionY, sectorAreaX, sectorAreaY);
        }

        return rightSideArea(positionX, positionY, sectorAreaX, sectorAreaY);
    }


    private int leftSideArea(double positionX, double positionY, int sectorAreaX, int sectorAreaY) {
        int supposedAreaX = sectorAreaX / 6;
        int supposedAreaY = (sectorAreaY / 4) * 2;
        float centerX = getCenterAreaPositionX(supposedAreaX, supposedAreaY);
        float centerY = getCenterAreaPositionY(supposedAreaX, supposedAreaY);

        double distance = Utils.getDistanceBetweenTwoPoints(centerX, centerY, positionX, positionY);

        if (distance < gridSetting.getHeight()) {
            return getCoordinate(supposedAreaX, supposedAreaY);
        }

        boolean isBottom = centerY < positionY;

        if (isBottom) {
            if (distance > gridSetting.getSize() / 2) {
                return getCoordinate(supposedAreaX - 1, supposedAreaY + 1);
            }
        } else {

            if (distance > gridSetting.getSize() / 2) {
                return getCoordinate(supposedAreaX - 1, supposedAreaY - 1);
            }
        }


        //need control h*sin(x)
        return getCoordinate(supposedAreaX, supposedAreaY);
    }



    private int rightSideArea(double positionX, double positionY, int sectorAreaX, int sectorAreaY) {
        int supposedAreaX = sectorAreaX / 6;
        int supposedAreaY = (sectorAreaY / 4) * 2;
        float centerX = getCenterAreaPositionX(supposedAreaX, supposedAreaY);
        float centerY = getCenterAreaPositionY(supposedAreaX, supposedAreaY);

        double distance = Utils.getDistanceBetweenTwoPoints(centerX, centerY, positionX, positionY);

        if (distance < gridSetting.getHeight()) {
            return getCoordinate(supposedAreaX, supposedAreaY);
        }

        boolean isBottom = centerY - positionY < 0;

        if (isBottom) {
            if (distance > gridSetting.getSize() / 2) {
                return getCoordinate(supposedAreaX, supposedAreaY + 1);
            }
        } else {

            if (distance > gridSetting.getSize() / 2) {
                return getCoordinate(supposedAreaX, supposedAreaY - 1);
            }
        }


        //need control h*sin(x)
        return getCoordinate(supposedAreaX, supposedAreaY);
    }




    private int getSectorAreaX(double positionX) {
        if (positionX < 0)
            positionX -= gridSetting.getNextX() / 6;
        return (int) (positionX / (gridSetting.getNextX() / 6));
    }

    private int getSectorAreaY(double positionY) {
        if (positionY < 0)
            positionY -= gridSetting.getNextY() / 4;
        return (int) (positionY / (gridSetting.getNextY() / 4));
    }

    @Override
    public float getCenterAreaPositionX(int areaX, int areaY) {
        if (areaY % 2 == 0) {
            return areaX * gridSetting.getNextX() + gridSetting.getSize() / 2;
        } else {
            return areaX * gridSetting.getNextX() + (gridSetting.getNextX() + gridSetting.getSize()) / 2;
        }
    }

    @Override
    public float getCenterAreaPositionX(int coordinate) {
        int areaX = getAreaX(coordinate);
        int areaY = getAreaY(coordinate);
        if (areaY % 2 == 0) {
            return areaX * gridSetting.getNextX() + gridSetting.getSize() / 2;
        } else {
            return areaX * gridSetting.getNextX() + (gridSetting.getNextX() + gridSetting.getSize()) / 2;
        }
    }

    @Override
    public float getCenterAreaPositionY(int coordinate) {
        return getAreaY(coordinate) * gridSetting.getNextY() / 2 + gridSetting.getNextY() / 2;
    }

    @Override
    public float getCenterAreaPositionY(int areaX, int areaY) {
        return areaY * gridSetting.getNextY() / 2 + gridSetting.getNextY() / 2;
    }


}
