package es.ulpgc.eite.da.basicquizlab;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@SuppressWarnings("ALL")
@LargeTest
@RunWith(AndroidJUnit4.class)
public class LandscapeQuestionTest {

  @Rule
  public ActivityTestRule<QuestionActivity> mActivityTestRule =
      new ActivityTestRule<>(QuestionActivity.class);

  @Test
  public void landscapeQuestionTest() {

    // GIVEN

    ViewInteraction textView = onView(withId(R.id.questionText));
    textView.check(matches(withText("Question #1: True")));

    ViewInteraction textView2 = onView(withId(R.id.replyText));
    textView2.check(matches(withText("???")));

    // WHEN

    ViewInteraction appCompatButton = onView(withId(R.id.trueButton));
    appCompatButton.perform(click());

    // THEN & GIVEN

    ViewInteraction textView3 = onView(withId(R.id.questionText));
    textView3.check(matches(withText("Question #1: True")));

    ViewInteraction textView4 = onView(withId(R.id.replyText));
    textView4.check(matches(withText("Correct")));

    // ---------------------

    // WHEN

//    mActivityTestRule.getActivity()
//        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    try {

      Thread.sleep(2000);

      UiDevice device = UiDevice.getInstance(getInstrumentation());
      device.setOrientationLeft();

      Thread.sleep(2000);

    } catch (Exception e) {

    }

    // THEN & GIVEN

    ViewInteraction textView5 = onView(withId(R.id.questionText));
    textView5.check(matches(withText("Question #1: True")));

    ViewInteraction textView6 = onView(withId(R.id.replyText));
    textView6.check(matches(withText("Correct")));

    // ---------------------

    // WHEN

    ViewInteraction appCompatButton2 = onView(withId(R.id.nextButton));
    appCompatButton2.perform(click());

    // THEN & GIVEN

    ViewInteraction textView7 = onView(withId(R.id.questionText));
    textView7.check(matches(withText("Question #2: False")));

    ViewInteraction textView8 = onView(withId(R.id.replyText));
    textView8.check(matches(withText("???")));

    // ---------------------

    // WHEN

//    mActivityTestRule.getActivity()
//        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    try {

      Thread.sleep(2000);

      UiDevice device = UiDevice.getInstance(getInstrumentation());
      device.setOrientationNatural();

      Thread.sleep(2000);

    } catch (Exception e) {

    }

    // THEN

    ViewInteraction textView9 = onView(withId(R.id.questionText));
    textView9.check(matches(withText("Question #2: False")));

    ViewInteraction textView10 = onView(withId(R.id.replyText));
    textView10.check(matches(withText("???")));
  }

  
}
