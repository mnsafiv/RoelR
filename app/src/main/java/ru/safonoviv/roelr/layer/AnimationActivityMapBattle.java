package ru.safonoviv.roelr.layer;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.GenerateObject.Component.MapComponent;
import ru.safonoviv.roelr.Object.Model.GroupExploreModel;
import ru.safonoviv.roelr.Graphics.Enum.MapType;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Logic.LaunchBattlefield;

import java.util.Map;

public class AnimationActivityMapBattle extends Activity {
    private LaunchBattlefield launchBattlefield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        GroupExploreModel modelOpponent = null;
        GroupExploreModel modelPlayer = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Setting.getInstance().setDisplayBounds(getWindowManager().getCurrentWindowMetrics().getBounds());
        }


        if (Build.VERSION.SDK_INT >= 33) {
            modelOpponent = bundle.getSerializable(DefaultValue.OPPONENT_KEY, GroupExploreModel.class);
            modelPlayer = bundle.getSerializable(DefaultValue.PLAYER_KEY, GroupExploreModel.class);
        }


        Map<Integer, MapValue> map = MapComponent.getInstance().getBattleMap(MapType.Simply);


//        StartBattleModel startBattleModel = new StartBattleModel(modelOpponent,modelPlayer,map);


        launchBattlefield = new LaunchBattlefield(this, getWindow());

        setContentView(launchBattlefield);
    }





    @Override
    protected void onResume() {
        super.onResume();
        launchBattlefield.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        launchBattlefield.pause();
    }

    public void backToExplore(boolean result) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DefaultValue.PLAYER_KEY,result);
        if(result){
            setResult(Activity.RESULT_OK, returnIntent);
        }else{
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }

        finish();

    }
}
