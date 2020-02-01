package com.example.swapan.quizzlerpractice;
/*
TODO List:
1. first create variable for two button and find view by ID
2. create a variable for display text question
3. create a Class (TrueFalse)with constructor for question management
4. create a question bank array to hold all question
5. create a variable(mIndex) to track which question is on
6. create a variable(mQuestion) to save question by using question bank and
    TrueFalse class(get questionID)
7. set question to textView
8. create a method(updateQuestion) to go next question and call it to
    two buttonClicked method
9.use % in index variable increment function to avoid app crass
10. create answer checking method and check condition
11. update progress bar
12. update score
13. update screen rotation function
14. work for Alert dialog
15. call onSaveInstanceState() method to save data when you rotate your phone
16. we can edit manifest file to MainActivity and add screenOrentation=potrat mood

 */

import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex;  //which question fetch from question bank or in which question user is on
    int mQuestion;  // To store question get from question bank
    int mScore;  // To track the score


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil (100.0 / mQuestionBank.length);
    // this Math.ceil method convert a double number into integer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for saving display before rotate the screen
        if (savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");

        }else {
            mScore = 0;
            mIndex = 0;

        }

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mScoreTextView = findViewById(R.id.score);

        mProgressBar = findViewById(R.id.progress_bar);


        /*
        To get question from question bank and save it in a variable
        TrueFalse firstQuestion = mQuestionBank[mIndex];
        int question = firstQuestion.getQuestionID();
        */

        // get question from question bank and save it in a variable
        mQuestion = mQuestionBank[mIndex].getQuestionID();

        // set questions to textView
        mQuestionTextView.setText(mQuestion);

        mScoreTextView.setText("Score " + mScore + " /" + mQuestionBank.length);

///*
//        final View.OnClickListener myListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Quizzler", "button Pressed");
//                Toast.makeText(getApplicationContext(),"True pressed",Toast.LENGTH_SHORT).show();
//            }
//        };
//        */
        // another way to set OnClick listener
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Quizzler", "button Presed");
//                Toast.makeText(getApplicationContext(),"True pressed",Toast.LENGTH_SHORT).show();
                // call the updateQuestion method
                checkAnswer(true); // must call this method before updateQuestion
                updateQuestion();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast myToast = Toast.makeText(getApplicationContext(),"False pressed",Toast.LENGTH_SHORT);
//                myToast.show();
                // call the updateQuestion method
                checkAnswer(false); // must call this method before updateQuestion
                updateQuestion();
            }
        });
    }
    // To go next question
    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length;
        /* modulas by total number of question to avoid crass the app and start
         from the first question
        */

        // code for alert dialog
        if (mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored " + mScore + " points");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();

        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();

        // set question to the question textView
        mQuestionTextView.setText(mQuestion);

        // to update progress bar
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

        // set score in score Text View
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);

    }
    // TODO: create answer checking method
    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if (userSelection == correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;  // to update score(correct answer only)

        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey",mScore);
        outState.putInt("IndexKey", mIndex);
    }
}
