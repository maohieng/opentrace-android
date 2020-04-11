package kh.edu.niptict.opentrace.streetpass.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kh.edu.niptict.opentrace.streetpass.persistence.StreetPassRecord
import kh.edu.niptict.opentrace.streetpass.persistence.StreetPassRecordDatabase
import kh.edu.niptict.opentrace.streetpass.persistence.StreetPassRecordRepository

class RecordViewModel(app: Application) : AndroidViewModel(app) {

    private var repo: StreetPassRecordRepository

    var allRecords: LiveData<List<StreetPassRecord>>

    init {
        val recordDao = StreetPassRecordDatabase.getDatabase(app).recordDao()
        repo = StreetPassRecordRepository(recordDao)
        allRecords = repo.allRecords
    }


}
