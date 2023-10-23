package ru.safonoviv.roelr.layer;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.GenerateObject.Component.MapComponent;
import ru.safonoviv.roelr.Graphics.Enum.MapType;
import ru.safonoviv.roelr.Graphics.Layer.GridType;
import ru.safonoviv.roelr.Logic.LaunchExplore;
import ru.safonoviv.roelr.MainActivity;
import ru.safonoviv.roelr.Object.CharacterExplore.GroupExplore;
import ru.safonoviv.roelr.Object.Model.ModelDataExplore;


public class AnimationActivityMapExplore extends Activity {
    private static AnimationActivityMapExplore mInstance;
    private LaunchExplore launchExplore;

    static final int PICK_CONTACT_REQUEST = 0;

    public static Activity getInstance() {
        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DefaultValue.GRID_TYPE = GridType.HexHorizontal;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Setting.getInstance().setDisplayBounds(getWindowManager().getCurrentWindowMetrics().getBounds());
        }

        mInstance = this;

        ModelDataExplore mapModelData = MapComponent.getInstance().getModelGroup(MapComponent.getInstance().getExploreMap(MapType.Simply, Setting.getInstance()));

        launchExplore = new LaunchExplore(this, getWindow(), mapModelData);
        setContentView(launchExplore);
    }

    @Override
    protected void onResume() {
        super.onResume();
        launchExplore.start();
    }


    public void setBattlefieldActivity(GroupExplore opponentGroup, GroupExplore playerGroup) {
        Intent intent = new Intent(AnimationActivityMapExplore.this, AnimationActivityMapBattle.class);

        intent.putExtra(DefaultValue.OPPONENT_KEY, opponentGroup.getGroupExploreModel());
        intent.putExtra(DefaultValue.PLAYER_KEY, playerGroup.getGroupExploreModel());

        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }


    @Override
    protected void onPause() {
        super.onPause();
        launchExplore.pause();

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                // handle data
                assert data != null;
                Bundle bundle = data.getExtras();
                boolean result = bundle.getBoolean(DefaultValue.PLAYER_KEY);
                launchExplore.setResultBattle(result);
            } else {
                // handle cancellation
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


}
