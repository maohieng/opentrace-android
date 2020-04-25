package kh.edu.niptict.opentrace

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yariksoffice.lingver.Lingver
import kh.edu.niptict.opentrace.logging.CentralLog
import kh.edu.niptict.opentrace.onboarding.PreOnboardingActivity

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity";

    private val SPLASH_TIME: Long = 2000
    var needToUpdateApp = false

    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mHandler = Handler()

        //check if the intent was from notification and its a update notification
        intent.extras?.let {
            val notifEvent: String? = it.getString("event", null)

            notifEvent?.let {
                if (it.equals("update")) {
                    needToUpdateApp = true
                    intent = Intent(Intent.ACTION_VIEW);
                    //Copy App URL from Google Play Store.
                    intent.data = Uri.parse(BuildConfig.STORE_URL)

                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()
        if (!needToUpdateApp) {
            mHandler.postDelayed({
                goToNextScreen()
                finish()
            }, SPLASH_TIME)
        }
    }

    private fun goToNextScreen() {
        val locale = Lingver.getInstance().getLocale();
        CentralLog.i(TAG, "App language: $locale")
        if (locale != Languages.UNDEFINED) {
            TracerApp.CURRENT_LANGUAGE = locale;
            goNext(this)
        } else {
            startActivity(Intent(this, SelectLanguageActivity::class.java))
        }
    }

    companion object {
        fun goNext(activity: Activity) {
            if (!Preference.isOnBoarded(activity)) {
                activity.startActivity(Intent(activity, PreOnboardingActivity::class.java))
            } else {
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
        }
    }
}
