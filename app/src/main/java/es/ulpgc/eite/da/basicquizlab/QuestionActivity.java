package es.ulpgc.eite.da.basicquizlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

  public static final String TAG = "Quiz.QuestionActivity";

  public final static String KEY_INDEX = "KEY_INDEX";
  public final static String KEY_REPLY = "KEY_REPLY";
  public final static String KEY_ENABLED = "KEY_ENABLED";
  public static final int CHEAT_REQUEST = 1;

  private Button falseButton, trueButton,cheatButton, nextButton;
  private TextView questionText, replyText;

  private String[] questionArray;
  private int questionIndex=0;
  private int[] replyArray;
  private boolean nextButtonEnabled;
  //private String currentReply;
  private boolean trueButtonSelected;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);

    getSupportActionBar().setTitle(R.string.question_title);

    Log.d(TAG, "onCreate()");

    initLayoutData();
    linkLayoutComponents();
    //resetReplyContent();

    if(savedInstanceState != null) {
      questionIndex=savedInstanceState.getInt(KEY_INDEX);
      //currentReply = savedInstanceState.getString(KEY_REPLY);
      trueButtonSelected=savedInstanceState.getBoolean(KEY_REPLY);
      nextButtonEnabled=savedInstanceState.getBoolean(KEY_ENABLED);

    }

    updateLayoutContent();
    enableLayoutButtons();
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy()");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    Log.d(TAG, "onSaveInstanceState()");

    outState.putInt(KEY_INDEX, questionIndex);
    //outState.putString(KEY_REPLY, currentReply);
    outState.putBoolean(KEY_REPLY, trueButtonSelected);
    outState.putBoolean(KEY_ENABLED, nextButtonEnabled);
  }

  private void enableLayoutButtons() {

    trueButton.setOnClickListener(v -> onTrueButtonClicked());
    falseButton.setOnClickListener(v -> onFalseButtonClicked());
    nextButton.setOnClickListener(v -> onNextButtonClicked());
    cheatButton.setOnClickListener(v -> onCheatButtonClicked());
  }


  private void initLayoutData() {
    questionArray=getResources().getStringArray(R.array.question_array);
    replyArray=getResources().getIntArray(R.array.reply_array);
  }

  private void linkLayoutComponents() {
    falseButton = findViewById(R.id.falseButton);
    trueButton = findViewById(R.id.trueButton);
    cheatButton = findViewById(R.id.cheatButton);
    nextButton = findViewById(R.id.nextButton);

    questionText = findViewById(R.id.questionText);
    replyText = findViewById(R.id.replyText);
  }

  /*
  private void resetReplyContent() {
    currentReply = getString(R.string.empty_text);
  }
  */

  private void updateLayoutContent() {
    questionText.setText(questionArray[questionIndex]);
    //replyText.setText(currentReply);


    if(trueButtonSelected) {
      if(replyArray[questionIndex] == 1) {
        replyText.setText(R.string.correct_text);
      } else {
        replyText.setText(R.string.incorrect_text);
      }

    } else { // has pulasado false o ninguno

      if(!nextButtonEnabled) {
        replyText.setText(R.string.empty_text);
      } else {
        if(replyArray[questionIndex] == 0) {
          replyText.setText(R.string.correct_text);
        } else {
          replyText.setText(R.string.incorrect_text);
        }
      }
    }

    nextButton.setEnabled(nextButtonEnabled);
    cheatButton.setEnabled(!nextButtonEnabled);
    falseButton.setEnabled(!nextButtonEnabled);
    trueButton.setEnabled(!nextButtonEnabled);
  }

  private void onTrueButtonClicked() {

    if(replyArray[questionIndex] == 1) {
      //currentReply=getString(R.string.correct_text);
      replyText.setText(R.string.correct_text);
    } else {
      //currentReply=getString(R.string.incorrect_text);
      replyText.setText(R.string.incorrect_text);
    }

    nextButtonEnabled = true;
    trueButtonSelected = true;
    updateLayoutContent();
  }

  private void onFalseButtonClicked() {

    if(replyArray[questionIndex] == 0) {
      //currentReply=getString(R.string.correct_text);
      replyText.setText(R.string.correct_text);
    } else {
      //currentReply=getString(R.string.incorrect_text);
      replyText.setText(R.string.incorrect_text);
    }

    nextButtonEnabled = true;
    trueButtonSelected = false;
    updateLayoutContent();
  }

  private void onCheatButtonClicked() {

    Intent intent = new Intent(this, CheatActivity.class);
    intent.putExtra(CheatActivity.EXTRA_ANSWER, replyArray[questionIndex]);
    startActivityForResult(intent, CHEAT_REQUEST);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Log.d(TAG, "onActivityResult()");

    if (requestCode == CHEAT_REQUEST) {
      if (resultCode == RESULT_OK) {

        boolean answerCheated = data.getBooleanExtra(
            CheatActivity.EXTRA_CHEATED, false
        );

        Log.d(TAG, "answerCheated: " + answerCheated);

        if(answerCheated) {
          nextButtonEnabled = true;
          onNextButtonClicked();
        }
      }
    }
  }

  private void onNextButtonClicked() {
    Log.d(TAG, "onNextButtonClicked()");

    nextButtonEnabled = false;
    questionIndex++;

    // si queremos que el quiz acabe al llegar
    // a la ultima pregunta debemos comentar esta linea
    checkIndexData();

    if(questionIndex < questionArray.length) {
      //resetReplyContent();
      trueButtonSelected=false;
      updateLayoutContent();
    }

  }

  private void checkIndexData() {

    // hacemos que si llegamos al final del quiz
    // volvamos a empezarlo nuevamente
    if(questionIndex == questionArray.length) {
      questionIndex=0;
    }

  }
}
