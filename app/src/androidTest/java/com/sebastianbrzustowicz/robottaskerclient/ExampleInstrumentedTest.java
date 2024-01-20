package com.sebastianbrzustowicz.robottaskerclient;

import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.util.Random;
import static org.hamcrest.Matchers.not;

import com.sebastianbrzustowicz.robottaskerclient.activity.LoginActivity;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.sebastianbrzustowicz.robottaskerclient", appContext.getPackageName());
    }

    @Test
    public void loginButtonClicked_Success() {
        // Type email and password
        Espresso.onView(withId(R.id.et_dataInputEmail)).perform(ViewActions.typeText("email3"));
        Espresso.onView(withId(R.id.et_dataInputPassword)).perform(ViewActions.typeText("password3"));

        // Click the login button
        Espresso.onView(withId(R.id.btn_LoginRequest)).perform(ViewActions.click());

        intending(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.MenuActivity"));
    }

    @Test
    public void loginButtonClicked_Failed() {
        // Type email and password
        Espresso.onView(withId(R.id.et_dataInputEmail)).perform(ViewActions.typeText("invalid@example.com"));
        Espresso.onView(withId(R.id.et_dataInputPassword)).perform(ViewActions.typeText("password"));

        // Click the login button
        Espresso.onView(withId(R.id.btn_LoginRequest)).perform(ViewActions.click());

        intending(not(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.MenuActivity")));
    }

    @Test
    public void swapToRegisterButtonClicked() {
        Espresso.onView(withId(R.id.btn_SwapToRegister)).perform(ViewActions.click());

        Intents.intended(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.RegistrationActivity"));
    }


    @Test
    public void registerButtonClicked_Success() {
        Espresso.onView(withId(R.id.btn_SwapToRegister)).perform(ViewActions.click());

        Random random = new Random();
        Espresso.onView(withId(R.id.et_CustomVehicleId)).perform(ViewActions.typeText(String.valueOf(random.nextInt())));
        Espresso.onView(withId(R.id.et_VehicleName)).perform(ViewActions.typeText(String.valueOf(random.nextInt())));
        Espresso.onView(withId(R.id.et_VehicleType)).perform(ViewActions.typeText(String.valueOf(random.nextInt())));
        Espresso.onView(withId(R.id.et_dataInputPhoneNumberRegisterForm)).perform(ViewActions.typeText(String.valueOf(random.nextInt())));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.btn_CustomRegisterSend)).perform(ViewActions.click());

        Intents.intended(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.LoginActivity"));
    }

    @Test
    public void registerButtonClicked_Failed() {
        Espresso.onView(withId(R.id.btn_SwapToRegister)).perform(ViewActions.click());

        Random random = new Random();
        Espresso.onView(withId(R.id.et_CustomVehicleId)).perform(ViewActions.typeText("email3"));
        Espresso.onView(withId(R.id.et_VehicleName)).perform(ViewActions.typeText("password3"));
        Espresso.onView(withId(R.id.et_VehicleType)).perform(ViewActions.typeText("quadcopter"));
        Espresso.onView(withId(R.id.et_dataInputPhoneNumberRegisterForm)).perform(ViewActions.typeText("123456789"));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.btn_CustomRegisterSend)).perform(ViewActions.click());

        Intents.intended(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.RegistrationActivity"));
    }

    @Test
    public void swapToLoginButtonClicked() {
        Espresso.onView(withId(R.id.btn_SwapToRegister)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.btn_SwapToLoginRegisterForm)).perform(ViewActions.click());

        Intents.intended(hasComponent("com.sebastianbrzustowicz.robottaskerclient.activity.LoginActivity"));
    }
}