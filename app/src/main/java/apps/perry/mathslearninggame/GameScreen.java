package apps.perry.mathslearninggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameScreen extends AppCompatActivity {

    boolean sw = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_mc);
    }

    public void switchView(View v) {
        if (sw) {
            setContentView(R.layout.activity_game_screen_fii);
        }
        else {
            setContentView(R.layout.activity_game_screen_mc);
        }

        sw = !sw;
    }
}
