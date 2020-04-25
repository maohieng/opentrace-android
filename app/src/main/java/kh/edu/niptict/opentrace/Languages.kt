package kh.edu.niptict.opentrace

import android.content.Context
import com.yariksoffice.lingver.Lingver
import java.util.*

/**
 * This class is used for ...
 *
 *
 * @autor MAO Hieng 4/24/2020
 */
object Languages {

    // https://www.loc.gov/standards/iso639-2/php/langcodes_name.php?code_ID=233
    // https://en.wikipedia.org/wiki/ISO_3166-2:KH
    val KHMER = Locale("khm", "KH")
    val CHINA = Locale.CHINA
    val ENGLISH = Locale.ENGLISH
    val UNDEFINED = Locale.FRANCE;

    fun isKhmerOrChinaLanguage(context: Context): Locale? {
        val default = Locale.getDefault()
        if (default.language == KHMER.language || default.language == CHINA.language) {
            return default
        } else {
            // get from shared preference
            val appLanguage = Lingver.getInstance().getLanguage();
            if (appLanguage == KHMER.language) {
                return KHMER;
            } else if (appLanguage == CHINA.language) {
                return CHINA;
            }

            return null;
        }
    }

    private fun isEnglish(context: Context, locale: Locale): Boolean {
        return locale.language == ENGLISH.language
    }

}