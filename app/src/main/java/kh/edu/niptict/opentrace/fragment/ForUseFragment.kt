package kh.edu.niptict.opentrace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.niptict.opentrace.R
import kotlinx.android.synthetic.main.fragment_upload_foruse.*

class ForUseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upload_foruse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forUseFragmentActionButton.setOnClickListener {
            var myParentFragment: ForUseByOTCFragment = (parentFragment as ForUseByOTCFragment)
            myParentFragment.goToUploadFragment()
        }
    }
}
