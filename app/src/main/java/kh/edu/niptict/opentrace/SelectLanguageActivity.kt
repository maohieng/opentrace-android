package kh.edu.niptict.opentrace

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yariksoffice.lingver.Lingver
import kh.edu.niptict.opentrace.logging.CentralLog
import java.util.*

/**
 * This class is used for ...
 *
 *
 * @autor MAO Hieng 4/24/2020
 */
class SelectLanguageActivity : AppCompatActivity() {

    private val TAG = "SelectLanguageActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)

        val khmerOrChinaLocale = Languages.isKhmerOrChinaLanguage(baseContext)
        if (khmerOrChinaLocale != null) {
            CentralLog.i(TAG, "Auto select app language: ${khmerOrChinaLocale.language}")
            setCurrentLocale(khmerOrChinaLocale)
            return
        }
    }

    fun clickKhmerLanguage(view: View) {
        setCurrentLocale(Languages.KHMER)
    }

    fun clickChinaLanguage(view: View) {
        setCurrentLocale(Languages.CHINA)
    }

    fun clickEnglishLanguage(view: View) {
        setCurrentLocale(Languages.ENGLISH)
    }

    private fun setCurrentLocale(newLocale: Locale) {
        Lingver.getInstance().setLocale(this, newLocale)
        TracerApp.CURRENT_LANGUAGE = newLocale;
        goNext()
        finish()
    }

    private fun goNext() {
        SplashActivity.goNext(this)
    }

}