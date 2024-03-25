package ru.netology.testing.uiautomator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


const val MODEL_PACKAGE = "ru.netology.testing.uiautomator"

const val TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class ChangeTextTest {

    private lateinit var device: UiDevice
    private val textToSet = "Netology"





    private fun waitForPackage(MODEL_PACKAGE: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(MODEL_PACKAGE)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(MODEL_PACKAGE)), TIMEOUT)
    }

    @Before
    fun beforeEachTest() {
        // Press home
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)

        waitForPackage(MODEL_PACKAGE)
    }


    @Test
    fun testChangeText() {

        device.findObject(By.res(MODEL_PACKAGE, "userInput")).text = textToSet
        device.findObject(By.res(MODEL_PACKAGE, "buttonChange")).click()

        val result = device.findObject(By.res(MODEL_PACKAGE, "textToBeChanged")).text
        assertEquals(result, textToSet)
    }

    @Test
    fun testNoTextAdded() {

        device.findObject(By.res(MODEL_PACKAGE, "buttonChange")).click()

        val initialText = "Hello UiAutomator!"
        val result = device.findObject(By.res(MODEL_PACKAGE, "textToBeChanged")).text
        assertEquals(result, initialText)
    }

    @Test
    fun testNewWindowText() {

        device.findObject(By.res(MODEL_PACKAGE, "userInput")).text = textToSet
        device.findObject(By.res(MODEL_PACKAGE, "buttonActivity")).click()
        waitForPackage(MODEL_PACKAGE)

        val result = device.findObject(By.res(MODEL_PACKAGE, "text")).text
        assertEquals(result, textToSet)
    }

}