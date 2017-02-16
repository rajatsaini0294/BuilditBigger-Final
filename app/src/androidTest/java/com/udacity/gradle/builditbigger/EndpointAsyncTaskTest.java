package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by rajat on 10/30/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndpointAsyncTaskTest {
    CountDownLatch signal = new CountDownLatch(1);
    String joke = null;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void changeText_sameActivity() throws InterruptedException {
        /*onView(withId(R.id.jokeButton))
                .perform(click());
        onView(withId(R.id.joke_view))
                .check(matches(not(withText(""))));
*/
        try {
            joke = new EndpointAsyncTask(new EndpointAsyncTask.Listener() {

                @Override
                public void onJokeLoaded(String joke) {
                    signal.countDown();
                }
            }).execute().get();
            signal.await();
            Log.d("CloudModuleTest", "Retrieved a joke successfully: " + joke);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
