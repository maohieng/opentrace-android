package kh.edu.niptict.opentrace.onboarding

import android.content.Intent
import android.os.Bundle
import kh.edu.niptict.opentrace.BaseLanguageActivity
import kh.edu.niptict.opentrace.R
import kotlinx.android.synthetic.main.main_activity_howitworks.*

class HowItWorksActivity : BaseLanguageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_howitworks)
        btn_onboardingStart.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}
