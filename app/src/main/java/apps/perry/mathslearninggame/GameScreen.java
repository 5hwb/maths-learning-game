package apps.perry.mathslearninggame;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import apps.perry.mathslearninggame.backend.Question;
import apps.perry.mathslearninggame.backend.QuestionFormatType;
import apps.perry.mathslearninggame.backend.QuestionManager;

import static apps.perry.mathslearninggame.R.id.answer1;
import static apps.perry.mathslearninggame.R.id.answer2;
import static apps.perry.mathslearninggame.R.id.answer3;
import static apps.perry.mathslearninggame.R.id.answer4;

public class GameScreen extends AppCompatActivity {

    QuestionManager qm;
    String correctAnswer;
    String recordedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_mc);
        qm = new QuestionManager();
        loadQuestion(null);
    }

    /**
     * Load the question onto the user interface
     * @param v
     */
    public void loadQuestion(View v) {
        // Get the current Question
        Question q = qm.giveMeAQuestion();
        QuestionFormatType qft = q.type();
        ArrayList<String> answers = q.answers();
        TextView question;

        // Set up the UI depending on the question type
        switch (qft) {
            case MULTIPLE_CHOICE:
                setContentView(R.layout.activity_game_screen_mc);
                question = (TextView) findViewById(R.id.textView_question);
                final TextView[] textViews = new TextView[4];
                textViews[0] = (TextView) findViewById(answer1);
                textViews[1] = (TextView) findViewById(answer2);
                textViews[2] = (TextView) findViewById(answer3);
                textViews[3] = (TextView) findViewById(answer4);

                question.setText(q.question());
                textViews[0].setText(answers.get(0));
                textViews[1].setText(answers.get(1));
                textViews[2].setText(answers.get(2));
                textViews[3].setText(answers.get(3));
                correctAnswer = answers.get(0);

                // Set up click listeners for each button to record their answer
                for (int i = 0; i < 4; i++) {
                    textViews[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tv = (TextView) v;
                            recordedAnswer = (String) tv.getText();

                            // Set background and text colours for all answer buttons
                            for (int i = 0; i < textViews.length; i++) {
                                textViews[i].setBackgroundResource(R.drawable.button_background);
                                textViews[i].setTextColor(0x000000);
                            }

                            // Set background and text colours for the selected answer button
                            tv.setBackgroundResource(R.drawable.button_background_selected);
                            tv.setTextColor(0xFFFFFF);
                        }
                    });
                }
                break;
            case FILL_IT_IN:
                setContentView(R.layout.activity_game_screen_fii);
                question = (TextView) findViewById(R.id.textView_question);
                question.setText(q.question());

                EditText et = (EditText) findViewById(R.id.answer_edittext);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        recordedAnswer = editable.toString();
                    }
                });

                correctAnswer = answers.get(0);
                break;
            default:
                setContentView(R.layout.activity_game_screen_mc);
                break;
        }

    }

    public void checkAnswer(View v) {
        boolean answerIsCorrect = recordedAnswer.equals(correctAnswer);

        String messageAnswerCorrect = String.format("Correct answer! %s", correctAnswer);
        String messageAnswerWrong = String.format("Wrong answer! %s", correctAnswer);

        // 1. Instantiate the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((answerIsCorrect) ? messageAnswerCorrect : messageAnswerWrong)
                .setTitle(R.string.dialog_title);

        // Add continue button
        builder.setPositiveButton(R.string.dialog_continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                loadQuestion(null);
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
