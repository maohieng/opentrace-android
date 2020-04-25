package kh.edu.niptict.opentrace.onboarding

import android.content.Intent
import android.os.Bundle
import kh.edu.niptict.opentrace.BaseLanguageActivity
import kh.edu.niptict.opentrace.R
import kotlinx.android.synthetic.main.main_activity_onboarding.*

class PreOnboardingActivity : BaseLanguageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_onboarding)
        btn_onboardingStart.setOnClickListener {
            var intent = Intent(this, HowItWorksActivity::class.java)
            startActivity(intent)
        }
    }
}
