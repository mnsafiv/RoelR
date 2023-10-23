package ru.safonoviv.roelr.Graphics.Panel.RectItem;

import android.content.Context;
import android.widget.Toast;
import ru.safonoviv.roelr.MainActivity;
import ru.safonoviv.roelr.Object.ExploreObject.ChestRabbit;
import ru.safonoviv.roelr.layer.AnimationActivityMapExplore;

public class ButtonDeclineReward extends StructureBitmap {

    private final ChestRabbit chestRabbit;
    private final String str;

    public ButtonDeclineReward(Context context, int id, int numberPosition, int number, int height, ChestRabbit chestRabbit) {
        super(context, id, numberPosition, number, height);
        this.chestRabbit = chestRabbit;
        str = "Decline reward, but no impl";
    }



    @Override
    public boolean getAction() {
        chestRabbit.removeFromMapValue();
        new Thread(() -> AnimationActivityMapExplore.getInstance().runOnUiThread(() -> Toast.makeText(MainActivity.getContext(),str,
                Toast.LENGTH_SHORT).show())).start();
        return true;
    }


}
