package es.ulpgc.eite.da.basicquizlab;


import android.content.Context;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheatInstrumentedTest {

  @Rule
  public ActivityTestRule<QuestionActivity> mActivityTestRule =
      new ActivityTestRule<>(QuestionActivity.class);



  @Test
  public void cheatActivityTest() {

    Context appContext =
        InstrumentationRegistry.getInstrumentation().getTargetContext();

    String[] questionArray =
        appContext.getResources().getStringArray(R.array.question_array);

    String correctText = appContext.getString(R.string.correct_text);
    String incorrectText = appContext.getString(R.string.incorrect_text);
    String emptyText = appContext.getString(R.string.empty_text);

    String trueText = appContext.getString(R.string.true_text);
    String falseText = appContext.getString(R.string.false_text);
    String warningText = appContext.getString(R.string.warning_text);

    // GIVEN

    ViewInteraction textView21 = onView(withId(R.id.questionText));
    textView21.check(matches(withText(questionArray[0])));

    ViewInteraction textView22 = onView(withId(R.id.replyText));
    textView22.check(matches(withText(emptyText)));


    // WHEN

    ViewInteraction button = onView(withId(R.id.cheatButton));
    button.perform(click());

    // THEN & GIVEN

    ViewInteraction textView = onView(withId(R.id.warningText));
    textView.check(matches(withText(warningText)));


    ViewInteraction textView2 = onView(withId(R.id.answerText));
    textView2.check(matches(withText(emptyText)));


    // ------------------------

    // WHEN

    pressBack();

    // THEN & GIVEN

    ViewInteraction textView3 = onView(withId(R.id.questionText));
    textView3.check(matches(withText(questionArray[0])));

    ViewInteraction textView4 = onView(withId(R.id.replyText));
    textView4.check(matches(withText(emptyText)));

    // ------------------------

    // WHEN

    ViewInteraction button2 = onView(withId(R.id.cheatButton));
    button2.perform(click());

    ViewInteraction button3 = onView(withId(R.id.noButton));
    button3.perform(click());


    // THEN & GIVEN

    ViewInteraction textView5 = onView(withId(R.id.questionText));
    textView5.check(matches(withText(questionArray[0])));

    ViewInteraction textView6 = onView(withId(R.id.replyText));
    textView6.check(matches(withText(emptyText)));

    // ------------------------

    // WHEN

    ViewInteraction button4 = onView(withId(R.id.cheatButton));
    button4.perform(click());

    ViewInteraction button5 = onView(withId(R.id.yesButton));
    button5.perform(click());


    // THEN & GIVEN

    ViewInteraction textView7 = onView(withId(R.id.warningText));
    textView7.check(matches(withText(warningText)));

    ViewInteraction textView8 = onView(withId(R.id.answerText));
    textView8.check(matches(withText(trueText)));

    // ------------------------

    // WHEN

    pressBack();

    // THEN & GIVEN
    
    ViewInteraction textView9 = onView(withId(R.id.questionText));
    textView9.check(matches(withText(questionArray[1])));

    ViewInteraction textView10 = onView(withId(R.id.replyText));
    textView10.check(matches(withText(emptyText)));


    // ------------------------

    // WHEN

    ViewInteraction button6 = onView(withId(R.id.falseButton));
    button6.perform(click());

    ViewInteraction textView11 = onView(withId(R.id.questionText));
    textView11.check(matches(withText(questionArray[1])));

    ViewInteraction textView13 = onView(withId(R.id.replyText));
    textView13.check(matches(withText(correctText)));


    ViewInteraction button7 = onView(withId(R.id.cheatButton));
    button7.perform(click());

    // THEN

    ViewInteraction textView14 = onView(withId(R.id.questionText));
    textView14.check(matches(withText(questionArray[1])));

    ViewInteraction textView15 = onView(withId(R.id.replyText));
    textView15.check(matches(withText(correctText)));
  }


  private void delayTest(long msecs) {

    try {
      Thread.sleep(msecs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
