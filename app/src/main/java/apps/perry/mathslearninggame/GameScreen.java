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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import apps.perry.mathslearninggame.backend.Question;
import apps.perry.mathslearninggame.backend.QuestionFormatType;
import apps.perry.mathslearninggame.backend.QuestionManager;

import static apps.perry.mathslearninggame.R.id.answer1;
import static apps.perry.mathslearninggame.R.id.answer2;
import static apps.perry.mathslearninggame.R.id.answer3;
import static apps.perry.mathslearninggame.R.id.answer4;

public class GameScreen extends AppCompatActivity {

    /** Random number generator */
    protected Random r;

    QuestionManager qm;
    String correctAnswer;
    String recordedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_mc);
        r = new Random();
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

                // Shuffle a copy of the list of answers to be used for final display
                List<String> shuffledAnswers = new ArrayList<String>(answers);
                Collections.shuffle(shuffledAnswers);

                for (int i = 0; i < 4; i++) {
                    // Set the button answer display
                    textViews[i].setText(shuffledAnswers.get(i));

                    // Set up click listeners for each button to record their answer
                    textViews[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tv = (TextView) v;
                            recordedAnswer = (String) tv.getText();

                            // Set background and text colours for all answer buttons
                            for (int i = 0; i < textViews.length; i++) {
                                textViews[i].setBackgroundResource(R.drawable.button_background);
                                textViews[i].setTextColor(0xFF000000);
                            }

                            // Set background and text colours for the selected answer button
                            tv.setBackgroundResource(R.drawable.button_background_selected);
                            ((TextView) v).setTextColor(0xFFFFFFFF);
                        }
                    });
                }
                break;
            case FILL_IT_IN:
                setContentView(R.layout.activity_game_screen_fii);
                question = (TextView) findViewById(R.id.textView_question);
                question.setText(q.question());

                // Set the recorded answer after the user finishes entering it
                EditText et = (EditText) findViewById(R.id.answer_edittext);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void afterTextChanged(Editable editable) {
                        recordedAnswer = editable.toString();
                    }
                });

                break;
            default:
                setContentView(R.layout.activity_game_screen_mc);
                break;
        }

        // The correct answer is always 1st in the list of possible answers
        correctAnswer = answers.get(0);
    }

    /**
     * Get the player's answer and check if it is correct
     * @param v
     */
    public void checkAnswer(View v) {
        recordedAnswer = recordedAnswer.replace("x2", "x²"); // replace normal 2 with superscript tool (TEMP HACK FOR NOW)
        boolean answerIsCorrect = recordedAnswer.equals(correctAnswer);

        String titleAnswerCorrect = "Correct!";
        String titleAnswerWrong = "Wrong!";

        String message = String.format("The correct answer is %s", correctAnswer);

        // 1. Instantiate the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle((answerIsCorrect) ? titleAnswerCorrect : titleAnswerWrong);

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

    /**
     * Generate a random number in a range between a minimum and maximum value
     * @param min Minimum value
     * @param max Maximum value
     * @return A random number
     */
    private int randomInteger(int min, int max) {
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
