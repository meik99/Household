package tk.rynkbit.household;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by michael on 30.07.17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UIMainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, true);

    @Test
    public void roomsScreenOpened(){
        Activity activity = mainActivityActivityTestRule.getActivity();

        Assert.assertNotNull(activity.findViewById(R.id.container));
        Assert.assertNotNull(activity.findViewById(R.id.fabAddRoom));
    }

    @Test
    public void openEmptyRoomDetailActivity(){
        roomsScreenOpened();

        onView(withId(R.id.fabAddRoom))
                .perform(ViewActions.click());

        onView(withId(R.id.editRoomName));
    }
}
