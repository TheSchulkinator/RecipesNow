package com.example.theschulk.recipesnow;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.example.theschulk.recipesnow.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeDetailTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void recipeDetailTest() {
        ViewInteraction recyclerView = onView(
allOf(withId(R.id.rv_recipe_list),
childAtPosition(
withClassName(is("android.widget.RelativeLayout")),
0)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));
        
        ViewInteraction recyclerView2 = onView(
allOf(withId(R.id.rv_recipe_list),
childAtPosition(
withClassName(is("android.widget.RelativeLayout")),
0)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));
        
        ViewInteraction recyclerView3 = onView(
allOf(withId(R.id.rv_recipe_step_list),
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(5000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
        
        pressBack();
        
        ViewInteraction recyclerView4 = onView(
allOf(withId(R.id.rv_recipe_step_list),
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0)));
        recyclerView4.perform(actionOnItemAtPosition(8, click()));
        
        pressBack();
        
        ViewInteraction recyclerView5 = onView(
allOf(withId(R.id.rv_recipe_step_list),
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0)));
        recyclerView5.perform(actionOnItemAtPosition(9, click()));
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(5000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
        
        pressBack();
        
        pressBack();
        
        pressBack();
        
        }

        private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
