package apps.perry.mathslearninggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import apps.perry.mathslearninggame.backend.ScoreManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v) {
        ScoreManager.getInstance().resetScore();
        Intent intent = new Intent(getApplicationContext(), GameScreenActivity.class);
        startActivity(intent);
    }
}
