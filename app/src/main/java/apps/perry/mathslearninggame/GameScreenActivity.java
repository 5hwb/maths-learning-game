package apps.perry.mathslearninggame;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import apps.perry.mathslearninggame.backend.Question;
import apps.perry.mathslearninggame.backend.QuestionFormatType;
import apps.perry.mathslearninggame.backend.QuestionManager;
import apps.perry.mathslearninggame.backend.QuestionType;

import static android.R.attr.y;
import static android.os.Build.VERSION_CODES.N;
import static apps.perry.mathslearninggame.R.id.answer1;
import static apps.perry.mathslearninggame.R.id.answer2;
import static apps.perry.mathslearninggame.R.id.answer3;
import static apps.perry.mathslearninggame.R.id.answer4;
import static apps.perry.mathslearninggame.R.id.button;
import static apps.perry.mathslearninggame.backend.QuestionFormatType.FILL_IT_IN;
import static apps.perry.mathslearninggame.backend.QuestionFormatType.MULTIPLE_CHOICE;

public class GameScreenActivity extends AppCompatActivity {

    /** Random number generator */
    protected Random r;

    /** Manages the list of questions to be shown to the player */
    QuestionManager qm;

    /** Stores the correct answer and the user's recorded answer */
    String correctAnswer;
    String recordedAnswer;

    /** Common GUI elements */
    TextView question;
    Button buttonContinue;

    /** This interface represents a group of inner classes used for building the user interface */
    interface GUIBuilder {
        /** Load the question and answer(s) onto the correct GUI */
        void loadScreen(Question qu);
    }

    /** GUIBuilder - multiple choice implementation */
    class GUIBuilderMC implements GUIBuilder {

        @Override
        public void loadScreen(Question qu) {
            setContentView(R.layout.activity_game_screen_mc);
            question = (TextView) findViewById(R.id.textView_question);
            final TextView[] textViews = new TextView[4];
            textViews[0] = (TextView) findViewById(answer1);
            textViews[1] = (TextView) findViewById(answer2);
            textViews[2] = (TextView) findViewById(answer3);
            textViews[3] = (TextView) findViewById(answer4);
            question.setText(qu.question());
            buttonContinue = (Button) findViewById(R.id.button_continue);

            // Shuffle a copy of the list of answers to be used for final display
            List<String> shuffledAnswers = new ArrayList<String>(qu.answers());
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

                        // Enable the continue button
                        buttonContinue.setClickable(true);
                        buttonContinue.setAlpha(1.0f);
                    }
                });
            }
        }
    }

    /** GUIBuilder - 'fill-it-in' implementation */
    class GUIBuilderFII implements GUIBuilder {

        @Override
        public void loadScreen(Question qu) {
            setContentView(R.layout.activity_game_screen_fii);
            question = (TextView) findViewById(R.id.textView_question);
            question.setText(qu.question());
            EditText et = (EditText) findViewById(R.id.answer_edittext);
            buttonContinue = (Button) findViewById(R.id.button_continue);
            buttonContinue.setClickable(false);

            // Set the recorded answer after the user finishes entering it
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    recordedAnswer = editable.toString();

                    // Enable the continue button
                    buttonContinue.setClickable(true);
                    buttonContinue.setAlpha(1.0f);
                }
            });
        }
    }

    /** This inner class manages the GUIBuilders, ensuring the correct one is selected */
    class GUIManager {
        void loadScreen(Question qu) {
            // Set up the UI depending on the question type
            QuestionFormatType qft = qu.type();
            GUIBuilder gb;

            switch (qft) {
                case MULTIPLE_CHOICE:
                    gb = new GUIBuilderMC();
                    break;
                case FILL_IT_IN:
                    gb = new GUIBuilderFII();
                    break;
                default:
                    gb = new GUIBuilderMC();
                    break;
            }
            gb.loadScreen(qu);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_mc);
        r = new Random();
        qm = QuestionManager.getInstance();
        loadQuestion(null);
    }

    /**
     * Load the question onto the user interface
     * @param v
     */
    public void loadQuestion(View v) {
        // Get the current question from the QuestionManager
        Question q = qm.giveMeAQuestion();
        QuestionFormatType qft = q.type();
        ArrayList<String> answers = q.answers();

        // Load the question onto the user interface
        GUIManager guiManager = new GUIManager();
        guiManager.loadScreen(q);

        // The correct answer is always 1st in the list of possible answers
        correctAnswer = answers.get(0);

        // By default, the continue button is disabled before the player selects their answer
        buttonContinue.setClickable(false);
        buttonContinue.setAlpha(0.5f);
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
}
