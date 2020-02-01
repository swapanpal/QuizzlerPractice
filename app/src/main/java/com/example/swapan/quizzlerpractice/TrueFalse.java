package com.example.swapan.quizzlerpractice;

public class TrueFalse {

    private int mQuestionID;
    private boolean mAnswer;
    // create a constructor for this class
    public TrueFalse(int questionResourceID, boolean trueOrFalse){
        mQuestionID = questionResourceID;
        mAnswer = trueOrFalse;
    }
        // create getter and setter for this constructor
    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
