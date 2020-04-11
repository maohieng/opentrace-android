package kh.edu.niptict.opentrace.protocol.v2

import kh.edu.niptict.opentrace.TracerApp
import kh.edu.niptict.opentrace.logging.CentralLog
import kh.edu.niptict.opentrace.protocol.BlueTraceProtocol
import kh.edu.niptict.opentrace.protocol.CentralInterface
import kh.edu.niptict.opentrace.protocol.PeripheralInterface
import kh.edu.niptict.opentrace.streetpass.CentralDevice
import kh.edu.niptict.opentrace.streetpass.ConnectionRecord
import kh.edu.niptict.opentrace.streetpass.PeripheralDevice

class BlueTraceV2 : BlueTraceProtocol(
    versionInt = 2,
    peripheral = V2Peripheral(),
    central = V2Central()
)

class V2Peripheral : PeripheralInterface {

    private val TAG = "V2Peripheral"

    override fun prepareReadRequestData(protocolVersion: Int): ByteArray {
        return V2ReadRequestPayload(
            v = protocolVersion,
            id = TracerApp.thisDeviceMsg(),
            o = TracerApp.ORG,
            peripheral = TracerApp.asPeripheralDevice()
        ).getPayload()
    }

    override fun processWriteRequestDataReceived(
        dataReceived: ByteArray,
        centralAddress: String
    ): ConnectionRecord? {
        try {
            val dataWritten =
                V2WriteRequestPayload.fromPayload(
                    dataReceived
                )

            return ConnectionRecord(
                version = dataWritten.v,
                msg = dataWritten.id,
                org = dataWritten.o,
                peripheral = TracerApp.asPeripheralDevice(),
                central = CentralDevice(dataWritten.mc, centralAddress),
                rssi = dataWritten.rs,
                txPower = null
            )
        } catch (e: Throwable) {
            CentralLog.e(TAG, "Failed to deserialize write payload ${e.message}")
        }
        return null
    }
}

class V2Central : CentralInterface {

    private val TAG = "V2Central"

    override fun prepareWriteRequestData(
        protocolVersion: Int,
        rssi: Int,
        txPower: Int?
    ): ByteArray {
        return V2WriteRequestPayload(
            v = protocolVersion,
            id = TracerApp.thisDeviceMsg(),
            o = TracerApp.ORG,
            central = TracerApp.asCentralDevice(),
            rs = rssi
        ).getPayload()
    }

    override fun processReadRequestDataReceived(
        dataRead: ByteArray,
        peripheralAddress: String,
        rssi: Int,
        txPower: Int?
    ): ConnectionRecord? {
        try {
            val readData =
                V2ReadRequestPayload.fromPayload(
                    dataRead
                )
            var peripheral =
                PeripheralDevice(readData.mp, peripheralAddress)

            var connectionRecord = ConnectionRecord(
                version = readData.v,
                msg = readData.id,
                org = readData.o,
                peripheral = peripheral,
                central = TracerApp.asCentralDevice(),
                rssi = rssi,
                txPower = txPower
            )
            return connectionRecord
        } catch (e: Throwable) {
            CentralLog.e(TAG, "Failed to deserialize read payload ${e.message}")
        }

        return null
    }
}
