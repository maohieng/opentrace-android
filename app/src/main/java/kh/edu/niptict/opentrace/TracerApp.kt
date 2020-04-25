package kh.edu.niptict.opentrace

import android.app.Application
import android.content.Context
import android.os.Build
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import kh.edu.niptict.opentrace.idmanager.TempIDManager
import kh.edu.niptict.opentrace.logging.CentralLog
import kh.edu.niptict.opentrace.services.BluetoothMonitoringService
import kh.edu.niptict.opentrace.streetpass.CentralDevice
import kh.edu.niptict.opentrace.streetpass.PeripheralDevice
import java.util.*

class TracerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext = applicationContext

//        val defaultLanguage = CURRENT_LANGUAGE;
        val defaultLanguage = Languages.KHMER;
        val store = PreferenceLocaleStore(this, defaultLanguage)
        Lingver.init(this, store)
    }

    companion object {

        var CURRENT_LANGUAGE: Locale = Languages.UNDEFINED;

        private val TAG = "TracerApp"
        const val ORG = BuildConfig.ORG

        lateinit var AppContext: Context

        fun thisDeviceMsg(): String {
            BluetoothMonitoringService.broadcastMessage?.let {
                CentralLog.i(TAG, "Retrieved BM for storage: $it")

                if (!it.isValidForCurrentTime()) {

                    var fetch = TempIDManager.retrieveTemporaryID(AppContext)
                    fetch?.let {
                        CentralLog.i(TAG, "Grab New Temp ID")
                        BluetoothMonitoringService.broadcastMessage = it
                    }

                    if (fetch == null) {
                        CentralLog.e(TAG, "Failed to grab new Temp ID")
                    }

                }
            }
            return BluetoothMonitoringService.broadcastMessage?.tempID ?: "Missing TempID"
        }

        fun asPeripheralDevice(): PeripheralDevice {
            return PeripheralDevice(Build.MODEL, "SELF")
        }

        fun asCentralDevice(): CentralDevice {
            return CentralDevice(Build.MODEL, "SELF")
        }
    }
}
